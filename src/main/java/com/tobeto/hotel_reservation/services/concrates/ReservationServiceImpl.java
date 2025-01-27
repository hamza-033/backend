package com.tobeto.hotel_reservation.services.concrates;

import com.tobeto.hotel_reservation.entities.*;
import com.tobeto.hotel_reservation.repositories.ReservationRepository;
import com.tobeto.hotel_reservation.services.abstracts.*;
import com.tobeto.hotel_reservation.services.dtos.requests.AddReservationRequest;
import com.tobeto.hotel_reservation.services.dtos.requests.UpdateReservationRequest;
import com.tobeto.hotel_reservation.services.dtos.responses.AddReservationResponse;
import com.tobeto.hotel_reservation.services.dtos.responses.ListReservationResponse;
import com.tobeto.hotel_reservation.services.mappers.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final NotificationService notificationService;

    @Override
    public AddReservationResponse add(AddReservationRequest addReservationRequest) {
        Optional<User> user = userService.findById(addReservationRequest.getUserId());
        Optional<Room> room = roomService.findById(addReservationRequest.getRoomId());

        if (user.isPresent() && room.isPresent()) {

            List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(
                    addReservationRequest.getRoomId(),
                    addReservationRequest.getEnteranceDay(),
                    addReservationRequest.getReleaseDay()
            );

            if (!conflictingReservations.isEmpty()) {
                throw new RuntimeException("There is another reservation for this room on these dates.");
            }

            Reservation reservation = ReservationMapper.INSTANCE.reservationFromAddRequest(addReservationRequest);
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setRoom(room.get());
            calculateTotalAmount(reservation);
            reservation = reservationRepository.save(reservation);
            AddReservationResponse response = ReservationMapper.INSTANCE.addResponseFromReservation(reservation);
            //Create notification for new reservation
            notificationService.createNotification(reservation, "New reservation created. Waiting for confirmation.", NotificationStatus.PENDINGRESERVATION);
            return response;
        } else {
            throw new RuntimeException("UserId or RoomId not found.");
        }
    }

    @Override
    public void update(UpdateReservationRequest updateReservationRequest) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(updateReservationRequest.getId());
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            ReservationStatus oldStatus = reservation.getReservationStatus();
            reservation.setReservationStatus(updateReservationRequest.getReservationStatus());
            reservationRepository.save(reservation);
            //Create notification for status change
            if (oldStatus != reservation.getReservationStatus()) {
                String text = "Reservation status changed: " + reservation.getReservationStatus().name();
                NotificationStatus notificationStatus = getNotificationStatusFromReservationStatus(reservation.getReservationStatus());
                notificationService.createNotification(reservation, text, notificationStatus);
            }
        } else {
            throw new RuntimeException("Reservation Id not found!");
        }
    }

    //TODO: ROOM AVAILABILITY STATUS TO BE DISCUSSED
    @Override
    public Optional<Reservation> findById(int id) {
        return reservationRepository.findById(id);
    }

    private void calculateTotalAmount(Reservation reservation) {
        LocalDate enteranceDay = reservation.getEnteranceDay();
        LocalDate releaseDay = reservation.getReleaseDay();
        double roomPrice = reservation.getRoom().getPrice();

        int comparisonResult = releaseDay.compareTo(enteranceDay);
        if (comparisonResult > 0) {
            long difference = ChronoUnit.DAYS.between(enteranceDay, releaseDay);
            double totalAmount = difference * roomPrice;
            reservation.setTotalAmount(totalAmount);
        } else {
            throw new RuntimeException("Exit date cannot be before the entrance date!");
        }
    }

    //TODO: CREATION DATE FOR RESERVATION CAN BE ADDED.

    private NotificationStatus getNotificationStatusFromReservationStatus(ReservationStatus reservationStatus) {
        switch (reservationStatus) {
            case PENDING:
                return NotificationStatus.PENDINGRESERVATION;
            case CONFIRMED:
                return NotificationStatus.CONFIRMEDRESERVATION;
            case CANCELED:
                return NotificationStatus.CANCELEDRESERVATION;
            default:
                throw new IllegalArgumentException("Unknown reservation status: " + reservationStatus);
        }
    }

    @Override
    public List<ListReservationResponse> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> ReservationMapper.INSTANCE.listResponseFromReservation(reservation))
                .toList();
    }

    @Override
    public ListReservationResponse getById(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return ReservationMapper.INSTANCE.listResponseFromReservation(reservation);
    }

    @Override
    public List<Object[]> getReservationsByUserId(int userId) {
        List<Object[]> reservations = reservationRepository.findReservationsByUserId(userId);
        if (reservations.isEmpty()) {
            throw new RuntimeException("No reservations found for this user.");
        }
        return reservations;
    }

    @Override
    public List<Object[]> findReservationsByHotelId(int hotelId) {
        return reservationRepository.findReservationsByHotelId(hotelId);
    }
}
