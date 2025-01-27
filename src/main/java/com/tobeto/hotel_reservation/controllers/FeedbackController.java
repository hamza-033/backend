package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.FeedbackService;
import com.tobeto.hotel_reservation.services.dtos.requests.AddFeedbackRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("addfeedback")
    public String add(@Valid @RequestBody AddFeedbackRequest request){
        feedbackService.add(request);
        return "Feedback Created Successfully";
    }


    @GetMapping("hotel/{hotelId}/feedbacks")
    public List<Object[]> getFeedbacksByHotelId(@PathVariable int hotelId) {
        return feedbackService.getFeedbacksByHotelId(hotelId);
    }

}
