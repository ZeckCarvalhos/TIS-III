package salao.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import salao.sistema.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{
	Profissional findByEmail(String email);
}
