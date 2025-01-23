package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ao.uan.finalproject.model.Professor;

@RepositoryRestResource(collectionResourceRel = "professor", path = "professor")
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
