package com.viswateja.farmstead.Repository;

import com.viswateja.farmstead.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmailAddress(String email);
}
