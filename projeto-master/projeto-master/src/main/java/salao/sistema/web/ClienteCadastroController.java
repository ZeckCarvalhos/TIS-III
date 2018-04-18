package salao.sistema.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import salao.sistema.model.Cliente;
import salao.sistema.service.ClienteService;
import salao.sistema.web.dto.ClienteCadastroDto;



@Controller
@RequestMapping("/cadastroCliente")
public class ClienteCadastroController {
	 @Autowired
	 private ClienteService clienteService;

	 @ModelAttribute("cliente")
	 public ClienteCadastroDto clienteCadastroDto() {
		 return new ClienteCadastroDto();
	 }
	 
	 @GetMapping
	    public String showRegistrationForm(Model model) {
	        return "cadastroCliente";
	    }

	 @PostMapping
	 public String registerUserAccount(@ModelAttribute("cliente") @Valid ClienteCadastroDto clienteDto,
	                                      BindingResult result){

		 Cliente existing = clienteService.findByEmail(clienteDto.getEmail());
	     if (existing != null){
	    	 result.rejectValue("email", null, "There is already an account registered with that email");
	     }

	     if (result.hasErrors()){
	    	 return "cadastroCliente";
	     }

	     clienteService.save(clienteDto);
	     return "redirect:/login?cadastroSuccess";
	    }
}
