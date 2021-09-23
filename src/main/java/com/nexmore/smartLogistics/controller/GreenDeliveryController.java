package com.nexmore.smartLogistics.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class GreenDeliveryController {
    @GetMapping(value = {"/green_delivery" })
    public String green_delivery(HttpServletRequest req, Model model) {
        model.addAttribute("title", "그린 스마트 빌리지");
        return "page/green_delivery";
    }
}
