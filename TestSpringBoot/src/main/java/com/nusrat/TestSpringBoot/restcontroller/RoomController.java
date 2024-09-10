package com.nusrat.TestSpringBoot.restcontroller;

import com.nusrat.TestSpringBoot.entity.Hotel;
import com.nusrat.TestSpringBoot.entity.Room;
import com.nusrat.TestSpringBoot.service.HotelService;
import com.nusrat.TestSpringBoot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @PostMapping("/save")
    public ResponseEntity<String> saveRoom(
            @RequestPart(value = "room") Room room,
            @RequestParam(value = "image", required = true) MultipartFile file
            ) throws IOException {
        roomService.saveRoom(room,file);
        return  new ResponseEntity<>("Hotel added successfully with image", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable int id) {
        Room room = roomService.findById(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/r/searchroom")
    public ResponseEntity<List<Room>> findRoomByHotelName(
            @RequestParam("hotelName") String hotelName) {
        List<Room> rooms = roomService.findRoomByHotelName(hotelName);
        return ResponseEntity.ok(rooms);
    }

    public ResponseEntity<List<Room>> findRoomByHotelId(@RequestParam("hotelid") int hotelId) {
        List<Room> rooms = roomService.findRoomByHotelId(hotelId);
        return ResponseEntity.ok(rooms);
    }

}
