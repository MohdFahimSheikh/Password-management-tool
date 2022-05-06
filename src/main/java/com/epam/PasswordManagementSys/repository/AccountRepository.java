package com.epam.PasswordManagementSys.repository;

import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.Group;
import com.epam.PasswordManagementSys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    int countByAccountNameAndUser(String accountName , User user);
    List<Account> findByAccountIdAndUser(int accountId , User user);
    List<Account> findByUser(User user);
    List<Account> findByGroupAndUser(Group group, User user);
}
