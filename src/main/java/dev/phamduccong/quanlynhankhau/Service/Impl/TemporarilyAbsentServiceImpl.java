package dev.phamduccong.quanlynhankhau.Service.Impl;

import dev.phamduccong.quanlynhankhau.Dto.TemporarilyAbsentDto;
import dev.phamduccong.quanlynhankhau.Entity.TemporarilyAbsent;
import dev.phamduccong.quanlynhankhau.Repository.TemporarilyAbsentRepository;
import dev.phamduccong.quanlynhankhau.Service.TemporarilyAbsentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporarilyAbsentServiceImpl implements TemporarilyAbsentService {

    private final TemporarilyAbsentRepository absentRepository;
    @Override
    public TemporarilyAbsent save(TemporarilyAbsentDto absentDto) {
        try {
            TemporarilyAbsent absent = new TemporarilyAbsent();
            absent.setPopulationCode(absentDto.getPopulationCode());
            absent.setPopulationName(absentDto.getPopulationName());
            absent.setDocumentCode(absentDto.getDocumentCode());
            absent.setCurrentAddress(absentDto.getCurrentAddress());
            absent.setFromDate(absentDto.getFromDate());
            absent.setReason(absentDto.getReason());
            absent.setNewAbsent(true);
            return absentRepository.save(absent);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public TemporarilyAbsent update(TemporarilyAbsent absent) {
        absent.setNewAbsent(false);
        return absentRepository.save(absent);
    }

    @Override
    public List<TemporarilyAbsent> selectById(Long id) {
        List<TemporarilyAbsent> absent =absentRepository.searchTemporarilyAbsentByPopulationCode(id);
        return absent;
    }

    @Override
    public List<TemporarilyAbsent> selectByNewAbsent(Long id) {
        return absentRepository.searchTemporarilyAbsentByPopulationCodeAndNewAbsent(id);
    }
}