package com.nusrat.TestSpringBoot.service;

import com.nusrat.TestSpringBoot.entity.Hotel;
import com.nusrat.TestSpringBoot.entity.Room;
import com.nusrat.TestSpringBoot.repository.HotelRepository;
import com.nusrat.TestSpringBoot.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Value("src/main/resources/static/images")
    private String uploadDir;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void saveRoom(Room room, MultipartFile imageFile) throws IOException {
        Hotel hotel = hotelRepository.findById(room.getHotel().getId())
                .orElseThrow(() -> new RuntimeException("Hotel with this id not found"));
        System.out.println("Hotel: " + hotel.toString());

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageFilename = saveImage(imageFile, room);
            room.setImage(imageFilename);
        }
        room.setHotel(hotel);

        roomRepository.save(room);
    }
    private String saveImage(MultipartFile file, Room r) throws IOException {
        Path uploadPath = Paths.get(uploadDir + "/room");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = r.getRoomType() + "_" + UUID.randomUUID().toString();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public Room findById(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found by this Id"));
    }

    public List<Room> findRoomByHotelName(String hotelName) {
        return roomRepository.findRoomByHotelName(hotelName);
    }

    public List<Room> findRoomByHotelId(int hotelId) {
        return roomRepository.findRoomByHotelId(hotelId);
    }
}
