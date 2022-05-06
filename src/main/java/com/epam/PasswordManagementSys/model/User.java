package com.epam.PasswordManagementSys.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Account> accountList;


    @OneToMany(mappedBy = "user")
    private List<Group> group;

    public User(String userName, String password, Collection<Account> accountList) {
        this.userName = userName;
        this.password = password;
        this.accountList = accountList;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
