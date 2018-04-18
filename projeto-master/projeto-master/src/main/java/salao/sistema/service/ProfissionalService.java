package salao.sistema.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import salao.sistema.model.Profissional;
import salao.sistema.web.dto.ProfissionalCadastroDto;

public interface ProfissionalService extends UserDetailsService{
	Profissional findByEmail(String email);

	Profissional save(ProfissionalCadastroDto cadastro);
}
