package com.globallogic.bci.repository;

import com.globallogic.bci.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface IUserRepository extends JpaRepository<User, Id> {

    User findByToken(String token);
}
