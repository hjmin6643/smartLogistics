package com.nexmore.smartLogistics.mapper;

import com.nexmore.smartLogistics.domain.DeliveryCourierDTO;

import java.util.List;

public interface DeliveryCourierMapper {
    List<DeliveryCourierDTO> selectDeliveryCourier();
    int insertCourier(DeliveryCourierDTO deliveryCourierDTO);
    int deleteCourier(DeliveryCourierDTO deliveryCourierDTO);
    List<DeliveryCourierDTO> selectDeliveryCourierByNo(DeliveryCourierDTO deliveryCourierDTO);
    int updateCourier(DeliveryCourierDTO deliveryCourierDTO);
}
