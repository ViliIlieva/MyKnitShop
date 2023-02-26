package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.LoginDTO;
import com.example.myknitshop.models.dto.bindingModels.UserRegistrationDTO;
import com.example.myknitshop.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registrationDTO")//ако го няма инициран да го създадем ДТО-то
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO ();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO ();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

//TODO да проверя ако не е успял да се логне пак да редиректна логин
        if (bindingResult.hasErrors ()) {
            redirectAttributes.addFlashAttribute ("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }
        authService.registerAndLogin (registrationDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {

        if(error != null){
           model.addAttribute("error", "Error");
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {


        //ако има грешки от подадените във формата спрямо валидациите
        //изкарва грешките и връща под полетата валидация информация
        if (bindingResult.hasErrors ()) {
            redirectAttributes.addFlashAttribute ("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        //ако не сме го логнали успешно, няма го в базата, връщаме грешките и вярната информация
//        if (!this.authService.login (loginDTO)) {
//            redirectAttributes.addFlashAttribute ("loginDTO", loginDTO);
//            redirectAttributes.addFlashAttribute ("badCredentials", true);
//
//            return "redirect:/login";
//        }

        return "redirect:/index";
    }
}
