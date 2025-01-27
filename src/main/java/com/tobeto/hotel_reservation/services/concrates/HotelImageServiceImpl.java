package com.tobeto.hotel_reservation.services.concrates;

import com.tobeto.hotel_reservation.entities.Hotel;
import com.tobeto.hotel_reservation.entities.HotelImage;
import com.tobeto.hotel_reservation.repositories.HotelImageRepository;
import com.tobeto.hotel_reservation.services.abstracts.HotelImageService;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.dtos.requests.AddHotelImageRequest;
import com.tobeto.hotel_reservation.services.mappers.HotelImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelImageServiceImpl implements HotelImageService {
    private final HotelImageRepository hotelImageRepository;
    private final HotelService hotelService;

    @Override
    public void add(AddHotelImageRequest addHotelImageRequest, MultipartFile file) {
        Optional<Hotel> hotel = hotelService.findById(addHotelImageRequest.getHotelId());
        if (hotel.isPresent()) {
            HotelImage hotelImage = HotelImageMapper.INSTANCE.hotelImageFromAddRequest(addHotelImageRequest);

            String directoryPath = "C:\\Users\\Ertunç\\Desktop\\Java&Angular FullStack\\Hotel Reservation App\\Data\\07-06-2024(user_address)\\hotel_reservation\\images\\hotelimages\\";
            String fileName = file.getOriginalFilename();
            String filePath = directoryPath + fileName;

            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try {
                file.transferTo(new File(filePath));
                hotelImage.setImageUrl(filePath);
                hotelImageRepository.save(hotelImage);
            } catch (IOException e) {
                throw new RuntimeException("File upload error", e);
            }
        } else {
            throw new RuntimeException("Hotel Not Found");
        }
    }

    @Override
    public void delete(int id) {
        Optional<HotelImage> hotelImage = hotelImageRepository.findById(id);
        if (hotelImage.isPresent()) {
            hotelImageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Image Id Not Found");
        }
    }
}
