package dev.phamduccong.quanlynhankhau.Service;

import dev.phamduccong.quanlynhankhau.Dto.PopulationDto;
import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PopulationService {
    Population findByResidenceIdAndRelationship(ResidenceBooklet residenceBooklet);
    List<Population> findAllPopulationByResidenceBooklet(ResidenceBooklet residenceBooklet);
    List<Population> findPopulationIsNotMaster(ResidenceBooklet residenceBooklet);
    List<Population> selectAll();
    //Lưu
    Population save(MultipartFile multipartFile, PopulationDto population);
    //Sửa
    Population update(PopulationDto populationDto);
    //Trạng thái còn sống
    void alive(Long id);
    //Trạng thái đã chết
    void dead(Long id);
    //Tìm theo id
    PopulationDto getById(Long id);
    List<Population> getAllPopulationByResidenceBookletId(ResidenceBooklet residenceBooklet);
    //Tìm mã cccd
    boolean findByPopulationCode(String id);

    // get all populattion by residence booklet
    List<Population> getAllPopulationByResidenceBooklet(ResidenceBooklet residenceBooklet);
    Population changeResidenceBookletId(PopulationDto populationDto);






}
