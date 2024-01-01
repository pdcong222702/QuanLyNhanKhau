package dev.phamduccong.quanlynhankhau.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Tạm vắng
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemporarilyAbsent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "population_id")
    // mã nhân khẩu
    private Long populationCode;

    // mã giấy tạm vắng
    @Column(name = "document_code", unique = true, nullable = false)
    private String documentCode;


    @Column(name = "population_name")
    private String populationName;

    // địa chỉ tạm trú
    @Column(nullable = false)
    private String currentAddress;

    // từ ngày
    @Column(nullable = false)
    private String fromDate;

    private boolean isNewAbsent;
    // lý do
    @Column(nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "population_id", insertable = false, updatable = false)
    private Population population;
}
