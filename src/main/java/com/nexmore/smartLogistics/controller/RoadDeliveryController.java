package com.nexmore.smartLogistics.controller;

import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.service.DeliveryMapService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class RoadDeliveryController {
    private DeliveryMapService deliveryMapService;
    @GetMapping(value = {"/road_delivery" })
    public String road_delivery(HttpServletRequest req, Model model) {
        List<DeliveryStatusDTO> roadAll = deliveryMapService.selectroadAll();
        List<DeliveryStatusDTO> roadChart = deliveryMapService.selectroadChart();
        model.addAttribute("title", "한국 도로 공사");
        model.addAttribute("roadChart", roadChart);
        model.addAttribute("roadAll", roadAll);
        return "page/road_delivery";
    }
}
