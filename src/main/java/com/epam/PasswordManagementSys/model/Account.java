package com.epam.PasswordManagementSys.model;

import com.epam.PasswordManagementSys.constants.Constants;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    @NotBlank(message = "Account Name Can't Be Empty!!")
    @Size(min = 5, max = 20, message = "Account name Should have min 5 chars and max 20 chars")
    @Pattern(regexp = Constants.ACCOUNT_REGEX, message = "Account Name should be at least  one lower and one upper case, alphabets and can have underscore(_)")
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @NotBlank(message = "User Name Can't Be Empty!!")
    @Size(min = 5, max = 30, message = "User Name should be of min 5 chars and max 30 chars")
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotBlank(message = "URL Can't Be Empty!!")
    @Pattern(regexp = Constants.URL_REGEX, message = "Enter The Valid URL with http://")
    @Column(name = "url", nullable = false)
    private String url;

    @NotBlank(message = "Password Can't Be Empty!!")
    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Password Should Contain Lower case , Upper case , Numbers and Special chars with min of 8 chars")
    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date accountCreatingDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date accountModifyDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Group.class)
    @JoinColumn(name = "group_id")
    private Group group;

    public Account(String accountName, String userName, String url, String password, Date accountCreatingDate, Date accountModifyDate) {
        this.accountName = accountName;
        this.userName = userName;
        this.url = url;
        this.password = password;
        this.accountCreatingDate = accountCreatingDate;
        this.accountModifyDate = accountModifyDate;
    }

    public Account(String accountName, String userName, String url, String password) {
        this.accountName = accountName;
        this.userName = userName;
        this.url = url;
        this.password = password;
    }

    public Account(Integer accountId, String accountName, String userName, String url, String password) {
        this.accountName = accountName;
        this.userName = userName;
        this.url = url;
        this.password = password;
        this.accountId = accountId;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }
}
