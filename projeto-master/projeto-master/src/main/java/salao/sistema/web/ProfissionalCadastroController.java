package salao.sistema.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import salao.sistema.model.Profissional;
import salao.sistema.service.ProfissionalService;
import salao.sistema.web.dto.ProfissionalCadastroDto;

@Controller
@RequestMapping("/cadastroProfissional")
public class ProfissionalCadastroController {
	@Autowired
	 private ProfissionalService profissionalService;

	 @ModelAttribute("user")
	 public ProfissionalCadastroDto profissionalCadastroDto() {
		 return new ProfissionalCadastroDto();
	 }
	 
	 @PostMapping
	 public String registerUserAccount(@ModelAttribute("user") @Valid ProfissionalCadastroDto profissionalDto,
	                                      BindingResult result){

		 Profissional existing = profissionalService.findByEmail(profissionalDto.getEmail());
	     if (existing != null){
	    	 result.rejectValue("email", null, "There is already an account registered with that email");
	     }

	     if (result.hasErrors()){
	    	 return "cadastroCliente";
	     }

	     profissionalService.save(profissionalDto);
	     return "redirect:/login?cadastroSuccess";
	    }
}
