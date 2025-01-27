package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.DemandStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupportRequest {

    @NotNull(message = "Support Id cannot be empty")
    private int id;

    //@NotNull(message = "DemandStatus cannot be empty!")
    //private DemandStatus demandStatus;

}
