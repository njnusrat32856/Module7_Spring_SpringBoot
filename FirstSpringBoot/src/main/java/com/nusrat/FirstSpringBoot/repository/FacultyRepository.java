package com.nusrat.FirstSpringBoot.repository;

import com.nusrat.FirstSpringBoot.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {


}
