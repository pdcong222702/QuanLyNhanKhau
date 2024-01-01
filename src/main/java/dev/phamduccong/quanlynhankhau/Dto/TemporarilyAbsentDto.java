package dev.phamduccong.quanlynhankhau.Dto;

import dev.phamduccong.quanlynhankhau.Entity.Population;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporarilyAbsentDto {
    private Integer id;

    // mã nhân khẩu
    private Long populationCode;

    //tên nhân khẩu
    private String populationName;

    // mã giấy tạm vắng
    private String documentCode;

    // địa chỉ tạm trú
    private String currentAddress;

    // từ ngày
    private String fromDate;

    // đến ngày
    private Date toDate;

    // lý do
    private String reason;

    private Population population;
}