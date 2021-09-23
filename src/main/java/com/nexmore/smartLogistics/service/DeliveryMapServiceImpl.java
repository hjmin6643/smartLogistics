package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.DeliveryMapDTO;
import com.nexmore.smartLogistics.domain.DeliveryStatusDTO;
import com.nexmore.smartLogistics.mapper.DeliveryMapMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryMapServiceImpl implements DeliveryMapService {
    private DeliveryMapMapper deliveryMapMapper;
    @Override
    public List<DeliveryMapDTO> selectDeliveryMap() {
        return deliveryMapMapper.selectDeliveryMap();
    }
    @Override
    public List<DeliveryStatusDTO> selectKimcheonChart() {
        return deliveryMapMapper.selectKimcheonChart();
    }
    @Override
    public List<DeliveryStatusDTO> selectAllChart() {
        return deliveryMapMapper.selectAllChart();
    }
    @Override
    public List<DeliveryStatusDTO> selectAllChartList() {
        return deliveryMapMapper.selectAllChartList();
    }
    @Override
    public List<DeliveryStatusDTO> selectkimcheonChart() {

        return deliveryMapMapper.selectkimcheonChart();
    }
    @Override
    public List<DeliveryStatusDTO> selectroadChart() {
        return deliveryMapMapper.selectroadChart();
    }
    @Override
    public List<DeliveryStatusDTO> selectgreenChart() {
        return deliveryMapMapper.selectgreenChart();
    }
    @Override
    public List<DeliveryStatusDTO> selectroadAll() {
        return deliveryMapMapper.selectroadAll();
    }
    @Override
    public List<DeliveryStatusDTO> selectgreenAll() {
        return deliveryMapMapper.selectgreenAll();
    }

    @Override
    public List<DeliveryStatusDTO>  selectTraffic(){
        return deliveryMapMapper.selectTraffic();
    }
    @Override
    public List<DeliveryStatusDTO> selectTrafficAll(){
        return deliveryMapMapper.selectTrafficAll();
    }
    @Override
    public List<DeliveryStatusDTO> selectApart(){
        return deliveryMapMapper.selectApart();
    }
    @Override
    public List<DeliveryStatusDTO> selectApartAll(){
        return deliveryMapMapper.selectApartAll();
    }
    @Override
    public List<DeliveryStatusDTO> selectOffice(){
        return deliveryMapMapper.selectOffice();
    }
    @Override
    public List<DeliveryStatusDTO> selectOfficeAll(){
        return deliveryMapMapper.selectOfficeAll();
    }




}
