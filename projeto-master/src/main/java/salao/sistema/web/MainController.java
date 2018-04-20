package salao.sistema.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletRequest;
import java.util.Map;


@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(ServletRequest request, Model model) {
        Map<String, String[]> paramMap = request.getParameterMap();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)&& !paramMap.containsKey("logout")) {
            /* The user is logged in :) */
            return ("redirect:/");
        }
        return "login";
    }
    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}