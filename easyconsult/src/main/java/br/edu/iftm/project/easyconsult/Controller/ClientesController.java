package br.edu.iftm.project.easyconsult.Controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.project.easyconsult.DAO.ClientesDao;
import br.edu.iftm.project.easyconsult.Model.Clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class ClientesController {

    @Autowired
    private ClientesDao dao;

    @RequestMapping("clientes")
    public String getClientes(Model model) {
        model.addAttribute("cliente", new Clientes());
        model.addAttribute("clientes", dao.getClientes());
        model.addAttribute("edit", false);
        return "Clientes";
    }

    @PostMapping("addcliente")
    public String inserirCliente(Clientes cliente, Model model, RedirectAttributes redirectAttributes) {
        Clientes clienteDb = dao.getCliente(cliente.getCpf());
        if (clienteDb == null) {
            dao.inserirCliente(cliente);
        } else {
            dao.updateCliente(cliente);
        }
        redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/clientes";
    }

    @GetMapping("/delete")
    public String deleteCliente(@RequestParam(value = "cpf", required = true) String cpf, Model model) {
        dao.deleteCliente(cpf);
        return "redirect:/clientes";
    }

    @RequestMapping("/edit/{cpf}")
    public String editarCliente(@PathVariable String cpf, Model model) {
        Clientes cliente = dao.getCliente(cpf);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", dao.getClientes());
        model.addAttribute("edit", true);
        return "Clientes";
    }

    @GetMapping("/edit/{cpf}")
    public String mostrarEditForm(@PathVariable("cpf") String cpf, Model model) {
        Clientes cliente = dao.getCliente(cpf);
        if (cliente == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "editClientes";
    }
    @GetMapping("dashboard")
    public String dashbord(){
        return "redirect:/clientes";
    }
    @GetMapping("clienteToLogin")
    public String clienteToLogin() {
        return "redirect:/loginEasy";
    }
    @GetMapping("clienteToCadastro")
    public String clienteToCadastro() {
        return "redirect:/CadastroEasy";
    }
    @GetMapping("clienteToProduto")
    public String clienteToProduto() {
        return "redirect:/produtos";
    } @GetMapping("listToCliente")
    public String ListToCliente(){
        return "redirect:/clientes";
    }
}
