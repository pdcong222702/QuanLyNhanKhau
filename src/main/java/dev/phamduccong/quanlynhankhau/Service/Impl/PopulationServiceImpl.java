package dev.phamduccong.quanlynhankhau.Service.Impl;

import dev.phamduccong.quanlynhankhau.Dto.PopulationDto;
import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import dev.phamduccong.quanlynhankhau.Repository.PopulationRepository;
import dev.phamduccong.quanlynhankhau.Service.PopulationService;
import dev.phamduccong.quanlynhankhau.Utils.UploadImage;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PopulationServiceImpl implements PopulationService {
    private final PopulationRepository populationRepository;


    @Override
    public Population findByResidenceIdAndRelationship(ResidenceBooklet residenceBooklet) {
        return populationRepository.findByResidenceIdAndRelationship(residenceBooklet);
    }

    @Override
    public List<Population> findAllPopulationByResidenceBooklet(ResidenceBooklet residenceBooklet) {
        return populationRepository.findPopulationIsNotMaster(residenceBooklet);
    }


    @Override
    public List<Population> findPopulationIsNotMaster(ResidenceBooklet residenceBooklet) {
        return populationRepository.findPopulationIsNotMaster(residenceBooklet);
    }

    @Override
    public List<Population> selectAll() {
        return populationRepository.findAll();
    }

    @Override
    public Population save(MultipartFile multipartFile, PopulationDto population) {
        try {
            Population populationSave = new Population();
            populationSave.setPopulationCode(population.getPopulationCode());
            populationSave.setResidenceBooklet(population.getResidenceBooklet());
            populationSave.setPopulationName(population.getPopulationName());
            populationSave.setPopulationNickName(population.getPopulationNickName());
            UploadImage.upLoad(multipartFile);
            populationSave.setImage(population.getImageName());
            populationSave.setImageName(population.getImageName());
            populationSave.setDob(population.getDob());
            populationSave.setGender(population.getGender());
            populationSave.setBirthPlace(population.getBirthPlace());
            populationSave.setEthnicity(population.getEthnicity());
            populationSave.setReligion(population.getReligion());
            populationSave.setJob(population.getJob());
            populationSave.setRelationship(population.getRelationship());
            populationSave.setDateOfIssue(population.getDateOfIssue());
            populationSave.setPlaceOfIssue(population.getPlaceOfIssue());
            populationSave.setDead(false);
            populationSave.setAlive(true);
            populationSave.setWorkPlace(population.getWorkPlace());
            populationSave.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd"))).toString());
            return populationRepository.save(populationSave);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Population update(PopulationDto populationDto) {
        try{
            Population populationUpdate = populationRepository.getReferenceById(populationDto.getId());
            populationUpdate.setPopulationCode(populationDto.getPopulationCode());
            populationUpdate.setPopulationName(populationDto.getPopulationName());
            populationUpdate.setPopulationNickName(populationDto.getPopulationNickName());
            populationUpdate.setDob(populationDto.getDob());
            populationUpdate.setGender(populationDto.getGender());
            populationUpdate.setBirthPlace(populationDto.getBirthPlace());
            populationUpdate.setEthnicity(populationDto.getEthnicity());
            populationUpdate.setReligion(populationDto.getReligion());
            populationUpdate.setJob(populationDto.getJob());
            populationUpdate.setRelationship(populationDto.getRelationship());
            populationUpdate.setDateOfIssue(populationDto.getDateOfIssue());
            populationUpdate.setPlaceOfIssue(populationDto.getPlaceOfIssue());
            populationUpdate.setWorkPlace(populationDto.getWorkPlace());
            populationUpdate.setCreateDate(populationDto.getCreateDate());
            return populationRepository.save(populationUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void alive(Long id) {
        Population population =populationRepository.getReferenceById(id);
        population.setAlive(true);
        population.setDead(false);
        populationRepository.save(population);
    }

    @Override
    public void dead(Long id) {
        Population population =populationRepository.getReferenceById(id);
        population.setAlive(false);
        population.setDead(true);
        populationRepository.save(population);
    }

    @Override
    public PopulationDto getById(Long id) {
        Population populationgetId = populationRepository.getReferenceById(id);
        PopulationDto population = new PopulationDto();
        population.setId(populationgetId.getId());
        population.setPopulationCode(populationgetId.getPopulationCode());
        population.setPopulationName(populationgetId.getPopulationName());
        population.setPopulationNickName(populationgetId.getPopulationNickName());
        population.setDob(populationgetId.getDob());
        population.setGender(populationgetId.getGender());
        population.setBirthPlace(populationgetId.getBirthPlace());
        population.setEthnicity(populationgetId.getEthnicity());
        population.setReligion(populationgetId.getReligion());
        population.setJob(populationgetId.getJob());
        population.setRelationship(populationgetId.getRelationship());
        population.setDateOfIssue(populationgetId.getDateOfIssue());
        population.setPlaceOfIssue(populationgetId.getPlaceOfIssue());
        population.setWorkPlace(populationgetId.getWorkPlace());
        population.setCreateDate(populationgetId.getCreateDate());
        population.setResidenceBookletCode(population.getPopulationCode());
        return population;
    }

    @Override
    public List<Population> getAllPopulationByResidenceBookletId(ResidenceBooklet residenceBooklet) {
        return populationRepository.findAllPopulationByResidenceBookletId(residenceBooklet);
    }

    @Override
    public boolean findByPopulationCode(String id) {
        return populationRepository.existsByPopulationCode(id);
    }

    @Override
    public List<Population> getAllPopulationByResidenceBooklet(ResidenceBooklet residenceBooklet) {
        return populationRepository.findPopulationsByResidenceBooklet(residenceBooklet);
    }

    @Override
    public Population changeResidenceBookletId(PopulationDto populationDto) {
        Population population = populationRepository.getReferenceById(populationDto.getId());
        population.setResidenceBooklet(populationDto.getResidenceBooklet());
        population.setPopulationCode(populationDto.getPopulationCode());
        population.setPopulationName(populationDto.getPopulationName());
        population.setPopulationNickName(populationDto.getPopulationNickName());
        population.setDob(populationDto.getDob());
        population.setGender(populationDto.getGender());
        population.setBirthPlace(populationDto.getBirthPlace());
        population.setEthnicity(populationDto.getEthnicity());
        population.setReligion(populationDto.getReligion());
        population.setJob(populationDto.getJob());
        population.setRelationship(populationDto.getRelationship());
        population.setDateOfIssue(populationDto.getDateOfIssue());
        population.setPlaceOfIssue(populationDto.getPlaceOfIssue());
        population.setWorkPlace(populationDto.getWorkPlace());
        population.setCreateDate(populationDto.getCreateDate());
        population.setRelationship(populationDto.getRelationship());
        populationRepository.save(population);
        return population;
    }
}
