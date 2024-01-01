package dev.phamduccong.quanlynhankhau.Service;

import dev.phamduccong.quanlynhankhau.Dto.TemporarilyAbsentDto;
import dev.phamduccong.quanlynhankhau.Entity.TemporarilyAbsent;

import java.util.List;

public interface TemporarilyAbsentService {
    TemporarilyAbsent save(TemporarilyAbsentDto absentDto);
    //Thay đổi
    TemporarilyAbsent update(TemporarilyAbsent absent);
    //Tìm kiếm theo id
    List<TemporarilyAbsent> selectById(Long id);
    //Tìm kiếm New Absent = true
    List<TemporarilyAbsent> selectByNewAbsent(Long id);
}
