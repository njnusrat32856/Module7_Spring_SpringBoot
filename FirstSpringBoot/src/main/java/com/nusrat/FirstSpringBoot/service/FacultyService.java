package com.nusrat.FirstSpringBoot.service;

import com.nusrat.FirstSpringBoot.entity.Department;
import com.nusrat.FirstSpringBoot.entity.Faculty;
import com.nusrat.FirstSpringBoot.repository.DepartmentRepository;
import com.nusrat.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public void saveFaculty(Faculty f) {

        Department newDep = departmentRepository.findById(f.getDepartment()
                .getId())
                .get();

        f.setDepartment(newDep);

        facultyRepository.save(f);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public void deleteFacultyById(int id) {
        facultyRepository.deleteById(id);
    }

    public Faculty findById(int id) {
        return facultyRepository.findById(id).get();
    }

    public void updateFaculty(Faculty f) {

        facultyRepository.save(f);
    }
}
