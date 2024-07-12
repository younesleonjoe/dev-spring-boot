package com.leon.aopdemo.dao;

import com.leon.aopdemo.entity.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount();
    void addAccount(Account account, boolean isVip);
    List<Account> findAccounts(boolean trowException);


    void addSillyMethod();
    boolean addSillyBooleanMethod();
}
