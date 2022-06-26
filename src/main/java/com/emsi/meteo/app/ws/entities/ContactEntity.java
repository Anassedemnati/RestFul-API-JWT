package com.emsi.meteo.app.ws.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity @Data
public class ContactEntity implements Serializable {
    private static final long serialVersionUID = -2516275549986276526L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(length = 30)
    private String contactId;
    @NotBlank
    private String mobile;
    private String skype;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
