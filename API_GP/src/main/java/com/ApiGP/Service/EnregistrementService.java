package com.ApiGP.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiGP.Models.Enregistrement;
import com.ApiGP.Models.EnregistrementCredentials;
import com.ApiGP.Models.Project;
import com.ApiGP.Models.UpdateEnregistrementCredentials;
import com.ApiGP.Models.User;
import com.ApiGP.Repository.EnregistrementRepository;
import com.ApiGP.Repository.ProjectRepository;
import com.ApiGP.Repository.UserRepository;

@Service
@Transactional
public class EnregistrementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EnregistrementRepository enregsitrementRepository;

    public Enregistrement Create_enregsitrement(EnregistrementCredentials enregistrement) {

	Optional<User> userFind = userRepository.findById(enregistrement.getId_user());

	Enregistrement enregistrementSaved = new Enregistrement();

	if (!Objects.isNull(userFind.get())) {

	    Optional<Project> projectFind = projectRepository.findById(enregistrement.getId_project());

	    if (!Objects.isNull(projectFind)) {

		enregistrementSaved.setUser(userFind.get());
		enregistrementSaved.setProject(projectFind.get());
		enregistrementSaved.setDate_depart(enregistrement.getDate_depart());
		enregistrementSaved.setDate_fin(enregistrement.getDate_fin());

		Long datetime = System.currentTimeMillis();
		Timestamp date = new Timestamp(datetime);

		enregistrementSaved.setDate_creation(date);

		return enregsitrementRepository.save(enregistrementSaved);

	    } else {
		// project inexistant

		return null;
	    }
	} else {
	    // user inexistant

	    return null;
	}
    }

    public Enregistrement update_enregistrement(UpdateEnregistrementCredentials enregistrement) {

	Optional<Enregistrement> enregistrementUpdate = enregsitrementRepository
		.findById(enregistrement.getId_enregistrement());

	if (!enregistrementUpdate.isEmpty()) {

	    enregistrementUpdate.get().setDate_depart(enregistrement.getDate_depart());
	    enregistrementUpdate.get().setDate_fin(enregistrement.getDate_fin());

	    return enregsitrementRepository.save(enregistrementUpdate.get());

	} else {

	    Enregistrement enregistrement_null = new Enregistrement();

	    return enregistrement_null;
	}
    }

    public Iterable<Enregistrement> get_allenregistrements() {

	return enregsitrementRepository.findAll();
    }

    public Optional<Enregistrement> get_allenregistrements_by_id(int id_enregistrement) {

	return enregsitrementRepository.findById(id_enregistrement);
    }

    public Iterable<Enregistrement> get_allenregistrements_by_project(int id_project) {

	Optional<Project> projectFind = projectRepository.findById(id_project);

	if (projectFind.isEmpty()) {

	    return null;
	} else {

	    return enregsitrementRepository.findByProject(projectFind.get());
	}

    }

    public Iterable<Enregistrement> get_allenregistrements_by_user(int id_user) {

	Optional<User> userFind = userRepository.findById(id_user);

	if (userFind.isEmpty()) {

	    return null;
	} else {

	    return enregsitrementRepository.findByUser(userFind.get());
	}

    }
}
