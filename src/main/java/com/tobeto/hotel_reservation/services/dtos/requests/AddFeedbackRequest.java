package com.tobeto.hotel_reservation.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFeedbackRequest {
    @NotNull(message = "Reservation Id cannot be empty")
    private int reservationId;
    @NotNull(message = "Rating cannot be empty")
    private int rating;
    @NotBlank(message = "Comment cannot be empty")
    private String comment;
}
