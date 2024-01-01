package dev.phamduccong.quanlynhankhau.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@Table(name = "populations", uniqueConstraints = @UniqueConstraint(columnNames = {"population_code", "image"}))
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "population_id")
    private Long id;


    @Column(name = "population_code", length = 12)
    private String populationCode;

    // tên nhân khẩu
    private String populationName;

    private String gender;

    private String dob;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;
    private String imageName;

    // Biệt danh nhân khẩu
    private String populationNickName;


    // Nơi sinh nhân khẩu
    private String birthPlace;


    // dân tộc nhân khẩu
    @Column(name="ethnicity")
    private String ethnicity;

    private boolean isChoosen = false;

    // Tôn giáo nhân khẩu

    @Column(name="religion")
    private String religion;

    // Nghề nghiệp
    private String job;

    // Quan he
    private String relationship;


    // Ngay cap
    private String dateOfIssue;

    private boolean alive;

    //Trang thai da chet
    private boolean dead;

    //Noi cap
    private String placeOfIssue;

    // Nơi làm việc
    private String workPlace;


    // Ngày vào sổ
    private String createDate;
    private boolean isChosen = false;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residence_booklet_id")
    private ResidenceBooklet residenceBooklet;

    @OneToMany
    @JoinColumn(name = "population_id")
    private Collection<TemporarilyAbsent> temporarilyAbsents;

    @OneToMany
    @JoinColumn(name = "population_id")
    private Collection<TemporarilyStaying> temporarilyStayings;

//    @OneToMany
//    @JoinColumn(name = "population_id")
//    private Collection<Reflect> reflects;
}
