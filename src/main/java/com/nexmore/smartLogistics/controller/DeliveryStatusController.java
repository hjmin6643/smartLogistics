package com.nexmore.smartLogistics.controller;

import com.google.gson.Gson;
import com.nexmore.smartLogistics.domain.DeliveryInfoDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDetailDTO;
import com.nexmore.smartLogistics.service.DeliveryMapService;
import com.nexmore.smartLogistics.service.DeliveryStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DeliveryStatusController {
    private DeliveryStatusService deliveryStatusService;
    private DeliveryMapService deliveryMapService;

    @GetMapping(value = {"/kimcheon_deliveryStatus" })
    public String kimcheon_deliveryStatus(HttpServletRequest req, Model model) {
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        if (startDate == null || endDate == null) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate today = LocalDate.now();
            startDate = today.minusDays(1).format(pattern);
            endDate = today.format(pattern);
        }

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        List<DeliveryStatusDTO> kimCountList = deliveryMapService.selectKimcheonChart();
        List<DeliveryStatusDTO> kimCountAll = deliveryMapService.selectkimcheonChart();
        model.addAttribute("title", "김천시청");
        model.addAttribute("kimCountList", kimCountList);
        model.addAttribute("kimCountAll", kimCountAll);
        return "kimcheon/kimcheon";
    }

    @GetMapping(value = {"/kimcheon_update" })
    public String kimcheon_update(HttpServletRequest req, Model model, String no) {

        DeliveryStatusDetailDTO deliveryStatusDetailDTO = new DeliveryStatusDetailDTO();
        deliveryStatusDetailDTO.setNo(no);
        List<DeliveryStatusDetailDTO> deliveryDetailList = deliveryStatusService.selectFindNo(deliveryStatusDetailDTO);
        model.addAttribute("title", "배송 정보 수정");
        model.addAttribute("deliveryDetailList", deliveryDetailList);
        return "kimcheon/kimcheon_update";
    }


    @ResponseBody
    @PostMapping("/updateKimcheon.do")
    public String updateKimcheon(String no, String reason, String staff, String mobileNo, String area, String address,  HttpServletRequest req) {
        DeliveryStatusDetailDTO detailDTO = new DeliveryStatusDetailDTO();
        System.out.println("update");
        detailDTO.setNo(no);
        detailDTO.setReason(reason);
        detailDTO.setDeleteYn("수정");
        detailDTO.setAddress(address);
        detailDTO.setStaff(staff);
        detailDTO.setMobileNo(mobileNo);
        detailDTO.setArea(area);
        System.out.println("test12345"+detailDTO.toString());
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        try {

            int result = deliveryStatusService.insertDetailDelete(detailDTO);
            int result1 = deliveryStatusService.updateDeliveryDetail(detailDTO);
            if(result1 > 0) {
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
    @GetMapping(value = {"/traffic_deliveryStatus" })
    public String traffic_deliveryStatus(HttpServletRequest req, Model model) {
        List<DeliveryStatusDTO> trafficChart = deliveryMapService.selectTraffic();
        List<DeliveryStatusDTO> trafficAll = deliveryMapService.selectTrafficAll();
        model.addAttribute("title", "한국교통안전공단");
        model.addAttribute("trafficChart", trafficChart);
        model.addAttribute("trafficAll", trafficAll);
        return "traffic/traffic";
    }

    @GetMapping(value = {"/status_delete" })
    public String status_delete(HttpServletRequest req, Model model, String no) {

        model.addAttribute("title", "배송 현황 삭제 사유");
        model.addAttribute("no", no);
        return "kimcheon/kimcheon_delete";
    }

    @GetMapping(value = {"/detail" })
    public String kimcheon_detail(HttpServletRequest req, Model model, String no) {

        DeliveryStatusDetailDTO deliveryStatusDetailDTO = new DeliveryStatusDetailDTO();
        DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
        deliveryInfoDTO.setNo(no);
        deliveryStatusDetailDTO.setNo(no);

        try {
            List<DeliveryStatusDetailDTO> deliveryDetailList = deliveryStatusService.selectFindNo(deliveryStatusDetailDTO);
            List<DeliveryInfoDTO> deliveryInfoList = deliveryStatusService.selectDeliveryInfo(deliveryInfoDTO);
            List<DeliveryStatusDetailDTO> deleteYn = deliveryStatusService.selectDeleteYn(deliveryStatusDetailDTO);
            List<DeliveryStatusDetailDTO> deleteList = deliveryStatusService.selectDeleteList(deliveryStatusDetailDTO);
            model.addAttribute("deliveryDetailList", deliveryDetailList);
            model.addAttribute("deliveryInfoList", deliveryInfoList);
            model.addAttribute("deleteList", deleteList);
            model.addAttribute("deleteYn", deleteYn);


        } catch (Exception e) {
            model.addAttribute("deliveryDetailList",  new ArrayList<>());
            model.addAttribute("deliveryInfoList",  new ArrayList<>());
            model.addAttribute("deleteList", new ArrayList<>());
            model.addAttribute("deleteYn", new ArrayList<>());
        }
        model.addAttribute("title", "김천시청 배송 상세정보");
        model.addAttribute("no", no);
        return "kimcheon/kimcheon_detail";
    }


    @ResponseBody
    @PostMapping("/deliveryStatus")
    public String deliveryStatus(HttpServletRequest req, String startDate, String endDate) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
            DeliveryStatusDTO deliveryStatusDTO = new DeliveryStatusDTO();
            deliveryStatusDTO.setStartDate(startDate);
            deliveryStatusDTO.setEndDate(endDate);
            List<DeliveryStatusDTO> tableList = deliveryStatusService.selectDeliveryStatus(deliveryStatusDTO);
            obj.put("data", tableList);

        } catch (Exception e) {

           // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }
    @ResponseBody
    @PostMapping("/traffic_deliveryStatus")
    public String traffic_deliveryStatus(HttpServletRequest req) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
            List<DeliveryStatusDTO> tableList = deliveryStatusService.selecttrafficStatus();
            obj.put("data", tableList);

        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }

    @ResponseBody
    @PostMapping("/road_deliveryStatus")
    public String road_deliveryStatus(HttpServletRequest req) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        try {
            List<DeliveryStatusDTO> tableList = deliveryStatusService.selectroadStatus();
            obj.put("data", tableList);

        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }

    @ResponseBody
    @PostMapping("/delete_status_info")
    public String delete_status(String no, String reason, HttpServletRequest req) {
        DeliveryStatusDetailDTO detailDTO = new DeliveryStatusDetailDTO();
        detailDTO.setNo(no);
        detailDTO.setReason(reason);
        detailDTO.setDeleteYn("삭제");
        System.out.println("test12345"+detailDTO.toString());
        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();
        try {

            int result = deliveryStatusService.insertDetailDelete(detailDTO);
            if(result > 0) {
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
    @GetMapping("/findNo")
    public String findNo(HttpServletRequest req, String no) {

        Gson gson = new Gson();
        Map<String, Object> obj = new HashMap<>();

        DeliveryStatusDetailDTO deliveryStatusDetailDTO = new DeliveryStatusDetailDTO();
        deliveryStatusDetailDTO.setNo(no);
        try {
            List<DeliveryStatusDetailDTO> tableList = deliveryStatusService.selectFindNo(deliveryStatusDetailDTO);
            obj.put("data", tableList);

        } catch (Exception e) {

            // log.error("OstatController weatherTable Error : ", e);
            obj.put("data", new ArrayList<>());

        }

        return gson.toJson(obj);

    }

}
