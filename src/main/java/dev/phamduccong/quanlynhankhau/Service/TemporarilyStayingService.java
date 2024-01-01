package dev.phamduccong.quanlynhankhau.Service;

import dev.phamduccong.quanlynhankhau.Entity.TemporarilyStaying;
import dev.phamduccong.quanlynhankhau.Dto.TemporarilyStayingDto;

import java.util.List;

public interface TemporarilyStayingService {
    //Lưu
    TemporarilyStaying save(TemporarilyStayingDto stayingDto);
    //cập nhật
    TemporarilyStaying update(TemporarilyStaying staying);

    List<TemporarilyStaying> selectById(Long id);
    //Tìm kiếm New Absent = true
    List<TemporarilyStaying> selectByNewAbsent(Long id);
}
