package com.nexmore.smartLogistics.domain;

import lombok.Data;

@Data
public class SmsDTO {
    private String no;
    private String mobileNo;
    private String message;
    private String regDate;

}
