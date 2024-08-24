package com.nusrat.FirstSpringBoot.service;


import com.nusrat.FirstSpringBoot.entity.Department;
import com.nusrat.FirstSpringBoot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public void saveDep(Department d) {
        departmentRepository.save(d);

    }

    public List<Department> getALLDep() {

        return departmentRepository.findAll();
    }

    public void deleteDepById(Integer id) {
        departmentRepository.deleteById(id);
    }

    public Department findById(Integer id) {

        return departmentRepository.findById(id).get();
    }

    public void updateDep(Department d) {
        departmentRepository.save(d);
    }
}
