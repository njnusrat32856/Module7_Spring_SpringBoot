package com.nusrat.onlineBanking.service.sharedService;

import com.nusrat.onlineBanking.entities.sharedEntities.Branch;
import com.nusrat.onlineBanking.entities.sharedEntities.Department;
import com.nusrat.onlineBanking.repository.shared.BranchRepository;
import com.nusrat.onlineBanking.repository.shared.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BranchRepository branchRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
    }

    public List<Department> getDepartmentsByBranchId(Long branchId) {
        return departmentRepository.findByBranchId(branchId);
    }

    public Department createDepartment(Long branchId, Department department) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found with ID: " + branchId));

        department.setBranch(branch);
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = getDepartmentById(id);
        department.setDepartmentName(departmentDetails.getDepartmentName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with ID: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
