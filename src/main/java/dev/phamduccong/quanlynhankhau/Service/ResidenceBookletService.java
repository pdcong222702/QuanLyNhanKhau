package dev.phamduccong.quanlynhankhau.Service;

import dev.phamduccong.quanlynhankhau.Dto.ResidenceBookletDto;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResidenceBookletService {
    ResidenceBooklet findByResidenceBookletCode(String code);
    List<ResidenceBooklet> getAllResidenceBooklet();
    void saveResidenceBooklet(MultipartFile image,ResidenceBookletDto residenceBookletDto);
    ResidenceBooklet save(ResidenceBooklet residenceBooklet);
    ResidenceBooklet findAllInforResidenceBookletById(Long id);
    ResidenceBooklet findResidenceBookletById(Long id);
    ResidenceBookletDto getResidenceBookletDtoById(Long id);
    ResidenceBooklet update(ResidenceBookletDto residenceBookletDto);
    ResidenceBooklet saveResidenceBookletSplit(ResidenceBookletDto residenceBookletDto);
    List<ResidenceBooklet> findAllResidenceBookletByAddress(String address);
    List<ResidenceBooklet> findAllResidenceBookletByCode(String code);
    ResidenceBooklet getResidenceBookletById(Long id);
}
