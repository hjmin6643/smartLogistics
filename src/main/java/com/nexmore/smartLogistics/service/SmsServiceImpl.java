package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.SmsDTO;
import com.nexmore.smartLogistics.mapper.SmsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SmsServiceImpl implements SmsService {
    private SmsMapper smsMapper;

    @Override
    public List<SmsDTO> selectSmsList() {

        return smsMapper.selectSmsList();
    }
    @Override
    public List<SmsDTO>  selectSmsListNo(SmsDTO smsDTO){
        return smsMapper.selectSmsListNo(smsDTO);
    }
}
