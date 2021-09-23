package com.nexmore.smartLogistics.controller;

import com.google.gson.Gson;
import com.nexmore.smartLogistics.domain.DeliveryCourierDTO;
import com.nexmore.smartLogistics.domain.RobotDronDTO;
import com.nexmore.smartLogistics.service.RobotDronService;
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
public class RobotDronController {

    private RobotDronService robotDronService;

    @GetMapping(value = {"/robotDron" })
    public String robotDron(HttpServletRequest req, Model model) {
        model.addAttribute("title", "로봇/드론 관리");
        return "page/robot_Dron";
    }

    @ResponseBody
    @PostMapping("/robotDronList")
    public String deliveryStatus(HttpServletRequest req) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
            List<RobotDronDTO> tableList = robotDronService.selectRobotDron();
            obj.put("data", tableList);

        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }
}
