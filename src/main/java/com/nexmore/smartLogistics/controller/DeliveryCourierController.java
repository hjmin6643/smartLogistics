package com.nexmore.smartLogistics.controller;

import com.google.gson.Gson;
import com.nexmore.smartLogistics.domain.DeliveryCourierDTO;
import com.nexmore.smartLogistics.domain.DeliveryManagerDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.service.DeliveryCourierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DeliveryCourierController {

    private DeliveryCourierService deliveryCourierService;

    @GetMapping(value = {"/delivery_courier" })
    public String deliveryCourier(HttpServletRequest req, Model model) {
        model.addAttribute("title", "택배기사 관리");
        return "page/delivery_courier";
    }

    @GetMapping(value = {"/update_courier" })
    public String update_courier(HttpServletRequest req, String no, Model model) {
        DeliveryCourierDTO deliveryCourierDTO = new DeliveryCourierDTO();
        deliveryCourierDTO.setNo(Integer.parseInt(no));
        List<DeliveryCourierDTO> courierList = deliveryCourierService.selectDeliveryCourierByNo(deliveryCourierDTO);
        model.addAttribute("title", "택배기사 수정");
        model.addAttribute("courierList", courierList);
        return "page/updateCourier";
    }


    @ResponseBody
    @PostMapping(value = {"/updateCourier.do" })
    public String  updateCourierDo (String no, String area, String name, String mobileNm,  RedirectAttributes red) throws IOException {
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        DeliveryCourierDTO deliveryCourierDTO = new DeliveryCourierDTO();
        System.out.println("no"+no);
        deliveryCourierDTO.setNo(Integer.parseInt(no));
        deliveryCourierDTO.setMobileNm(mobileNm);
        deliveryCourierDTO.setArea(area);
        deliveryCourierDTO.setName(name);
        System.out.println("test12345"+deliveryCourierDTO.toString());
        try {
            int res = deliveryCourierService.updateCourier(deliveryCourierDTO);
            System.out.println("test14"+deliveryCourierDTO.toString());
            if (res > 0) {
                System.out.println("success");
                obj.put("result", "success");
            } else {
                obj.put("result", "fail");
            }
        } catch (Exception e) {
            obj.put("result", "fail");
        }

        return gson.toJson(obj);
    }


    @ResponseBody
    @PostMapping("/deliveryCourier")
    public String deliveryStatus(HttpServletRequest req) {
        System.out.println("1111");
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
           List<DeliveryCourierDTO> tableList = deliveryCourierService.selectDeliveryCourier();
            obj.put("data", tableList);
            System.out.println("testtttt"+tableList.toString());
        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }
    @GetMapping(value = {"/addCourier" })
    public String addCourier(HttpServletRequest req, Model model) {
        model.addAttribute("title", "택배기사 신규 등록");
        return "page/addCourier";
    }

    @ResponseBody
    @PostMapping(value = {"/deleteCourier.do" })
    public String  deleteCourier (String no,  RedirectAttributes red) throws IOException {
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        DeliveryCourierDTO deliveryCourierDTO = new DeliveryCourierDTO();
        System.out.println("no1234534"+no);
        deliveryCourierDTO.setNo(Integer.parseInt(no));
        try {
            int res = deliveryCourierService.deleteCourier(deliveryCourierDTO);
            System.out.println("test1234"+deliveryCourierDTO.toString());
            if (res > 0) {
                System.out.println("success");
                obj.put("result", "success");
            } else {
                obj.put("result", "fail");
            }
        } catch (Exception e) {
            obj.put("result", "fail");
        }

        return gson.toJson(obj);
    }

    @ResponseBody
    @PostMapping(value = {"/addCourier.do" })
    public String  addCourierForm (String name, String mobileNm, String area, Model model,  RedirectAttributes red) throws IOException {
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        DeliveryCourierDTO deliveryCourierDTO = new DeliveryCourierDTO();
        deliveryCourierDTO.setArea(area);
        deliveryCourierDTO.setName(name);
        deliveryCourierDTO.setMobileNm(mobileNm);
        System.out.println("test12"+deliveryCourierDTO.toString());
        try {
            int res = deliveryCourierService.insertCourier(deliveryCourierDTO);
             System.out.println("test1234"+deliveryCourierDTO.toString());
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
        System.out.println("test"+deliveryCourierDTO.toString());
        return gson.toJson(obj);
    }

}
