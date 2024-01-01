package dev.phamduccong.quanlynhankhau.Repository;


import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenceBookletRepository extends JpaRepository<ResidenceBooklet, Long> {
    ResidenceBooklet findByResidenceBookletCode(String code);

    @Query("SELECT r.id, r.residenceBookletCode, r.bookletArea FROM ResidenceBooklet r WHERE r.id = :id")
    ResidenceBooklet findResidenceBookletById(Long id);

    @Query("SELECT r FROM ResidenceBooklet r WHERE r.id = :id")
    ResidenceBooklet getResidenceBookletById(Long id);
    ResidenceBooklet findAllResidenceInforById(Long id);

    @Query("SELECT r.id, r.residenceBookletCode, r.bookletArea FROM ResidenceBooklet r WHERE r.address = :address")
    List<ResidenceBooklet> findAllResidenceBookletByAddress(String address);

    @Query("SELECT r FROM ResidenceBooklet r WHERE r.address = :address")
    List<ResidenceBooklet> findResidenceBookletByAddress(String address);

    List<ResidenceBooklet> findResidenceBookletByResidenceBookletCode(String code);
}
