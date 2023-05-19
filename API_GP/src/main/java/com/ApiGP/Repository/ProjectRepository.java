package com.ApiGP.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ApiGP.Models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    public Optional<Project> findByNom(String nom_projet);

    // public Iterable<User> findByrole(String email);

}
