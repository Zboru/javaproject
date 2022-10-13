package com.zborowski.inmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InmateController {
    @Autowired private InmateService service;

    @GetMapping("/inmates")
    public String showInmateList(Model model) {
        List<Inmate> inmates = service.getInmates();
        model.addAttribute("inmates", inmates);
        return "inmates";
    }

}
