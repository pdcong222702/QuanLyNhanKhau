package dev.phamduccong.quanlynhankhau.Repository;

import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository  extends JpaRepository<Population,Long> {
    @Query("SELECT p FROM Population p WHERE p.residenceBooklet = :residenceBooklet AND p.relationship = 'Chủ hộ'")
    Population findByResidenceIdAndRelationship(ResidenceBooklet residenceBooklet);

    @Query("SELECT p FROM Population p WHERE p.residenceBooklet = :residenceBooklet")
    List<Population> findAllPopulationByResidenceBookletId(ResidenceBooklet residenceBooklet);
    @Query("SELECT p FROM Population p WHERE p.residenceBooklet = :residenceBooklet AND p.relationship <> 'Chủ hộ'")
    List<Population> findPopulationIsNotMaster(ResidenceBooklet residenceBooklet);

    List<Population> findPopulationsByResidenceBooklet(ResidenceBooklet residenceBooklet);
    boolean existsByPopulationCode(String id);

    List<Population> findPopulationByResidenceBooklet(ResidenceBooklet residenceBooklet);

}
