package com.nusrat.TestSpringBoot.repository;

import com.nusrat.TestSpringBoot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
