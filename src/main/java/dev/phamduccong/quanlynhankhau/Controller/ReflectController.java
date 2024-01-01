package dev.phamduccong.quanlynhankhau.Controller;

import dev.phamduccong.quanlynhankhau.Dto.UpdateReflectStatusRequest;
import dev.phamduccong.quanlynhankhau.Entity.Reflect;
import dev.phamduccong.quanlynhankhau.Service.ReflectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reflect")
public class ReflectController {

    private final ReflectService reflectService;

    @Autowired
    public ReflectController(ReflectService reflectService) {
        this.reflectService = reflectService;
    }

    @GetMapping
    public String showReflectPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "12") int size,
                                  Model model) {
        try {
            Page<Reflect> reflectPage = reflectService.getReflects(PageRequest.of(page, size));

            if (reflectPage != null) {
                model.addAttribute("reflectPage", reflectPage);
                model.addAttribute("currentPage", page);
                model.addAttribute("pageSize", size);
            } else {
                model.addAttribute("error", "Error retrieving reflects from the database.");
                return "error";
            }

            return "reflect";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/api")
    public ResponseEntity<String> addReflect(@RequestBody Reflect reflect) {
        try {
            reflectService.addReflect(reflect);
            return ResponseEntity.ok("Phản ánh đã được thêm mới thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi thêm mới phản ánh.");
        }
    }

    @PostMapping("/api/update-reflects")
    public ResponseEntity<String> updateReflectsStatus(@RequestBody UpdateReflectStatusRequest request) {
        try {
            List<Long> reflectIds = request.getReflectIds();
            String status = request.getStatus();

            List<Reflect> reflectsToUpdate = reflectIds.stream()
                    .map(reflectService::getReflectById)
                    .filter(reflect -> reflect != null && !reflect.getTrangThai().equals("Đã giải quyết"))
                    .collect(Collectors.toList());

            for (Reflect reflect : reflectsToUpdate) {
                reflect.setTrangThai(status);
                reflectService.updateReflect(reflect);
            }

            return ResponseEntity.ok("Cập nhật trạng thái thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi khi cập nhật trạng thái.");
        }
    }

}

