package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentRequest {
    @NotNull(message = "ReservationId cannot be empty!")
    private int reservationId;
    @NotNull(message = "Payment Type cannot be empty!")
    private PaymentType paymentType;
}
