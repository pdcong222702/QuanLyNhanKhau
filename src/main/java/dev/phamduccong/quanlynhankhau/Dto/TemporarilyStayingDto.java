package dev.phamduccong.quanlynhankhau.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporarilyStayingDto {
    private Integer id;
    //Tên
    private String populationName;
    //ngày sinh
    private String dob;
    // mã nhân khẩu
    private Long populationCode;
    //địa chỉ thường trú
    private String address;
    // mã giấy tạm trú
    private String documentCode;
    // số điện thoại đăng kí
    private String numberPhone;
    //địa chỉ tạm trú
    private String stayingAddress;
    // từ ngày
    private String fromDate;
    // lý do
    private String reason;
}
