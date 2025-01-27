package com.tobeto.hotel_reservation.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSupportRequest {

    @NotNull(message = "UserId cannot be empty")
    private int userId;
    @NotBlank(message = "Title cannot be empty")
    @Length(min = 5, max = 50, message = "Title must be between 5 and 50 characters.")
    private String title;
    @NotBlank(message = "Text cannot be empty")
    @Length(min = 10, max = 300, message = "Text must be between 10 and 300 characters.")
    private String text;
}
