package dev.phamduccong.quanlynhankhau.Service;

import dev.phamduccong.quanlynhankhau.Entity.Population;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StaticPopulationService {
    List<Population> getPopulationByAddressOrCode(String search, String gioiTinh);

    List<Population> getByDate(String ageBegin,String ageEnd);

    Page<Population> pagination(Integer page, Integer size);
}
