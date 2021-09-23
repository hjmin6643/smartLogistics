package com.nexmore.smartLogistics;

import com.nexmore.smartLogistics.domain.DeliveryMapDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.service.DeliveryMapService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class MainController {

    private DeliveryMapService deliveryMapService;

    @GetMapping(value = {"/", "/main"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("title", "main");
        System.out.println("test"+DeviceUtils.getCurrentDevice( request ));
        Device device = new LiteDeviceResolver().resolveDevice(request);
        request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE,device);
        Device currentDevice = DeviceUtils.getCurrentDevice(request);

        if ( currentDevice.isMobile() ){
            return "mobile/mobile_index";
        } else if(currentDevice.isTablet()) {
            return "";
        } else  {
            List<DeliveryMapDTO> mapList = deliveryMapService.selectDeliveryMap();
            List<DeliveryStatusDTO> kimCountList = deliveryMapService.selectKimcheonChart();
            List<DeliveryStatusDTO> allCount = deliveryMapService.selectAllChart();
            List<DeliveryStatusDTO> allCountList = deliveryMapService.selectAllChartList();
            List<DeliveryStatusDTO> kimcheonCount = deliveryMapService.selectkimcheonChart();
            List<DeliveryStatusDTO> roadChart = deliveryMapService.selectroadChart();
            List<DeliveryStatusDTO> greenChart = deliveryMapService.selectgreenChart();
            List<DeliveryStatusDTO> roadAll = deliveryMapService.selectroadAll();
            List<DeliveryStatusDTO> greenAll = deliveryMapService.selectgreenAll();
            List<DeliveryStatusDTO> trafficChart = deliveryMapService.selectTraffic();
            List<DeliveryStatusDTO> trafficAll = deliveryMapService.selectTrafficAll();
            List<DeliveryStatusDTO> apartChart = deliveryMapService.selectApart();
            List<DeliveryStatusDTO> apartAll = deliveryMapService.selectApartAll();
            List<DeliveryStatusDTO> officeChart = deliveryMapService.selectOffice();
            List<DeliveryStatusDTO> officeAll = deliveryMapService.selectOfficeAll();

            System.out.println("map test"+mapList.toString());
            model.addAttribute("mapList", mapList);
            model.addAttribute("kimCountList", kimCountList);
            model.addAttribute("allCount", allCount);
            model.addAttribute("allCountList", allCountList);
            model.addAttribute("kimcheonCount", kimcheonCount);
            model.addAttribute("roadChart", roadChart);
            model.addAttribute("greenChart", greenChart);
            model.addAttribute("roadAll", roadAll);
            model.addAttribute("greenAll", greenAll);
            model.addAttribute("trafficChart", trafficChart);
            model.addAttribute("trafficAll", trafficAll);
            model.addAttribute("apartChart", apartChart);
            model.addAttribute("apartAll", apartAll);
            model.addAttribute("officeChart", officeChart);
            model.addAttribute("officeAll", officeAll);

            return "index";
        }
      /*  return "index";*/
    }
    @GetMapping(value = {"/bacordTest"})
    public String bacordTest(HttpServletRequest req, Model model) {
        model.addAttribute("title", "bacordTest");
        return "main";
    }
}
