package com.nexmore.smartLogistics.mapper;

import com.nexmore.smartLogistics.domain.SmsDTO;

import java.util.List;

public interface SmsMapper {

    List<SmsDTO> selectSmsList();
    List<SmsDTO> selectSmsListNo(SmsDTO smsDTO);
}
