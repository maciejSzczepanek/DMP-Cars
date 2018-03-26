package com.sda.dmpcars.security;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetails implements UserDetailsService {

    @Autowired
    AccountDao accountDao;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountDao.findByUsername(s);
        if(account == null)
            throw new UsernameNotFoundException(s);

        return new AccountPrincipal(account);
    }
}
