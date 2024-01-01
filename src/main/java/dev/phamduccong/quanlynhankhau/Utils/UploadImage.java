package dev.phamduccong.quanlynhankhau.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class UploadImage {
    private static final String uploadFolder = "D:\\Haui\\MSCNPT\\BTL_MSCN_Final\\QuanLyNhanKhau\\QuanLyNhanKhau\\src\\main\\resources\\static\\images";
    public static boolean upLoad(MultipartFile image){
        boolean isUpload = false;
        try {
            Files.copy(image.getInputStream(),
                    Paths.get(uploadFolder + File.separator,image.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isUpload;
    }

}
