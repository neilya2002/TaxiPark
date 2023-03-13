package com.example.TaxiPark.model.entyti;

import javax.persistence.*;

@Entity
@Table(name = "usr_text")
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @OneToOne
    private User user;

    public UserMessage(Long id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public UserMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
