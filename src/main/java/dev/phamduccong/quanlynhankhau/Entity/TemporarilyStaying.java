package dev.phamduccong.quanlynhankhau.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Tạm trú

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporarilyStaying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "population_id")
    // mã nhân khẩu
    private Long populationCode;

    // địa chỉ thường trú
    @Column(name = "address")
    private String address;

    @Column(name = "population_name")
    private String populationName;

    // mã giấy tạm trú
    @Column(nullable = false, unique = true)
    private String documentCode;

    //ngày sinh
    @Column(name = "date_of_birth")
    private String dob;

    // số điện thoại đăng kí
    private String numberPhone;

    // từ ngày
    @Column(nullable = false)
    private String fromDate;

    //địa chỉ tạm trú
    @Column(name = "absent_address")
    private String stayingAddress;


    //Trạng thái
    private boolean isNewStay;


    private String reason;


    @ManyToOne
    @JoinColumn(name = "population_id", insertable = false, updatable = false)
    private Population population;
}
