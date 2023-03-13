package com.example.TaxiPark.service;

import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.repository.UserRepos;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepos userRepos;

    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    public void saveUser(User user) {
        userRepos.save(user);
    }

    public void updateHomeAddress(Long userId, String homeAddress) {
        userRepos.setHomeAddressById(homeAddress, userId);
    }

    public User getUserById(Long id) {
        return userRepos.findById(id).get();
    }
}
