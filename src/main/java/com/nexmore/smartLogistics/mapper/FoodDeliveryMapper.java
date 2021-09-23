package com.nexmore.smartLogistics.mapper;

import com.nexmore.smartLogistics.domain.FoodDeliveryDTO;
import com.nexmore.smartLogistics.service.FoodDeliveryService;

import java.util.List;

public interface FoodDeliveryMapper {
    List<FoodDeliveryDTO> selectFoodDelivery();
    List<FoodDeliveryDTO> selectFoodDeliveryAll();
    List<FoodDeliveryDTO> selectfoodDeliveryStatus();
}
