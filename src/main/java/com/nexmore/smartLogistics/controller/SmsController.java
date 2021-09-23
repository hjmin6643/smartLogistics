package com.nexmore.smartLogistics.controller;

import com.google.gson.Gson;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.domain.SmsDTO;
import com.nexmore.smartLogistics.service.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
public class SmsController {

    private SmsService smsService;

    @GetMapping(value = {"/sms_notice" })
    public String sms_notice(HttpServletRequest req, Model model, String no) {
        model.addAttribute("title", "SMS 발송 이력");
        if(no != null) {
            model.addAttribute("no", no);
        }else {
            model.addAttribute("no", null);
        }
        return "page/sms_notice";
    }

    @ResponseBody
    @PostMapping("/sms_notice_table")
    public String sms_notice_table(HttpServletRequest req, String no) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        System.out.println(no+"noooo");
        try {
            if(no.equals("null")) {
                System.out.println("test");
                List<SmsDTO> tableList = smsService.selectSmsList();
                obj.put("data", tableList);
            }else  {
                SmsDTO smsDTO =  new SmsDTO();
                smsDTO.setNo(no);
                System.out.println(smsDTO.toString()+"test1231");
                List<SmsDTO> tableList = smsService.selectSmsListNo(smsDTO);
                System.out.println(tableList.toString()+"test321321");
                obj.put("data", tableList);

            }

        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }
}
