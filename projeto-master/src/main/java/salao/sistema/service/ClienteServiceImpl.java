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

import salao.sistema.model.Cliente;
import salao.sistema.model.Role;
import salao.sistema.repository.ClienteRepository;
import salao.sistema.web.dto.ClienteCadastroDto;


@Service
public class ClienteServiceImpl implements ClienteService{
	
	 @Autowired
	    private ClienteRepository clienteRepository;
	 
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	 
	 @Override
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Cliente cliente = clienteRepository.findByEmail(email);
		 if (cliente == null){
			 throw new UsernameNotFoundException("Invalid username or password.");
		 }
		 return new org.springframework.security.core.userdetails.User(cliente.getEmail(),
			 cliente.getPassword(),
			 mapRolesToAuthorities(cliente.getRoles()));
	 }
	 
	 public Cliente findByEmail(String email){
	        return clienteRepository.findByEmail(email);
	 }
	 
	 public Cliente save(ClienteCadastroDto cadastro){
	        Cliente cliente = new Cliente();
	        cliente.setNome(cadastro.getNome());
	        cliente.setRg(cadastro.getRg());
	        cliente.setCpf(cadastro.getCpf());
	        cliente.setEndereco(cadastro.getEndereco());
	        cliente.setEmail(cadastro.getEmail());
	        cliente.setUsuario(cadastro.getUsuario());
	        cliente.setPassword(passwordEncoder.encode(cadastro.getPassword()));
	        cliente.setRoles(Arrays.asList(new Role("ROLE_USER")));
	        return clienteRepository.save(cliente);
	    }
	 
	 private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
	        return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	 }
}
