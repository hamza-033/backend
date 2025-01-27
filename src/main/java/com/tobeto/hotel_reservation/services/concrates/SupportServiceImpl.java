package com.tobeto.hotel_reservation.services.concrates;

import com.tobeto.hotel_reservation.entities.*;
import com.tobeto.hotel_reservation.repositories.SupportRepository;
import com.tobeto.hotel_reservation.services.abstracts.NotificationService;
import com.tobeto.hotel_reservation.services.abstracts.SupportService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.requests.AddNotificationRequest;
import com.tobeto.hotel_reservation.services.dtos.requests.AddSupportRequest;
import com.tobeto.hotel_reservation.services.dtos.requests.UpdateSupportRequest;
import com.tobeto.hotel_reservation.services.mappers.SupportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public void add(AddSupportRequest addSupportRequest) {
        Optional<User> optionalUser = userService.findById(addSupportRequest.getUserId());
        if (optionalUser.isPresent()) {
            Support support = SupportMapper.INSTANCE.supportFromAddRequest(addSupportRequest);
            User user = optionalUser.get();
            support.setDemandStatus(DemandStatus.DELIVERED);
            supportRepository.save(support);
            notificationService.createNotificationForSupport(user, "Support Request Submitted.", NotificationStatus.NEWSUPPORTDEMAND);
        } else {
            throw new RuntimeException("No user found with this User Id.");
        }
    }

    @Override
    public void update(UpdateSupportRequest updateSupportRequest) {
        Optional<Support> optionalSupport = supportRepository.findById(updateSupportRequest.getId());
        if (optionalSupport.isPresent()) {
            Support support = optionalSupport.get();
            User user = support.getUser();
            support.setDemandStatus(DemandStatus.CLOSED);
            supportRepository.save(support);
            notificationService.createNotificationForSupport(user, "Support Request Closed", NotificationStatus.SUPPORTDEMANDCLOSED);
        } else {
            throw new RuntimeException("Support Id not found.");
        }
    }

    @Override
    public List<Support> getSupportRequestsByUserId(int userId) {
        return supportRepository.findByUserId(userId);
    }
}
