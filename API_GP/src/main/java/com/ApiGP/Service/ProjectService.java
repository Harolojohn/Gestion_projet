package com.ApiGP.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiGP.Models.Project;
import com.ApiGP.Models.ProjectCredentials;
import com.ApiGP.Models.UpdateProjectCredentials;
import com.ApiGP.Models.User;
import com.ApiGP.Repository.ProjectRepository;
import com.ApiGP.Repository.UserRepository;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Project Create_project(ProjectCredentials project) {

	Optional<User> user = userRepository.findById(project.getId_user());

	Project projectSaved = new Project();

	Optional<Project> ProjectFind = projectRepository.findByNom(project.getNom_projet());

	if (user.isEmpty()) {

	    projectSaved.setNom("#Not Found");

	    return projectSaved;

	} else {

	    if (user.get().getRole().equals("administrateur")) {

		if (Objects.isNull(ProjectFind)) {
		    // projet vide
		    projectSaved.setNom(project.getNom_projet());
		    projectSaved.setBudget(project.getBudget());
		    projectSaved.setTaux_horaire(project.getTaux_horaire());
		    projectSaved.setUser(user.get());

		    Long datetime = System.currentTimeMillis();
		    Timestamp date = new Timestamp(datetime);

		    projectSaved.setDate_creation(date);

		    projectSaved = projectRepository.save(projectSaved);

		    return projectSaved;

		} else {

		    projectSaved.setNom("#Rename");

		    return projectSaved;
		}
	    } else {

		projectSaved.setNom("#Unauthorized");

		return projectSaved;
	    }
	}
    }

    public Iterable<Project> get_allprojects() {

	return projectRepository.findAll();
    }

    public Optional<Project> get_project_by_id(int id_project) {

	return projectRepository.findById(id_project);
    }

    public Project update_project(UpdateProjectCredentials project) {

	Optional<Project> projectUpdate = projectRepository.findById(project.getId());

	if (projectUpdate.isEmpty()) {

	    Project project_null = new Project();

	    return project_null;

	} else {

	    projectUpdate.get().setNom(project.getNom_projet());
	    projectUpdate.get().setBudget(project.getBudget());
	    projectUpdate.get().setTaux_horaire(project.getTaux_horaire());

	    return projectRepository.save(projectUpdate.get());
	}
    }

    public int delete_project_by_id(int id_project) {

	Optional<Project> projectDelete = projectRepository.findById(id_project);

	if (!projectDelete.isEmpty()) {

	    projectRepository.deleteById(id_project);

	    return 1;

	} else {

	    return 0;
	}
    }
}
