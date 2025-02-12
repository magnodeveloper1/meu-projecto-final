package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.uan.finalproject.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}
