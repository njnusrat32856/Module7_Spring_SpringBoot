package com.nusrat.TestSpringBoot.service;

import com.nusrat.TestSpringBoot.entity.Hotel;
import com.nusrat.TestSpringBoot.entity.Location;
import com.nusrat.TestSpringBoot.repository.HotelRepository;
import com.nusrat.TestSpringBoot.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Value("src/main/resources/static/images")
    private String uploadDir;

    public List<Hotel> getAllHotel() {

        return hotelRepository.findAll();
    }

    @Transactional
    public void saveHotel(Hotel hotel, MultipartFile imageFile) throws IOException {

        Location location = locationRepository.findById(hotel.getLocation().getId())
                .orElseThrow(() -> new RuntimeException("Location with this Id not Found"));

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageFileName = saveImage(imageFile,hotel);
            hotel.setImage(imageFileName);
        }

        hotel.setLocation(location);

        hotelRepository.save(hotel);
    }

    public void deleteHotelById(int id) {
        hotelRepository.deleteById(id);
    }

    public Hotel findHotelById(int id) {

        return hotelRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Hotel not Found with this ID")
                );
    }

    private String saveImage(MultipartFile file, Hotel h) throws IOException {

        Path uploadPath = Paths.get(uploadDir + "/hotel");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        //generate a unique filename
        String filename = h.getName()+"_"+ UUID.randomUUID().toString();
        Path filePath = uploadPath.resolve(filename);

        //save the file
        Files.copy(file.getInputStream(), filePath);

        return filename;
    }

    public List<Hotel> findHotelByLocationName(String locationName) {
        return hotelRepository.findHotelByLocationName(locationName);
    }

}
