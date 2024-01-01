package dev.phamduccong.quanlynhankhau.Service.Impl;


import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Repository.PopulationRepository;
import dev.phamduccong.quanlynhankhau.Repository.StaticPopulationRepository;
import dev.phamduccong.quanlynhankhau.Service.StaticPopulationService;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaticPopulationServiceImpl implements StaticPopulationService {
    private final StaticPopulationRepository populationRepository;
    @Override
    public List<Population> getPopulationByAddressOrCode(String search, String gioiTinh) {
        return populationRepository.findAll(new Specification<Population>(){
            @Override
            public Predicate toPredicate(Root<Population> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!search.isBlank() ) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("birthPlace"), "%" + search + "%"),
                            criteriaBuilder.like(root.get("populationCode"), "%" + search + "%")));
                }
                if (!gioiTinh.equals("null") ) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("sex"),gioiTinh)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public List<Population> getByDate(String ageBegin, String ageEnd) {
        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Định dạng thời gian
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int begin = Integer.parseInt(String.valueOf(currentTime.getYear())) - Integer.parseInt(ageBegin);
        int end = Integer.parseInt(String.valueOf(currentTime.getYear())) - Integer.parseInt(ageEnd);
        System.out.println(begin);
        System.out.println(end);
        return populationRepository.getByDate(String.valueOf(end),String.valueOf(begin));
    }

    @Override
    public Page<Population> pagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return populationRepository.findAll(pageable);
    }
}