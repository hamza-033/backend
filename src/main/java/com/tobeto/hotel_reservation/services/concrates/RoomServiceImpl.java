package com.tobeto.hotel_reservation.services.concrates;

import com.tobeto.hotel_reservation.entities.Hotel;
import com.tobeto.hotel_reservation.entities.Room;
import com.tobeto.hotel_reservation.repositories.RoomRepository;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation.services.dtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation.services.mappers.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    //TODO: The room can only be added by the manager, so the security part will be resolved.
    @Override
    public void add(AddRoomRequest addRoomRequest) {
        Optional<Hotel> hotel = hotelService.findById(addRoomRequest.getHotelId());

        if (hotel.isPresent()) {
            Room room = RoomMapper.INSTANCE.roomFromAddRequest(addRoomRequest);
            room = roomRepository.save(room);
        } else {
            throw new RuntimeException("Hotel not found.");
        }
    }

    @Override
    public void update(UpdateRoomRequest updateRoomRequest) {
        Optional<Room> optionalRoom = roomRepository.findById(updateRoomRequest.getId());

        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setRoomType(updateRoomRequest.getRoomType());
            room.setCapacity(updateRoomRequest.getCapacity());
            room.setPrice(updateRoomRequest.getPrice());
            room.setRoomStatus(updateRoomRequest.getRoomStatus());
            roomRepository.save(room);
        } else {
            throw new RuntimeException("Room ID not found.");
        }
    }

    @Override
    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Object[]> findAvailableRooms(LocalDate entranceDay, LocalDate releaseDay, String roomType, Integer capacity, String location) {
        if (releaseDay.isBefore(entranceDay)) {
            throw new IllegalArgumentException("Release date cannot be before the entrance date.");
        }
        List<Object[]> availableRooms = roomRepository.findAvailableRooms(entranceDay, releaseDay, roomType, capacity, location);
        if (availableRooms.isEmpty()) {
            throw new RuntimeException("No available rooms on these dates.");
        }
        return availableRooms;
    }
}
