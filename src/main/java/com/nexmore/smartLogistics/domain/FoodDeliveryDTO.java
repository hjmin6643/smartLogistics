package com.nexmore.smartLogistics.domain;

import lombok.Data;

@Data
public class FoodDeliveryDTO {
    private String no;
    private String status;
    private String address;
    private String regDate;
    private String count;
    private String type;
}
