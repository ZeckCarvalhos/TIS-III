package salao.sistema.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salao.sistema.model.Servico;
import salao.sistema.repository.ServicoRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/cadastroServico")
public class ServicoCadastroController {

    @Autowired
    private ServicoRepository servicoRepository;

    @ModelAttribute("servico")
    public Servico servico() {
        return new Servico();
    }

    @GetMapping
    public String cadastroServicos(Model model) {return "cadastroServico";}

    @PostMapping
    public String cadastrarServico(@ModelAttribute("servico") @Valid Servico servico,
                                   BindingResult result){
        Servico existing = servicoRepository.findByNomeServico(servico.getNomeServico());
        if (existing != null){
            //result.rejectValue("cliente", null, "Cliente já existe");
            return "redirect:/cadastroServico?servicoExiste";
        }

        if (result.hasErrors()){
            return "cadastroServico";//erro
        }

        servicoRepository.save(servico);
        return "redirect:/cadastroServico?success";
    }

}
