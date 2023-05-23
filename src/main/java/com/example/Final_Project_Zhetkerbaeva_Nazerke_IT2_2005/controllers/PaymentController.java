package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.controllers;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.PaymentDto;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Users;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;


@RestController
public class PaymentController {

    @Autowired
    UserService userService;

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/add_payment")
    public RedirectView addPayment(@RequestParam(name="card_number_field") String card_number,
                             @RequestParam(name="expiration_field") String expiration,
                             @RequestParam(name="cvv_field") int card_v_number,
                             @RequestParam(name="amount_field") int amount,
                             @RequestParam(name="user_id") Long user_id){
        if(card_number.equals("") || expiration.equals("") || card_v_number == 0 || amount == 0 || user_id == 0){
            return new RedirectView("/payment");
        }
        PaymentDto paymentDto = new PaymentDto(card_number, expiration, card_v_number, amount, user_id);

        HttpEntity<PaymentDto> requestEntity = new HttpEntity<>(paymentDto);

        String paymentServerUrl = "http://localhost:8001/payment/add";

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                paymentServerUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String profilePage = "/profile";
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Users user = userService.getUser(user_id);
            user.setTicket(true);
            user.setAmount_of_ticket(user.getAmount_of_ticket() + amount);
            userService.saveUser(user);
        }
        return new RedirectView(profilePage);
    }

    @GetMapping("getPaymentByUserId/{id}")
    public RedirectView myPaymentInfo(@PathVariable(name="id") Long user_id, HttpSession session){
        String paymentServerUrl = "http://localhost:8001/payment/getByUserId/" + user_id;

        ResponseEntity<PaymentDto> response = restTemplate.getForEntity(paymentServerUrl, PaymentDto.class);
        PaymentDto payment = response.getBody();
        session.setAttribute("payment_id", payment.getPayment_id());
        session.setAttribute("card_number", payment.getCard_number());
        session.setAttribute("expiration", payment.getExpiration());
        session.setAttribute("card_v_number", payment.getCard_v_number());

        String paymentPage = "/paymentinfo";
        return new RedirectView(paymentPage);
    }

}
