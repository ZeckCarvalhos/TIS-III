package salao.sistema.repository;

import org.springframework.data.repository.CrudRepository;
import salao.sistema.model.Servico;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
    Servico findByNomeServico(String nomeServico);
}