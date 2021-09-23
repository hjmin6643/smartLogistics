package com.nexmore.smartLogistics.controller;

import com.google.gson.Gson;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.domain.FoodDeliveryDTO;
import com.nexmore.smartLogistics.service.FoodDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class FoodDeliveryController {
    private FoodDeliveryService foodDeliveryService;

    @GetMapping(value = {"/food_delivery" })
    public String foodDelivery(HttpServletRequest req, Model model) {

        List<FoodDeliveryDTO> foodList = foodDeliveryService.selectFoodDelivery();
        List<FoodDeliveryDTO> foodAll = foodDeliveryService.selectFoodDeliveryAll();
        model.addAttribute("title", "음식 배송 현황");
        model.addAttribute("foodList", foodList);
        model.addAttribute("foodAll", foodAll);
        return "page/food_delivery";
    }

    @ResponseBody
    @PostMapping("/food_deliveryStatus")
    public String deliveryStatus(HttpServletRequest req) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
            List<FoodDeliveryDTO> tableList = foodDeliveryService.selectfoodDeliveryStatus();
            obj.put("data", tableList);

        } catch (Exception e) {
            obj.put("data", new ArrayList<>());
        }
        return gson.toJson(obj);

    }
}
