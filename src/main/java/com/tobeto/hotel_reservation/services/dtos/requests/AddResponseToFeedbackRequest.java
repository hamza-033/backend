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
public class AddResponseToFeedbackRequest {
    @NotNull(message = "FeedbackId cannot be empty!")
    private int feedbackId;
    @NotBlank(message = "Description field cannot be empty!")
    private String description;
}
