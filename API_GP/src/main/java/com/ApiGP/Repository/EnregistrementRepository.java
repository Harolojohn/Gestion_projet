package com.ApiGP.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ApiGP.Models.Enregistrement;
import com.ApiGP.Models.Project;
import com.ApiGP.Models.User;

@Repository
public interface EnregistrementRepository extends CrudRepository<Enregistrement, Integer> {

    public Iterable<Enregistrement> findByProject(Project project);

    public Iterable<Enregistrement> findByUser(User user);

}
