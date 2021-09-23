package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryCourierDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.mapper.DeliveryCourierMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryCourierServiceImpl implements DeliveryCourierService {

    private DeliveryCourierMapper deliveryCourierMapper;

    @Override
    public List<DeliveryCourierDTO> selectDeliveryCourier() {

        return deliveryCourierMapper.selectDeliveryCourier();
    }
    @Override
    public int insertCourier (DeliveryCourierDTO deliveryCourierDTO) {
        return deliveryCourierMapper.insertCourier(deliveryCourierDTO);
    }

    @Override
    public int deleteCourier(DeliveryCourierDTO deliveryCourierDTO){
        return deliveryCourierMapper.deleteCourier(deliveryCourierDTO);
    }

    @Override
    public List<DeliveryCourierDTO> selectDeliveryCourierByNo(DeliveryCourierDTO deliveryCourierDTO){
        return deliveryCourierMapper.selectDeliveryCourierByNo(deliveryCourierDTO);
    }

    @Override
    public  int updateCourier(DeliveryCourierDTO deliveryCourierDTO){
        return deliveryCourierMapper.updateCourier(deliveryCourierDTO);
    }
}
