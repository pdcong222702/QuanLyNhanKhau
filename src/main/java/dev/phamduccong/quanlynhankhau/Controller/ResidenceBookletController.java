package dev.phamduccong.quanlynhankhau.Controller;


import dev.phamduccong.quanlynhankhau.Dto.SearchDto;
import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Dto.AddressDto;
import dev.phamduccong.quanlynhankhau.Dto.PopulationDto;
import dev.phamduccong.quanlynhankhau.Dto.ResidenceBookletDto;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import dev.phamduccong.quanlynhankhau.Service.PopulationService;
import dev.phamduccong.quanlynhankhau.Service.ResidenceBookletService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Controller
@RequiredArgsConstructor
public class ResidenceBookletController {

    private final ResidenceBookletService residenceBookletService;
    private final PopulationService populationService;
    Long idPP;
    List<PopulationDto> splitPopulation = new ArrayList<>();
    List<String> relationships = new ArrayList<>();

    @GetMapping("/residence-booklet")
    public String createResidenceBooklet( Model model){
        List<ResidenceBooklet> listResidenceBooklet = residenceBookletService.getAllResidenceBooklet();
        Map<ResidenceBooklet, Population> residencePopulationMap = new HashMap<>();
        for (ResidenceBooklet residenceBooklet : listResidenceBooklet) {
            Population population = populationService. findByResidenceIdAndRelationship(residenceBooklet);
            residencePopulationMap.put(residenceBooklet, population);
        }
        model.addAttribute("residencePopulationMap", residencePopulationMap);
        model.addAttribute("residenceBookletDto", new ResidenceBookletDto());
        model.addAttribute("addressDto", new AddressDto());
        model.addAttribute("search", new SearchDto());
        model.addAttribute("tittle","Quản lý hộ khẩu");
        return "house-hold";
    }

    @PostMapping("/save_residence_booklet")
    public String saveResidenceBooklet(@Valid @ModelAttribute("residenceBookletDto") ResidenceBookletDto residenceBookletDto,
                                       BindingResult result,
                                       Model model,
                                       HttpSession session,
                                       @RequestParam("image")MultipartFile image
                                       ){
        try{
            session.removeAttribute("message");
            if(result.hasErrors()){
                model.addAttribute("residenceBookletDto", residenceBookletDto);

                return "redirect:/residence-booklet";
            }
            String code = residenceBookletDto.getResidenceBookletCode();
            ResidenceBooklet residenceBooklet = residenceBookletService.findByResidenceBookletCode(code);
            if(residenceBooklet!=null){
                model.addAttribute("residenceBookletDto", new ResidenceBookletDto());
                session.setAttribute("message", "Mã hộ khẩu đã tồn tại");
                return "redirect:/residence-booklet";
            }
            residenceBookletService.saveResidenceBooklet(image, residenceBookletDto);
            model.addAttribute("residenceBookletDto", new ResidenceBookletDto());
            session.setAttribute("message", "Thêm mới thành công ");
            return "redirect:/residence-booklet";
        }catch (Exception e){
            session.setAttribute("message", "Có lỗi");
        }
        return "redirect:/residence-booklet";
    }

    @GetMapping("/residence-booklet-split/{id}")
    public String residenceBookletSplit(Model model, @PathVariable("id")Long id
                                       ){
        ResidenceBooklet residenceBooklet = residenceBookletService.findAllInforResidenceBookletById(id);
        Population populationMaster = populationService.findByResidenceIdAndRelationship(residenceBooklet);
        List<Population> populationMember = populationService.findPopulationIsNotMaster(residenceBooklet);
        List<String> relationship = new ArrayList<>();
        relationship.add("Chủ hộ");
        relationship.add("Vợ");
        relationship.add("Con");
        relationship.add("Con dâu");
        relationship.add("Con rể");
        relationship.add("Cha");
        relationship.add("Mẹ");
        relationship.add("Anh");
        relationship.add("Em");
        relationship.add("Ông");
        relationship.add("Bà");
        model.addAttribute("relationship", relationship);
        model.addAttribute("residenceBooklet",residenceBooklet);
        model.addAttribute("populationMaster", populationMaster);
        model.addAttribute("populationMember", populationMember);
        model.addAttribute("splitResidenceBooklet", new ResidenceBookletDto());
        idPP = id;
        model.addAttribute("tittle","Tách hộ khẩu");
        return "residence-booklet-split";
    }
    @GetMapping("/getCheckbox")
    public String getCheckbox(@RequestParam(value = "check", required = false) List<Boolean> isChosen,
                              @RequestParam(value = "listRelationship", required = false) List<String> listRelationship
                              ){
        ResidenceBooklet residenceBooklet = residenceBookletService.getResidenceBookletById(idPP);
        List<Population> populations = populationService.findPopulationIsNotMaster(residenceBooklet);
        List<PopulationDto> populationDtos = new ArrayList<>();
        populations.forEach(population -> {
            populationDtos.add(populationService.getById(population.getId()));
        });
        List<PopulationDto> populationsChosen = new ArrayList<>();
        for(int i = 0; i < isChosen.size();i ++){
            if(isChosen.get(i).equals(true)){
                populationDtos.get(i).setChosen(true);
                populationsChosen.add(populationDtos.get(i));
            }
        }
        splitPopulation = populationsChosen;
        relationships = listRelationship;
        return "redirect:/residence-booklet-split/" + idPP;
    }
    @PostMapping("/split-residence-booklet")
    public String splitResidenceBooklet(@ModelAttribute("splitResidenceBooklet")ResidenceBookletDto residenceBookletDto,
                                        Model model){
        ResidenceBooklet residenceBooklet =residenceBookletService.saveResidenceBookletSplit(residenceBookletDto);
        splitPopulation.forEach(population -> {
            population.setResidenceBooklet(residenceBooklet);
        });
        for (int i = 0; i < relationships.size() ; i++){
            splitPopulation.get(i).setRelationship(relationships.get(i));
        }
        splitPopulation.forEach(populationService::changeResidenceBookletId);

        splitPopulation.forEach(p->{
            System.out.println(p.getRelationship());
        }
        );
        ResidenceBooklet residenceBooklet2 = residenceBookletService.findAllInforResidenceBookletById(idPP);
        List<Population> populationMember = populationService.findPopulationIsNotMaster(residenceBooklet2);
        model.addAttribute("populationMember", populationMember);
        return "redirect:/residence-booklet";
    }
    @GetMapping("/residence-booklet-move/{id}")
    public String residenceBookletMove(Model model, @PathVariable("id")Long id){
        ResidenceBooklet residenceBookletMove = residenceBookletService.findAllInforResidenceBookletById(id);
        Population populationMaster = populationService.findByResidenceIdAndRelationship(residenceBookletMove);
        List<Population> populationMember = populationService.findPopulationIsNotMaster(residenceBookletMove);
        idPP = id;
        model.addAttribute("residenceBooklet",residenceBookletMove);
        model.addAttribute("populationMaster", populationMaster);
        model.addAttribute("populationMember", populationMember);
        ResidenceBookletDto residenceBookletDto = residenceBookletService.getResidenceBookletDtoById(id);
        model.addAttribute("residenceBookletUpdate",residenceBookletDto);
        model.addAttribute("tittle","Chuyển hộ khẩu");
        return "residence-booklet-move";
    }
    @PostMapping("/update-residence-booklet/{id}")
    public String updatePopulaton(@ModelAttribute("residenceBookletUpdate")
                                  ResidenceBookletDto residenceBookletDto,
                                  HttpSession session
                                  ){

        try{
            residenceBookletService.update(residenceBookletDto);
            session.setAttribute("message", "Chuyển thành công sổ hộ khẩu điện tử");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/residence-booklet-move/" + idPP;
    }
    @PostMapping("/filter-residence-booklet")
    private String filterResidenceBooklet(Model model,
                                          @ModelAttribute("addressDto") AddressDto addressDto,
                                          @ModelAttribute("search") SearchDto searchDto
                                          ){

        String findProvince = addressDto.getProvince();
        String findDistrict = addressDto.getDistrict();
        String findWard = addressDto.getWard();
        String[] province = findProvince.split("-");
        String[] district = findDistrict.split("-");
        String[] ward = findWard.split("-");
        String findAddress = ward[1] + " - " + district[1] + " - " + province[1];
        List<ResidenceBooklet> residenceBooklets = residenceBookletService.findAllResidenceBookletByAddress(findAddress);
        Map<ResidenceBooklet, Population> residencePopulationMap = new HashMap<>();
        for (ResidenceBooklet residenceBooklet : residenceBooklets) {
            Population population = populationService.findByResidenceIdAndRelationship(residenceBooklet);
            residencePopulationMap.put(residenceBooklet, population);
        }
        model.addAttribute("residencePopulationMap", residencePopulationMap);
        model.addAttribute("residenceBookletDto", new ResidenceBookletDto());
        model.addAttribute("residenceBookletSplit", new ResidenceBookletDto());
        model.addAttribute("tittle","Quản lý hộ khẩu");
        return "house-hold";
    }

    @PostMapping("/search-residence-booklet")
    private String searchResidenceBooklet(Model model,
                                          @ModelAttribute("search") SearchDto searchDto,
                                          @ModelAttribute("addressDto") AddressDto addressDto
    ){
        System.out.println(searchDto.getSearch());
        List<ResidenceBooklet> residenceBooklets = residenceBookletService.findAllResidenceBookletByCode(searchDto.getSearch());
        Map<ResidenceBooklet, Population> residencePopulationMap = new HashMap<>();
        for (ResidenceBooklet residenceBooklet : residenceBooklets) {
            Population population = populationService.findByResidenceIdAndRelationship(residenceBooklet);
            residencePopulationMap.put(residenceBooklet, population);
        }
        model.addAttribute("residencePopulationMap", residencePopulationMap);
        model.addAttribute("residenceBookletDto", new ResidenceBookletDto());
        model.addAttribute("residenceBookletSplit", new ResidenceBookletDto());
        model.addAttribute("tittle","Quản lý hộ khẩu");
        return "house-hold";
    }

}
