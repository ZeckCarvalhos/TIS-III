package salao.sistema.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import salao.sistema.model.Profissional;
import salao.sistema.model.Role;
import salao.sistema.repository.ProfissionalRepository;
import salao.sistema.web.dto.ProfissionalCadastroDto;

@Service
public class ProfissionalServiceImpl implements ProfissionalService{
	@Autowired
    private ProfissionalRepository profissionalRepository;
 
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	 
	 @Override
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Profissional profissional = profissionalRepository.findByEmail(email);
		 if (profissional == null){
			 throw new UsernameNotFoundException("Invalid username or password.");
		 }
		 return new org.springframework.security.core.userdetails.User(profissional.getEmail(),
			 profissional.getPassword(),
			 mapRolesToAuthorities(profissional.getRoles()));
	 }
	 
	 public Profissional findByEmail(String email){
	        return profissionalRepository.findByEmail(email);
	 }
	 
	 public Profissional save(ProfissionalCadastroDto cadastro){
	        Profissional profissional = new Profissional();
	        profissional.setNome(cadastro.getNome());
	        profissional.setRg(cadastro.getRg());
	        profissional.setCpf(cadastro.getCpf());
	        profissional.setEndereco(cadastro.getEndereco());
	        profissional.setEmail(cadastro.getEmail());
	        profissional.setUsuario(cadastro.getUsuario());
	        profissional.setPassword(passwordEncoder.encode(cadastro.getPassword()));
	        profissional.setContaBancaria(cadastro.getContaBancaria());
	        //profissional.setServico(cadastro.getServico());
	        profissional.setRoles(Arrays.asList(new Role("ROLE_USER")));
	        return profissionalRepository.save(profissional);
	    }
	 
	 private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
	        return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	 }
}
