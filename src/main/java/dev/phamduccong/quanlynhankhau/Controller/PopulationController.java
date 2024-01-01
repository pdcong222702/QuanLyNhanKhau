package dev.phamduccong.quanlynhankhau.Controller;

import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Entity.TemporarilyStaying;
import dev.phamduccong.quanlynhankhau.Dto.PopulationDto;
import dev.phamduccong.quanlynhankhau.Dto.TemporarilyAbsentDto;
import dev.phamduccong.quanlynhankhau.Dto.TemporarilyStayingDto;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import dev.phamduccong.quanlynhankhau.Entity.TemporarilyAbsent;
import dev.phamduccong.quanlynhankhau.Service.PopulationService;
import dev.phamduccong.quanlynhankhau.Service.ResidenceBookletService;
import dev.phamduccong.quanlynhankhau.Service.TemporarilyAbsentService;
import dev.phamduccong.quanlynhankhau.Service.TemporarilyStayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PopulationController {
    private final PopulationService populationService;
    private final ResidenceBookletService residenceBookletService;
    private final TemporarilyAbsentService absentService;
    private final TemporarilyStayingService stayingService;
    Long idPP;
    @GetMapping("/populations/{id}")
    public String populations(Model model, @PathVariable("id") Long id){
        ResidenceBooklet residenceBooklet =  residenceBookletService.findAllInforResidenceBookletById(id);
        List<Population> populations = populationService.getAllPopulationByResidenceBookletId(residenceBooklet);
        model.addAttribute("populations",populations);
        model.addAttribute("size",populations.size());
        idPP=id;
        model.addAttribute("populationNew",new Population());
        model.addAttribute("tittle","Thông tin hộ khẩu");
        return "population";
    }

    @PostMapping("/save-population")
    public String add(@ModelAttribute("populationNew")PopulationDto population,
                      @RequestParam("image") MultipartFile file,
                      RedirectAttributes attributes,Model model){
        try {
            String id =population.getPopulationCode();
            boolean findPopulationCode = populationService.findByPopulationCode(id);
            model.addAttribute("findPopulationCode",findPopulationCode);
            if(findPopulationCode){
                return "redirect:/populations/"+idPP;
            }
            population.setResidenceBooklet(residenceBookletService.findAllInforResidenceBookletById(idPP));
            populationService.save(file,population);
            attributes.addFlashAttribute("success","Thêm thành công nhân khẩu");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error","Add error");
        }
        return "redirect:/populations/"+idPP;
    }

    @GetMapping("/update-population/{id}")
    public String showUpdateForm(@PathVariable("id")Long id, Model model){
        PopulationDto populationDto = populationService.getById(id);
        model.addAttribute("idPP",idPP);
        model.addAttribute("populationUpdate",populationDto);
        return "update_population";
    }

    @PostMapping("/update-population/{id}")
    public String updatePopulaton(@ModelAttribute("populationUpdate") PopulationDto populationDto,
                                  RedirectAttributes attributes){
        try{
            populationService.update(populationDto);
            attributes.addFlashAttribute("success","Bạn đã thay đổi thông tin nhân khẩu thành công");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error","Bạn thay đổi thông tin nhân khẩu thất bại");
        }
        return "redirect:/populations/"+idPP;
    }

    @RequestMapping(value = "/alive-population/{id}",
            method = {RequestMethod.PUT, RequestMethod.GET})
    public String alivePopulation(@PathVariable("id")Long id){
        try {
            populationService.alive(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/populations/"+idPP;
    }

    @RequestMapping(value = "/dead-population/{id}",
            method = {RequestMethod.PUT, RequestMethod.GET})
    public String deadPopulation(@PathVariable("id")Long id){
        try {
            populationService.dead(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/populations/"+idPP;
    }

    //Bắt đầu đăng ký tạm vắng
    @GetMapping("/absent/{id}")
    public String TemporarilyAbsentShow(Model model, @PathVariable("id")Long id){
        PopulationDto populationDto = populationService.getById(id);
        List<TemporarilyAbsent> temporarilyAbsents = absentService.selectById(id);
        //Lấy danh sách giấy tạm vắng hết hạn
        List<TemporarilyAbsent> absentNewFalse = new ArrayList<>();
        for (TemporarilyAbsent absent: temporarilyAbsents){
            if(!absent.isNewAbsent()){
                absentNewFalse.add(absent);
            }else {
                model.addAttribute("absentNewTrue",absent);
            }
        }

        model.addAttribute("temporarilyAbsents",temporarilyAbsents);
        model.addAttribute("absentNewFalse",absentNewFalse);
        model.addAttribute("populationDto",populationDto);
        model.addAttribute("idPP",idPP);
        model.addAttribute("temporarilyAbsent",new TemporarilyAbsent());
        return "absent";
    }

    @PostMapping("/save-absent")
    public String TemporarilyAbsentSave(@ModelAttribute("temporarilyAbsent") TemporarilyAbsentDto absentDto,
                                        RedirectAttributes attributes){
        try {
            Long id = absentDto.getPopulationCode();
            List<TemporarilyAbsent> absentList =absentService.selectByNewAbsent(id);
            if(absentList != null){
                for (TemporarilyAbsent absent : absentList) {
                    absentService.update(absent);
                }
            }
            absentService.save(absentDto);
            attributes.addFlashAttribute("success","Bạn đã đã đăng ký tạm vắng");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error","Bạn đăng ký tạm vắng thất bại");
        }

        return "redirect:/populations/"+idPP;
    }
    //End đăng ký tạm vắng

    //Bắt đầu đăng ký tạm trú
    @GetMapping("/staying/{id}")
    public String TemporarilyStayingShow(Model model, @PathVariable("id")Long id){
        PopulationDto populationDto = populationService.getById(id);
        List<TemporarilyStaying> stayingList=stayingService.selectById(id);
        List<TemporarilyStaying> stayingNewFalse = new ArrayList<>();
        for (TemporarilyStaying staying : stayingList){
            if(!staying.isNewStay()){
                stayingNewFalse.add(staying);
            }else {
                model.addAttribute("stayingNewTrue",staying);
            }
        }
        model.addAttribute("stayingNewFalse",stayingNewFalse);
        model.addAttribute("populationDto",populationDto);
        model.addAttribute("idPP",idPP);
        model.addAttribute("temporarilyStaying",new TemporarilyStaying());
        return "staying";
    }

    @PostMapping("/save-staying")
    public String TemporarilyStayingSave(@ModelAttribute("temporarilyStaying") TemporarilyStayingDto stayingDto,
                                         RedirectAttributes attributes){
        try {
            Long id = stayingDto.getPopulationCode();
            List<TemporarilyStaying> stayingList = stayingService.selectByNewAbsent(id);
            if(stayingList!=null){
                for (TemporarilyStaying staying : stayingList){
                    stayingService.update(staying);
                }
            }
            stayingService.save(stayingDto);
            attributes.addFlashAttribute("success","Bạn đã đã đăng ký tạm trú");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error","Bạn đăng ký tạm trú thất bại");
        }
        return "redirect:/populations/"+idPP;
    }


//    @GetMapping("/statics-population")
//    public String hienThi(Model model, @RequestParam(value = "page",defaultValue = "0")Integer page) {
//        model.addAttribute("populations",populationService.pagination(page,5).getContent());
//        return "statics-population";
//    }
//    @GetMapping("/statics-population/{page}")
//    public String pagination(Model model,@PathVariable(value = "page")Integer page) {
//        model.addAttribute("populations",populationService.pagination(page,5).getContent());
//        return "statics-population";
//    }
//
//    @GetMapping("/filter")
//    public String loc(Model model,
//                      @RequestParam String search,
//                      @RequestParam("ageBegin") String ageBegin,
//                      @RequestParam("ageEnd") String ageEnd,
//                      @RequestParam("gioiTinh")String gioiTinh) {
//        if (ageBegin.isBlank() && ageEnd.isBlank()){
//            model.addAttribute("populations",populationService.getPopulationByAddressOrCode(search,gioiTinh));
//        }else {
//            model.addAttribute("populations",populationService.getByDate(ageBegin,ageEnd));
//        }
//        return "statics-population";
//    }
}
