package dev.phamduccong.quanlynhankhau.Service;

import dev.phamduccong.quanlynhankhau.Entity.Reflect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReflectService {
    List<Reflect> getAllReflects();

    void addReflect(Reflect reflect);

    Page<Reflect> getReflects(Pageable pageable);

    void updateReflect(Reflect reflect);

    Reflect getReflectById(Long id);
}
