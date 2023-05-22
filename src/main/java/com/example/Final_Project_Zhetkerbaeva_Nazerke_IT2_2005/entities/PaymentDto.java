package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String card_number;
    private String expiration;
    private int card_v_number;
    private int amount;
    private Long user_id;
}
