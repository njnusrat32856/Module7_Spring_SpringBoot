package com.nusrat.FirstSpringBoot.restcontroller;

import com.nusrat.FirstSpringBoot.entity.Faculty;
import com.nusrat.FirstSpringBoot.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    public List<Faculty> getAllFaculty(){
        return facultyService.getAllFaculty();
    }

    @PostMapping("/save")
    public void saveFaculty(@RequestBody Faculty f){
        facultyService.saveFaculty(f);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteFaculty(@PathVariable("id") int id){

        facultyService.deleteFacultyById(id);
    }

    public void updateFaculty(@RequestBody Faculty f){
        facultyService.updateFaculty(f);
    }
}
