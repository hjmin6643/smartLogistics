package com.nexmore.smartLogistics.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class ApartDeliveryController {
    @GetMapping(value = {"/apart_delivery" })
    public String apart_delivery(HttpServletRequest req, Model model) {
        model.addAttribute("title", "푸르나임 아파트");
        return "page/apartDelivery";
    }
}
