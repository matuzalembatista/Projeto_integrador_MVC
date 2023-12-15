
package br.edu.iftm.project.easyconsult.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.project.easyconsult.DAO.LoginDAO;
import br.edu.iftm.project.easyconsult.Model.User;

@Controller
public class ControllerLogin {

    @Autowired
    private LoginDAO loginDAO;

    @GetMapping("/loginEasy")
    public String home(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "loginEasy";
    }

    @PostMapping("loginEasy")
    public String Auenticar(@ModelAttribute("user") User user, Model model) {
        if (loginDAO.SenhaMatch(user.getLogin(), user.getSenhaLogin())){
          
            return "redirect:/clientes";

        } else {
            
            model.addAttribute("erro", "Senha incorreta!");
            return "loginEasy";
        }
    }

    @GetMapping("/")
    public String pageLogin() {
        return "redirect:/loginEasy";
    }

    @PostMapping("register")
    public String PageCadastro() {
        return "CadastroEasy";
    }

    @GetMapping("RedirecionarLogin")
    public String LoginToCadastro() {
        return "redirect:/loginEasy";
    }

}