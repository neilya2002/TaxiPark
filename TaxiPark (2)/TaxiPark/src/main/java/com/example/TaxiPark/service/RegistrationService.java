package com.example.TaxiPark.service;

import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.model.entyti.UserRole;
import com.example.TaxiPark.repository.UserRepos;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements UserDetailsService {
    private final UserRepos userRepos;

    public RegistrationService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    public void saveUser(User user) {
        user.setRole(UserRole.USER);
        userRepos.save(user);
    }

    public User getUpdatedUser(User user) {
      return userRepos.findById(user.getId()).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepos.findUserByName(username);
    }
}
