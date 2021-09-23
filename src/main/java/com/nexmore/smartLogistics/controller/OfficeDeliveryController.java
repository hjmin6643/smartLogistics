package com.nexmore.smartLogistics.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class OfficeDeliveryController {
    @GetMapping(value = {"/office_delivery" })
    public String office_delivery(HttpServletRequest req, Model model) {
        model.addAttribute("title", "어모면사무소");
        return "page/office_delivery";
    }
}
