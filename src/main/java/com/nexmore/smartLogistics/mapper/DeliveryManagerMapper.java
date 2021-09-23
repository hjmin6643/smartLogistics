package com.nexmore.smartLogistics.mapper;

import com.nexmore.smartLogistics.domain.DeliveryManagerDTO;

import java.util.List;

public interface DeliveryManagerMapper {
    List<DeliveryManagerDTO> selectDeliveryManager();
    int insertManager(DeliveryManagerDTO deliveryManagerDTO);
}
