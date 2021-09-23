package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryMapDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;

import java.util.List;

public interface DeliveryMapService  {
    List<DeliveryMapDTO> selectDeliveryMap();
    List<DeliveryStatusDTO> selectKimcheonChart();
    List<DeliveryStatusDTO> selectAllChart();
    List<DeliveryStatusDTO> selectAllChartList();
    List<DeliveryStatusDTO> selectkimcheonChart();
    List<DeliveryStatusDTO> selectroadChart();
    List<DeliveryStatusDTO> selectgreenChart();
    List<DeliveryStatusDTO> selectroadAll();
    List<DeliveryStatusDTO> selectgreenAll();
    List<DeliveryStatusDTO> selectTraffic();
    List<DeliveryStatusDTO> selectTrafficAll();
    List<DeliveryStatusDTO> selectApart();
    List<DeliveryStatusDTO> selectApartAll();
    List<DeliveryStatusDTO> selectOffice();
    List<DeliveryStatusDTO> selectOfficeAll();
}
