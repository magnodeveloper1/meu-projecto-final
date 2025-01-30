package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.uan.finalproject.domain.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
}
