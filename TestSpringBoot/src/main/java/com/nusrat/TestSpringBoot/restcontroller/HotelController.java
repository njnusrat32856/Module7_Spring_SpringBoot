package com.nusrat.TestSpringBoot.restcontroller;

import com.nusrat.TestSpringBoot.entity.Hotel;
import com.nusrat.TestSpringBoot.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/save")
    public ResponseEntity<String> saveHotel(
            @RequestPart(value = "hotel") Hotel hotel,
            @RequestParam(value = "image", required = true) MultipartFile file
    ) throws IOException {

        hotelService.saveHotel(hotel, file);

        return new ResponseEntity<>("Hotel added successfully with image.", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotel() {
        List<Hotel> hotels = hotelService.getAllHotel();
        return ResponseEntity.ok(hotels);
    }

//    public ResponseEntity<List<Hotel>> findHotelByLocation(
//            @RequestParam(value = "locationName") String locationName
//    ) {
//        List<Hotel> hotels = hotelService.findHotelByLocation(locationName);
//        return ResponseEntity.ok(hotels);
//    }

}
