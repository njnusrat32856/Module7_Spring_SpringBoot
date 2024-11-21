package com.nusrat.onlineBanking.repository.adminpart;

import com.nusrat.onlineBanking.entities.adminPart.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    List<Settings> findById(long id);

}
