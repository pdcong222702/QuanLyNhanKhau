package dev.phamduccong.quanlynhankhau.Dto;

import java.util.List;

public class UpdateReflectStatusRequest {
    private List<Long> reflectIds;
    private String status;
    public List<Long> getReflectIds() {
        return reflectIds;
    }
    public void setReflectIds(List<Long> reflectIds) {
        this.reflectIds = reflectIds;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}

