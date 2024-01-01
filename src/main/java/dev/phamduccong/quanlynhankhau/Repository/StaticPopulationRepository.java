package dev.phamduccong.quanlynhankhau.Repository;

import dev.phamduccong.quanlynhankhau.Entity.Population;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StaticPopulationRepository extends JpaRepository<Population,Long> {
    @Query(value = "SELECT a FROM Population a  WHERE a.dob between ?1 and ?2",nativeQuery = false)
    List<Population> getByDate(String dateBegin,String dateEnd);

    List<Population> findAll(Specification<Population> specification);
}
