package com.nusrat.FirstSpringBoot.restcontroller;


import com.nusrat.FirstSpringBoot.entity.Department;
import com.nusrat.FirstSpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep")
public class DepartmentRestController {

    @Autowired
    public DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDep() {
        return departmentService.getALLDep();
    }

    @PostMapping("/save")
    public void saveDep(@RequestBody Department d) {

        departmentService.saveDep(d);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDep(@PathVariable("id") int id) {

        departmentService.deleteDepById(id);
    }

    @PutMapping("/update")
    public void updateDep(@RequestBody Department d) {
        departmentService.updateDep(d);
    }

}
