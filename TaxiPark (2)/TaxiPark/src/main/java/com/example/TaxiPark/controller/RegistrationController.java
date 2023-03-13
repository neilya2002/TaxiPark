package com.example.TaxiPark.controller;

import com.example.TaxiPark.model.RouteDto;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.model.entyti.UserRole;
import com.example.TaxiPark.service.RegistrationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name,@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        registrationService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/")
    public String roleFilter(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute(registrationService.getUpdatedUser(user));
        if (user.getRole().equals(UserRole.USER)) {
            model.addAttribute("route", new RouteDto());
            if(user.getCurrentOrderId()!=null){
                return "redirect:/orderInfo";
            }
            return "/user";
        }
        if (user.getRole().equals(UserRole.MANAGER)) {
            return "redirect:/manager";
        }
        if (user.getRole().equals(UserRole.DISPATCHER)) {
            return "redirect:/dispatcher";
        }
        return "/user";
    }

}
