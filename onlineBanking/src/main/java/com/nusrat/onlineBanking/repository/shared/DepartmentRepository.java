package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.sharedEntities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

//    List<Department> findByDepartmentName(String departmentName);

//    List<Department> findByBranch(String branch);

    List<Department> findByBranchId(long branchId); // Find departments by branch ID
}
