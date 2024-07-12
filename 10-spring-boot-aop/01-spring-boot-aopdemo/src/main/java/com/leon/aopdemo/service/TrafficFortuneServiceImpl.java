package com.leon.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {

    @Override
    public String getFortune(boolean throwException) {

        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5);
            if (throwException) throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // return a fortune
        return "Expect heavy traffic this morning";
    }
}
