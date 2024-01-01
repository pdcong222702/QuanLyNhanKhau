package dev.phamduccong.quanlynhankhau.Repository;


import dev.phamduccong.quanlynhankhau.Entity.TemporarilyStaying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporarilyStayingRepository extends JpaRepository<TemporarilyStaying,Long> {
    List<TemporarilyStaying> findTemporarilyStayingByPopulationCode(Long id);
    @Query("SELECT s FROM TemporarilyStaying s WHERE s.isNewStay=true")
    List<TemporarilyStaying> searchTemporarilyStayingByPopulationCodeAndNewStay(Long id);
}
