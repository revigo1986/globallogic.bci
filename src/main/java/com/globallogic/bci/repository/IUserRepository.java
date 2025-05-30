package com.globallogic.bci.repository;

import com.globallogic.bci.model.Phone;
import com.globallogic.bci.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Phone> {

    Optional<User> findByToken(String token);
}
