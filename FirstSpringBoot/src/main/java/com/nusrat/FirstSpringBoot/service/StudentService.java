package com.nusrat.FirstSpringBoot.service;

import com.nusrat.FirstSpringBoot.entity.Department;
import com.nusrat.FirstSpringBoot.entity.Student;
import com.nusrat.FirstSpringBoot.repository.DepartmentRepository;
import com.nusrat.FirstSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public void  saveStu(Student s) {

        Department d = departmentRepository.findById(s.getDepartment()
                .getId())
                        .orElseThrow(
                                () -> new RuntimeException("Student not saved")
                        );

        s.setDepartment(d);


        studentRepository.save(s);
    }

    public List<Student> getAllStu() {

        return studentRepository.findAll();

    }
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    public  void updateStudent(Student s, int id) {
        studentRepository.save(s);
    }

}
