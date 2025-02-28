package com.tobeto.hotel_reservation.services.concrates;

import com.tobeto.hotel_reservation.entities.Feedback;
import com.tobeto.hotel_reservation.entities.NotificationStatus;
import com.tobeto.hotel_reservation.entities.Reservation;
import com.tobeto.hotel_reservation.repositories.FeedbackRepository;
import com.tobeto.hotel_reservation.services.abstracts.FeedbackService;
import com.tobeto.hotel_reservation.services.abstracts.NotificationService;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.dtos.requests.AddFeedbackRequest;
import com.tobeto.hotel_reservation.services.mappers.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ReservationService reservationService;
    private final NotificationService notificationService;

    @Override
    public void add(AddFeedbackRequest addFeedbackRequest) {
        Optional<Reservation> optionalReservation = reservationService.findById(addFeedbackRequest.getReservationId());

        Optional<Feedback> optionalFeedback = feedbackRepository.findByReservationId(addFeedbackRequest.getReservationId());

        if (optionalFeedback.isEmpty()) {

            if (optionalReservation.isPresent()) {
                Feedback feedback = FeedbackMapper.INSTANCE.feedbackFromAddRequest(addFeedbackRequest);
                Reservation reservation = optionalReservation.get();
                feedback.setDate(LocalDateTime.now().withNano(0));
                notificationService.createNotification(reservation, "New Comment Notification", NotificationStatus.NEWFEEDBACK);
                feedbackRepository.save(feedback);
            } else {
                throw new RuntimeException("Reservation Id Not Found!");
            }

        } else {
            throw new RuntimeException("A comment already exists for this reservation id!");
        }

    }

    @Override
    public Optional<Feedback> findById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public List<Object[]> getFeedbacksByHotelId(int hotelId) {
        return feedbackRepository.findFeedbacksByHotelId(hotelId);
    }
}
