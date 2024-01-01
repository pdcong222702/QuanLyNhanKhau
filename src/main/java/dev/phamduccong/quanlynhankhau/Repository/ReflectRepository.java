package dev.phamduccong.quanlynhankhau.Repository;

import dev.phamduccong.quanlynhankhau.Entity.Reflect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReflectRepository extends JpaRepository<Reflect, Long> {
    // Tìm kiếm PhanAnh theo tình trạng
    List<Reflect> findByTrangThai(String trangThai);

}