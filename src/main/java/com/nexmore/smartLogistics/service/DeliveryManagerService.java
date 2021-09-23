package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryManagerDTO;

import java.util.List;

public interface DeliveryManagerService {
    List<DeliveryManagerDTO> selectDeliveryManager();
    int insertManager(DeliveryManagerDTO deliveryManagerDTO);
}
