package dev.phamduccong.quanlynhankhau.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@Table(name = "phan_anh")
@AllArgsConstructor
@NoArgsConstructor
public class Reflect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "population_id")
    private Long id;

    @Column(name = "hoTen", nullable = false)
    private String hoTen;

    @Column(name = "noiDungPhanAnh", nullable = false)
    private String noiDungPhanAnh;

    @Column(name = "trangThai", nullable = false)
    private String trangThai;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @ManyToOne
//    @JoinColumn(name = "population_id", insertable = false, updatable = false)
//    private Population population;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNoiDungPhanAnh() {
        return noiDungPhanAnh;
    }

    public void setNoiDungPhanAnh(String noiDungPhanAnh) {
        this.noiDungPhanAnh = noiDungPhanAnh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
