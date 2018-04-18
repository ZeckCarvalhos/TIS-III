package salao.sistema.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import salao.sistema.model.Cliente;
import salao.sistema.web.dto.ClienteCadastroDto;

public interface ClienteService extends UserDetailsService{
	
	 Cliente findByEmail(String email);

	 Cliente save(ClienteCadastroDto cadastro);
}
