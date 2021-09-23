package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryManagerDTO;
import com.nexmore.smartLogistics.mapper.DeliveryManagerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryManagerServiceImpl implements DeliveryManagerService {
    private DeliveryManagerMapper deliveryManagerMapper ;

    @Override
    public List<DeliveryManagerDTO> selectDeliveryManager() {

        return deliveryManagerMapper.selectDeliveryManager();
    }

    @Override
    public  int insertManager(DeliveryManagerDTO deliveryManagerDTO){
        return deliveryManagerMapper.insertManager(deliveryManagerDTO);
    }
}
