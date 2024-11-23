package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.Employee;
import com.nusrat.onlineBanking.entities.sharedEntities.Branch;
import com.nusrat.onlineBanking.entities.sharedEntities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

//    List<Branch> findByBranchName(String branchName);
//
//    List<Branch> findByLocation(String location);
//
//    @Query("SELECT e FROM Employee e WHERE e.branch.id = :branchId")
//    List<Employee> findEmployeesByBranchId(@Param("branchId") Long branchId);
//
//    @Query("SELECT d FROM Department d WHERE d.branch.id = :branchId")
//    List<Department> findDepartmentsByBranchId(@Param("branchId") Long branchId);

}
