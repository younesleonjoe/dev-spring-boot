package com.leon.aopdemo.dao;

import com.leon.aopdemo.entity.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {

    public void addAccount() {

        System.out.println("AccountDAOImpl.addAccount() method has been called");
    }

    @Override
    public void addAccount(Account account, boolean isVip) {

        System.out.println("AccountDAOImpl.addAccount(account: " + account + ", isActive: "+ isVip + ") method has been called");
    }

    @Override
    public List<Account> findAccounts(boolean throwException) throws RuntimeException {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        accounts.add(new Account());
        accounts.add(new Account());
        if (throwException) throw new RuntimeException();
        return accounts;
    }

    public void addSillyMethod() {

        System.out.println("AccountDAOImpl.addSillyMethod() method has been called");
    }

    public boolean addSillyBooleanMethod() {

        System.out.println("AccountDAOImpl.addSillyBooleanMethod() method has been called");
        return true;
    }
}
