package dev.phamduccong.quanlynhankhau.Controller;

import dev.phamduccong.quanlynhankhau.Dto.AgeDto;
import dev.phamduccong.quanlynhankhau.Dto.SearchDto;
import dev.phamduccong.quanlynhankhau.Entity.Population;
import dev.phamduccong.quanlynhankhau.Entity.ResidenceBooklet;
import dev.phamduccong.quanlynhankhau.Repository.PopulationRepository;
import dev.phamduccong.quanlynhankhau.Service.PopulationService;
import dev.phamduccong.quanlynhankhau.Service.ResidenceBookletService;
import dev.phamduccong.quanlynhankhau.Service.StaticPopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StaticPopulationController {
    @Autowired
    private StaticPopulationService populationService;
    private final ResidenceBookletService residenceBookletService;
    private final PopulationRepository populationRepository;

    @GetMapping("/statics-population")
    public String hienThi(Model model, @RequestParam(value = "page",defaultValue = "0")Integer page) {
        List<Population> populationList = populationRepository.findAll();
        model.addAttribute("populations",populationList);
        model.addAttribute("searchDto", new SearchDto());
        model.addAttribute("ageDto", new AgeDto());
        return "statics-population";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("searchDto") SearchDto searchDto,
                         @ModelAttribute("ageDto") AgeDto ageDto
                         ,Model model){
        System.out.println(searchDto.getSearch());
        ResidenceBooklet residenceBooklet = residenceBookletService.findByResidenceBookletCode(searchDto.getSearch());
        List<Population> populationList = populationRepository.findPopulationsByResidenceBooklet(residenceBooklet);
        model.addAttribute("populations" , populationList);
        return "statics-population";
    }
    @PostMapping("/filter")
    public String loc(Model model,
                      @ModelAttribute("ageDto") AgeDto ageDto,
                      @ModelAttribute("searchDto") SearchDto searchDto
                      ) {
        List<Population> populationList = populationService.getByDate(ageDto.getBegin(),ageDto.getEnd());
        model.addAttribute("populations" , populationList);
        return "statics-population";
    }
}
