package com.example.TaxiPark.model.entyti;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER,DISPATCHER,MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
