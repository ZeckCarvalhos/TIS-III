package salao.sistema.model;

import javax.persistence.*;

@Entity
@Table
public class Servico {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String Descricao;
    private String Preco;
    private String nomeServico;

    public Integer getId() {
        return id;
    }

    public String getPreco() {
		return Preco;
	}



	public void setPreco(String preco) {
		Preco = preco;
	}



	public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", Preco='" + Preco + '\'' +
                ", Descrição='" + Descricao + '\'' +
                ", nomeServico='" + nomeServico + '\'' +
                '}';
    }
}
