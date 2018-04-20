package salao.sistema.model;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Profissional {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	
	private String nome;
	private String rg;
	private String cpf;
	private String endereco;
	private String email;
	private String usuario;
	private String password;
	private String contaBancaria;
	
	
	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(
	            name = "users_roles",
	            joinColumns = @JoinColumn(
	                    name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(
	                    name = "role_id", referencedColumnName = "id"))
	    private Collection<Role> roles;
	 
	// Construtor.
	public Profissional() {
	}
	 
	public Profissional(Integer id, String nome, String rg, String cpf, String endereco, String email, String usuario,
			String password, String contaBancaria, Servico servico) {
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.contaBancaria = contaBancaria;
	}
	
	public Profissional(Integer id, String nome, String rg, String cpf, String endereco, String email, String usuario,
			String password, String contaBancaria, Servico servico, Collection<Role> roles) {
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.contaBancaria = contaBancaria;
		//this.servico = servico;
		this.roles = roles;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String senha) {
		this.password = senha;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	/*
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	*/

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Profissional{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", usuario = " + usuario + '\'' +
                ", password='" + "*********" + '\'' +
                ", contaBancaria = " + contaBancaria + '\'' +
               // ", servico = " + servico + '\'' +
                ", roles=" + roles +
                '}';
	}


}
