package com.tobeto.hotel_reservation.services.concrates;

import com.tobeto.hotel_reservation.entities.NotificationStatus;
import com.tobeto.hotel_reservation.entities.Payment;
import com.tobeto.hotel_reservation.entities.Reservation;
import com.tobeto.hotel_reservation.entities.ReservationStatus;
import com.tobeto.hotel_reservation.repositories.PaymentRepository;
import com.tobeto.hotel_reservation.services.abstracts.NotificationService;
import com.tobeto.hotel_reservation.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.dtos.requests.AddPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationService reservationService;
    private final NotificationService notificationService;

    @Override
    public void add(AddPaymentRequest addPaymentRequest) {
        Optional<Reservation> optionalReservation = reservationService.findById(addPaymentRequest.getReservationId());
        Optional<Payment> optionalPayment = paymentRepository.findByReservationId(addPaymentRequest.getReservationId());

        if (optionalPayment.isEmpty()) {
            if (optionalReservation.isPresent()) {
                Payment payment = new Payment();
                Reservation reservation = optionalReservation.get();
                payment.setReservation(optionalReservation.get());
                payment.setPaymentTotal(optionalReservation.get().getTotalAmount());
                payment.setPaymentType(addPaymentRequest.getPaymentType());
                payment.setDate(LocalDate.now());
                optionalReservation.get().setReservationStatus(ReservationStatus.CONFIRMED);
                paymentRepository.save(payment);
                // Create a notification for the new reservation
                notificationService.createNotification(reservation, "A new reservation has been created. Confirmed.", NotificationStatus.CONFIRMEDRESERVATION);

            } else {
                throw new RuntimeException("Reservation ID not found!");
            }
        } else {
            throw new RuntimeException("Payment for this reservation has already been made!");
        }
    }
}
