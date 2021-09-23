package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.RobotDronDTO;
import com.nexmore.smartLogistics.mapper.RobotDronMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RobotDronServiceImpl implements RobotDronService {

    private RobotDronMapper robotDronMapper;

    @Override
    public List<RobotDronDTO> selectRobotDron() {
        return robotDronMapper.selectRobotDron();
    }
}
