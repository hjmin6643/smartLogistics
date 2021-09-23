package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryInfoDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDetailDTO;
import com.nexmore.smartLogistics.mapper.DeliveryStatusMapper;

import java.util.List;

public interface DeliveryStatusService {
    List<DeliveryStatusDTO> selectDeliveryStatus(DeliveryStatusDTO deliveryStatusDTO);
    List<DeliveryStatusDetailDTO> selectFindNo(DeliveryStatusDetailDTO deliveryStatusDetailDTO);
    List<DeliveryInfoDTO> selectDeliveryInfo(DeliveryInfoDTO deliveryInfoDTO);
    int insertDetailDelete(DeliveryStatusDetailDTO deliveryStatusDetailDTO);
    List<DeliveryStatusDetailDTO> selectDeleteYn(DeliveryStatusDetailDTO deliveryStatusDetailDTO);
    List<DeliveryStatusDetailDTO> selectDeleteList(DeliveryStatusDetailDTO deliveryStatusDetailDTO);
    int updateDeliveryDetail(DeliveryStatusDetailDTO deliveryStatusDetailDTO);
    List<DeliveryStatusDTO> selecttrafficStatus();
    List<DeliveryStatusDTO> selectroadStatus();
}
