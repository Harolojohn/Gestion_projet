package com.ApiGP.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ApiGP.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);

    public Iterable<User> findByrole(String email);

}
