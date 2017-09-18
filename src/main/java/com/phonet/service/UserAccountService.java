package com.phonet.service;


import com.phonet.model.UserAccount;
import com.phonet.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAccountService implements CRUDService<UserAccount> {

    @Autowired
    UserAccountRepository userAccountRepository;


    @Override
    public UserAccount findById(Long id) {
        return userAccountRepository.findOne(id);
    }

    @Override
    public void save(UserAccount entity) {
        userAccountRepository.save(entity);
    }

    @Override
    public void update(UserAccount entity) {
        save(entity);
    }

    @Override
    public void deleteById(Long id) {
        userAccountRepository.delete(id);
    }

    @Override
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public boolean isExist(UserAccount entity) {
        return userAccountRepository.findByNumber(entity.getNumber()) != null;
    }

    public UserAccount findByNumber(String number){
        return userAccountRepository.findByNumber(number);
    }

    public void cashOperation(UserAccount userAccount) {
        Double balance = userAccount.getCash();
        double withDraw = userAccount.getWithDraw();
        save(userAccount);
        double diff = balance - withDraw;
        if (balance > 0 && diff > 0) {
            userAccount.setCash(diff);
        }
    }

}
