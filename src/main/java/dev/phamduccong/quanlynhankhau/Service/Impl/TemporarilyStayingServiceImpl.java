package dev.phamduccong.quanlynhankhau.Service.Impl;

import dev.phamduccong.quanlynhankhau.Dto.TemporarilyStayingDto;
import dev.phamduccong.quanlynhankhau.Entity.TemporarilyStaying;
import dev.phamduccong.quanlynhankhau.Repository.TemporarilyStayingRepository;
import dev.phamduccong.quanlynhankhau.Service.TemporarilyStayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporarilyStayingServiceImpl implements TemporarilyStayingService {
    private final TemporarilyStayingRepository stayingRepository;
    @Override
    public TemporarilyStaying save(TemporarilyStayingDto stayingDto) {
        TemporarilyStaying staying = new TemporarilyStaying();
        staying.setPopulationName(stayingDto.getPopulationName());
        staying.setDob(stayingDto.getDob());
        staying.setPopulationCode(stayingDto.getPopulationCode());
        staying.setAddress(stayingDto.getAddress());
        staying.setDocumentCode(stayingDto.getDocumentCode());
        staying.setNumberPhone(stayingDto.getNumberPhone());
        staying.setStayingAddress(stayingDto.getStayingAddress());
        staying.setFromDate(stayingDto.getFromDate());
        staying.setReason(stayingDto.getReason());
        staying.setNewStay(true);
        return stayingRepository.save(staying);
    }

    @Override
    public TemporarilyStaying update(TemporarilyStaying staying) {
        staying.setNewStay(false);
        return stayingRepository.save(staying);
    }

    @Override
    public List<TemporarilyStaying> selectById(Long id) {
        List<TemporarilyStaying> stayings = stayingRepository.findTemporarilyStayingByPopulationCode(id);
        return stayings;
    }

    @Override
    public List<TemporarilyStaying> selectByNewAbsent(Long id) {
        return stayingRepository.searchTemporarilyStayingByPopulationCodeAndNewStay(id);
    }
}
