package dev.phamduccong.quanlynhankhau.Dto;

import dev.phamduccong.quanlynhankhau.Entity.TemporarilyAbsent;
import dev.phamduccong.quanlynhankhau.Entity.TemporarilyStaying;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopulationDto {

    private Long id;

    //Số cccd
    private String populationCode;

    // tên nhân khẩu
    private String populationName;

    private String imageName;
    private MultipartFile image;

    // Biệt danh nhân khẩu
    private String populationNickName;

    //Ngày sinh nhân khẩu
    private String dob;

    //Giới tính nhân khẩu
    private String gender;

    // Nơi sinh nhân khẩu
    private String birthPlace;

    // dân tộc nhân khẩu
    private String ethnicity;

    // Tôn giáo nhân khẩu
    private String religion;

    // Nghề nghiệp
    private String job;

    // Quan he
    private String relationship;


    // Ngay cap
    private String dateOfIssue;


    //Noi cap
    private String placeOfIssue;

    // Nơi làm việc
    private String workPlace;


    // Ngày vào sổ
    private String createDate;
    private boolean isChosen = false;

    private String residenceBookletCode;

    private ResidenceBooklet residenceBooklet;

    private Collection<TemporarilyAbsent> temporarilyAbsents;

    private Collection<TemporarilyStaying> temporarilyStayings;
}
