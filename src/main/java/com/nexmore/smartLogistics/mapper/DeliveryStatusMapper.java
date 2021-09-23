package com.nexmore.smartLogistics.mapper;

import com.nexmore.smartLogistics.domain.DeliveryInfoDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDetailDTO;

import java.util.List;

public interface DeliveryStatusMapper {
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
