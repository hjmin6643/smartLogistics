package com.nexmore.smartLogistics.service;

import com.nexmore.smartLogistics.domain.SmsDTO;

import java.util.List;

public interface SmsService {

    List<SmsDTO> selectSmsList();
    List<SmsDTO> selectSmsListNo(SmsDTO smsDTO);
}
