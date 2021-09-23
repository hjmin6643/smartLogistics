package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.FoodDeliveryDTO;
import com.nexmore.smartLogistics.mapper.FoodDeliveryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FoodDeliveryServiceImpl implements FoodDeliveryService {
    private FoodDeliveryMapper foodDeliveryMapper;

    @Override
    public List<FoodDeliveryDTO> selectFoodDelivery() {
        return foodDeliveryMapper.selectFoodDelivery();
    }
    @Override
    public List<FoodDeliveryDTO> selectFoodDeliveryAll() {
        return foodDeliveryMapper.selectFoodDeliveryAll();
    }
    @Override
    public List<FoodDeliveryDTO> selectfoodDeliveryStatus() {
        return foodDeliveryMapper.selectfoodDeliveryStatus();
    }

}
