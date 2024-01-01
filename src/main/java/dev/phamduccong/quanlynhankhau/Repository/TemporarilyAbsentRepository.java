package dev.phamduccong.quanlynhankhau.Repository;

import dev.phamduccong.quanlynhankhau.Entity.TemporarilyAbsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporarilyAbsentRepository extends JpaRepository<TemporarilyAbsent,Long> {
    TemporarilyAbsent findAllById(Long id);
    List<TemporarilyAbsent> searchTemporarilyAbsentByPopulationCode(Long id);

    @Query("SELECT a FROM TemporarilyAbsent a where a.isNewAbsent=true")
    List<TemporarilyAbsent> searchTemporarilyAbsentByPopulationCodeAndNewAbsent(Long id);
}