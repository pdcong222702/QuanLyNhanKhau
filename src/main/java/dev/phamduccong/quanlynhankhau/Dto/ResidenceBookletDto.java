package dev.phamduccong.quanlynhankhau.Dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceBookletDto {
    private Long id;
    private String residenceBookletCode;
    private String bookletArea;

    private String populationName;

    private String populationCode;


    private String imageName;
    private MultipartFile image;

    private String address;
    private String createDate;
    private String religion;
    private String ethnicity;

    private String job;
    private String dateOfIssue;
    private String placeOfIssue;

    private String dob;
    private String gender;
    private String workPlace;

    private String moveReason;
}
