package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryInfoDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDetailDTO;
import com.nexmore.smartLogistics.mapper.DeliveryStatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService {
    private DeliveryStatusMapper deliveryStatusMapper;

    @Override
    public List<DeliveryStatusDTO> selectDeliveryStatus(DeliveryStatusDTO deliveryStatusDTO) {

        return deliveryStatusMapper.selectDeliveryStatus(deliveryStatusDTO);
    }

    @Override
    public List<DeliveryStatusDetailDTO> selectFindNo(DeliveryStatusDetailDTO deliveryStatusDetailDTO) {
        return deliveryStatusMapper.selectFindNo(deliveryStatusDetailDTO);
    }
    @Override
    public List<DeliveryInfoDTO> selectDeliveryInfo(DeliveryInfoDTO deliveryInfoDTO) {
        return deliveryStatusMapper.selectDeliveryInfo(deliveryInfoDTO);
    }
    @Override
    public int insertDetailDelete(DeliveryStatusDetailDTO deliveryStatusDetailDTO) {
        return deliveryStatusMapper.insertDetailDelete(deliveryStatusDetailDTO);
    }

    @Override
    public List<DeliveryStatusDetailDTO> selectDeleteYn(DeliveryStatusDetailDTO deliveryStatusDetailDTO) {
        return deliveryStatusMapper.selectDeleteYn(deliveryStatusDetailDTO);
    }

    @Override
    public List<DeliveryStatusDetailDTO> selectDeleteList(DeliveryStatusDetailDTO deliveryStatusDetailDTO) {
        return deliveryStatusMapper.selectDeleteList(deliveryStatusDetailDTO);
    }
    @Override
    public int updateDeliveryDetail(DeliveryStatusDetailDTO deliveryStatusDetailDTO) {
        return deliveryStatusMapper.updateDeliveryDetail(deliveryStatusDetailDTO);
    }
    @Override
    public List<DeliveryStatusDTO> selecttrafficStatus(){
        return deliveryStatusMapper.selecttrafficStatus();
    }
    @Override
    public List<DeliveryStatusDTO> selectroadStatus(){
        return deliveryStatusMapper.selectroadStatus();
    }


}
