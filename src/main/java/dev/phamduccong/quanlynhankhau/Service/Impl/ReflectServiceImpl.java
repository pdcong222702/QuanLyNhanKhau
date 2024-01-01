package dev.phamduccong.quanlynhankhau.Service.Impl;

import dev.phamduccong.quanlynhankhau.Entity.Reflect;
import dev.phamduccong.quanlynhankhau.Repository.ReflectRepository;
import dev.phamduccong.quanlynhankhau.Service.ReflectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReflectServiceImpl implements ReflectService {

    private final ReflectRepository reflectRepository;

    @Autowired
    public ReflectServiceImpl(ReflectRepository reflectRepository) {
        this.reflectRepository = reflectRepository;
    }

    @Override
    public List<Reflect> getAllReflects() {
        try {
            return reflectRepository.findAll();
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addReflect(Reflect reflect) {
        reflectRepository.save(reflect);
    }

    @Override
    public Page<Reflect> getReflects(Pageable pageable) {
        try {
            return reflectRepository.findAll(pageable);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            throw new RuntimeException("Error getting reflects with paging: " + e.getMessage());
        }
    }

    @Override
    public void updateReflect(Reflect reflect) {
        reflectRepository.save(reflect);
    }

    @Override
    public Reflect getReflectById(Long id) {
        return reflectRepository.findById(id).orElse(null);
    }
}

