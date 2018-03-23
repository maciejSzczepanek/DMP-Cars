package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("accountService")
public class AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void addAccount(Account account){
        accountDao.save(account);
    }

    public Account getAccountById(Integer id){
        return accountDao.findById(id).orElse(new Account());
    }

    public List<Account> getAllAccounts(){
        List<Account> result = new ArrayList<>();
        accountDao.findAll().forEach(result::add);
        return result;
    }

    public Account loginAccount(String login, String password){
        return accountDao.findAccountByLoginAndPassword(login, password);
    }

    public void deleteAccount(Account account){
        accountDao.delete(account);
    }

    public void deleteAllAccounts(){
        accountDao.deleteAll();
    }

    public Account updateAccount(Account account){
        return accountDao.save(account);
    }
}
