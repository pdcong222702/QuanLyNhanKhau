package dev.phamduccong.quanlynhankhau.Service.Impl;


import dev.phamduccong.quanlynhankhau.Dto.ResidenceBookletDto;
import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import dev.phamduccong.quanlynhankhau.Repository.ResidenceBookletRepository;
import dev.phamduccong.quanlynhankhau.Service.ResidenceBookletService;
import dev.phamduccong.quanlynhankhau.Utils.UploadImage;
import dev.phamduccong.quanlynhankhau.Repository.PopulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ResidenceBookletServiceImpl implements ResidenceBookletService {
    private final ResidenceBookletRepository residenceBookletRepository;
    private final PopulationRepository populationRepository;

    @Override
    public ResidenceBooklet findByResidenceBookletCode(String code) {
        return residenceBookletRepository.findByResidenceBookletCode(code);
    }

    @Override
    public List<ResidenceBooklet> getAllResidenceBooklet() {

        return residenceBookletRepository.findAll();
    }


    @Override
    public void saveResidenceBooklet(MultipartFile image, ResidenceBookletDto residenceBookletDto) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            ResidenceBooklet residenceBooklet = new ResidenceBooklet();
            Population population = new Population();

            residenceBooklet.setResidenceBookletCode(residenceBookletDto.getResidenceBookletCode());
            residenceBooklet.setBookletArea(residenceBookletDto.getBookletArea());
            residenceBooklet.setAddress(residenceBookletDto.getAddress());
            residenceBooklet.setCreateDate(dtf.format(now));
            residenceBookletRepository.save(residenceBooklet);
            UploadImage.upLoad(image);
            population.setPopulationName(residenceBookletDto.getPopulationName());
            population.setPopulationCode(residenceBookletDto.getPopulationCode());
            population.setJob(residenceBookletDto.getJob());

            //SimpleDateFormat dateFormat = new SimpleDateFormat();

            System.out.println(residenceBookletDto.getDob());
            population.setDob(residenceBookletDto.getDob());
            population.setGender(residenceBookletDto.getGender());
            population.setEthnicity(residenceBookletDto.getEthnicity());
            population.setReligion(residenceBookletDto.getReligion());
            population.setPlaceOfIssue(residenceBookletDto.getPlaceOfIssue());
            population.setDateOfIssue(residenceBookletDto.getDateOfIssue());
            population.setWorkPlace(residenceBookletDto.getWorkPlace());
            population.setRelationship("Chủ hộ");
            population.setBirthPlace(residenceBookletDto.getAddress());
            population.setResidenceBooklet(residenceBooklet);
            population.setCreateDate(dtf.format(now));
            population.setImage(residenceBookletDto.getImageName());
            population.setImageName(residenceBookletDto.getImageName());
            population.setAlive(true);
            population.setDead(false);
            populationRepository.save(population);
    }

    @Override
    public ResidenceBooklet save(ResidenceBooklet residenceBooklet) {
        return residenceBookletRepository.save(residenceBooklet);
    }

    @Override
    public ResidenceBooklet saveResidenceBookletSplit(ResidenceBookletDto residenceBookletDto) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        ResidenceBooklet residenceBooklet = new ResidenceBooklet();

        residenceBooklet.setResidenceBookletCode(residenceBookletDto.getResidenceBookletCode());
        residenceBooklet.setBookletArea(residenceBookletDto.getBookletArea());
        residenceBooklet.setAddress(residenceBookletDto.getAddress());
        residenceBooklet.setCreateDate(dtf.format(now));
        return residenceBookletRepository.save(residenceBooklet);
    }



    @Override
    public ResidenceBooklet findAllInforResidenceBookletById(Long id) {
        return residenceBookletRepository.findAllResidenceInforById(id);
    }

    @Override
    public ResidenceBooklet findResidenceBookletById(Long id) {
        return residenceBookletRepository.findResidenceBookletById(id);
    }

    @Override
    public ResidenceBookletDto getResidenceBookletDtoById(Long id) {
        ResidenceBooklet residenceBooklet = residenceBookletRepository.getReferenceById(id);
        ResidenceBookletDto residenceBookletDto = new ResidenceBookletDto();
        residenceBookletDto.setId(residenceBooklet.getId());
        residenceBookletDto.setBookletArea(residenceBooklet.getBookletArea());
        residenceBookletDto.setAddress(residenceBooklet.getAddress());
        residenceBookletDto.setMoveReason(residenceBooklet.getMoveReason());
        return residenceBookletDto;
    }

    @Override
    public ResidenceBooklet update(ResidenceBookletDto residenceBookletDto) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        ResidenceBooklet residenceBooklet = residenceBookletRepository.getReferenceById(residenceBookletDto.getId());
        residenceBooklet.setBookletArea(residenceBookletDto.getBookletArea());
        residenceBooklet.setMoveReason(residenceBookletDto.getMoveReason());
        residenceBooklet.setAddress(residenceBookletDto.getAddress());
        residenceBooklet.setMoveDate(dtf.format(now));
        return residenceBookletRepository.save(residenceBooklet);
    }

    @Override
    public List<ResidenceBooklet> findAllResidenceBookletByAddress(String address) {
        return residenceBookletRepository.findResidenceBookletByAddress(address);
    }

    @Override
    public List<ResidenceBooklet> findAllResidenceBookletByCode(String code) {
        return residenceBookletRepository.findResidenceBookletByResidenceBookletCode(code);
    }

    @Override
    public ResidenceBooklet getResidenceBookletById(Long id) {
        return residenceBookletRepository.getResidenceBookletById(id);
    }


    private Page toPage(List list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }


}
