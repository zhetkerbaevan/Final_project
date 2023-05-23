package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long payment_id;
    private String card_number;
    private String expiration;
    private int card_v_number;
    private int amount;
    private Long userid;

    public PaymentDto(String card_number, String expiration, int card_v_number, int amount, Long userid) {
        this.card_number = card_number;
        this.expiration = expiration;
        this.card_v_number = card_v_number;
        this.amount = amount;
        this.userid = userid;
    }
}
