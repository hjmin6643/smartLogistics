package com.nexmore.smartLogistics.domain;

import lombok.Data;

@Data
public class DeliveryMapDTO {
    private String no;
    private String lat;
    private String lon;
    private String regDate;
    private String type;
    private String address;
}
