package com.nusrat.onlineBanking.repository;

import com.nusrat.onlineBanking.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByBranchId(String branchId);

    List<Employee> findByEmployeeName(String employeeName);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment(String department);
}
