package salao.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import salao.sistema.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByEmail(String email);
}
