package com.example.service;

import com.example.domain.WalletEntity;
import com.example.repository.WalletRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {
    @Autowired private WalletRepository repository;

    @KafkaListener(topics = {"user_created"}, groupId = "suriya")
    public void createWallet(String input) throws ParseException {
        JSONObject obj = (JSONObject) new JSONParser().parse(input);
//        String walletId = (String)obj.get("phone");
        String walletId = UUID.randomUUID().toString();
        WalletEntity wallet = WalletEntity.builder().walletId(walletId).currency("INR").balance(100L).build();
        repository.save(wallet);
    }

}
