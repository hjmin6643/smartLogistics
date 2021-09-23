package com.nexmore.smartLogistics.controller;

import com.google.gson.Gson;
import com.nexmore.smartLogistics.domain.DeliveryCourierDTO;
import com.nexmore.smartLogistics.domain.DeliveryManagerDTO;
import com.nexmore.smartLogistics.service.DeliveryManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DeliveryManagerController {

    private DeliveryManagerService deliveryManagerService;

    @GetMapping(value = {"/delivery_Manager" })
    public String deliveryManager(HttpServletRequest req, Model model) {
        model.addAttribute("title", "배송 담당자 관리");
        return "page/delivery_manager";
    }

    @GetMapping(value = {"/addManager" })
    public String addManager(HttpServletRequest req, Model model) {
        model.addAttribute("title", "배송 담당자 신규 등록");
        return "page/addManager";
    }
    @ResponseBody
    @PostMapping("/deliveryManager")
    public String deliveryStatus(HttpServletRequest req) {
        System.out.println("1111");
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
            List<DeliveryManagerDTO> tableList = deliveryManagerService.selectDeliveryManager();
            obj.put("data", tableList);
            System.out.println("testtttt"+tableList.toString());
        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }
    @ResponseBody
    @PostMapping(value = {"/addManager.do" })
    public String  addManager (String name, String mobileNm, String area, Model model,  RedirectAttributes red) throws IOException {
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        DeliveryManagerDTO deliveryManagerDTO = new DeliveryManagerDTO();
        deliveryManagerDTO.setArea(area);
        deliveryManagerDTO.setName(name);
        deliveryManagerDTO.setMobileNm(mobileNm);

        try {
            int res = deliveryManagerService.insertManager(deliveryManagerDTO);

            if (res > 0) {
                System.out.println("success");
                obj.put("result", "success");
            } else {
                obj.put("result", "fail");
            }
        } catch (Exception e) {
            obj.put("result", "fail");
            System.out.println(e.getMessage());
        }

        return gson.toJson(obj);
    }

}
