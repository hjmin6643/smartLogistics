package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.FoodDeliveryDTO;

import java.util.List;

public interface FoodDeliveryService {

    List<FoodDeliveryDTO> selectFoodDelivery();
    List<FoodDeliveryDTO> selectFoodDeliveryAll();
    List<FoodDeliveryDTO> selectfoodDeliveryStatus();
}
