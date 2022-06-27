package com.emsi.meteo.app.ws.entities;

import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
@Data
public class GroupEntity implements Serializable {
    private static final long serialVersionUID = -213275949982276526L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome",length = 30)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "groups_users",
            joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private Set<UserEntity> users= new HashSet<>();
}
