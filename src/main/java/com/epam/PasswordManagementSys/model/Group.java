package com.epam.PasswordManagementSys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "account_group")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private Long groupId;

    @NotBlank(message = "Account Name Can't Be Empty!!")
    @Size(min = 3, max = 20, message = "Account name Should have min 3 chars and max 20 chars")
    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Account> accounts;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Group(String groupName) {
        this.groupName = groupName;
    }
}

