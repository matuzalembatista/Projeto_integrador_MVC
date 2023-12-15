package br.edu.iftm.project.easyconsult.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.iftm.project.easyconsult.DAO.UsuarioDAO;
import br.edu.iftm.project.easyconsult.Model.User;
import br.edu.iftm.project.easyconsult.Service.Crypto;

@Controller
public class ControllerCadastro {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private Crypto crypto;

    @GetMapping("register")
    public String retornePage() {
        return "redirect:/CadastroEasy";
    }

    @GetMapping("/Cadastrar")
    public String PageCadastro() {
        return "CadastroEasy";
    }

    @GetMapping("/CadastroEasy")
    public String home(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return "CadastroEasy";

    }

    @PostMapping("RedirecionarLogin")
    public String LoginToCadastro() {
        return "loginEasy";
    }

    @PostMapping("/CadastroEasy")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        if (usuarioDAO.usuarioExiste(user.getUsuario())) {
            model.addAttribute("message", "Usuario existente");

            return "CadastroEasy";

        } else {
            user.setSenhaEncoded(crypto.criptografarSenha(user.getSenha()));
            usuarioDAO.inserirCliente(user);
            return "loginEasy";

        }

    }

}
