package com.leon.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAOImpl implements MembershipDAO {

    public void addAccount() {

        System.out.println("MembershipDAOImpl.addAccount() method has been called");
    }
}
