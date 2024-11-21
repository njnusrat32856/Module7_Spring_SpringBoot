package com.nusrat.onlineBanking.repository.adminpart;

import com.nusrat.onlineBanking.entities.adminPart.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    List<Settings> findById(long id);

//    List<Settings> findByKey(String key);

    Optional<Settings> findByKey(String key);

    @Query("SELECT s FROM Settings s WHERE s.editable = true")
    List<Settings> findAllEditableSettings();

}
