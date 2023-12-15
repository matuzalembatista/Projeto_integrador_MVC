package br.edu.iftm.project.easyconsult.Controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.project.easyconsult.DAO.VendasDao;
import br.edu.iftm.project.easyconsult.Model.Vendas;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class VendasController {

    @Autowired
    private VendasDao vendasDao;

    @RequestMapping("/vendas")
    public String getVendas(Model model) {
        model.addAttribute("venda", new Vendas());
        model.addAttribute("vendasList", vendasDao.getVendas());
        model.addAttribute("edit", false);
        return "Vendas";
    }

    @RequestMapping("/vendasList")
    public String listarVendas(Model model) {
        List<Vendas> vendasList = vendasDao.getVendas();
        model.addAttribute("vendasList", vendasList);
        return "vendasList";
    }

    @PostMapping("/addvenda")
    public String inserirVenda(@ModelAttribute("venda") Vendas venda, Model model,
            RedirectAttributes redirectAttributes) {
        String cpfCliente = venda.getCpfCliente();
        String nomeCliente = vendasDao.getNomeByCPF(cpfCliente);

        venda.setNomeCliente(nomeCliente);

        vendasDao.inserirVenda(venda);

        redirectAttributes.addFlashAttribute("mensagem", "Venda cadastrada com sucesso!");

        return "redirect:/vendas";
    }

    @GetMapping("/deletevenda/{codVenda}")
    public String deleteVenda(@PathVariable Long codVenda, Model model, RedirectAttributes redirectAttributes) {
        vendasDao.deleteVenda(codVenda);
        // Adicione a venda excluída à lista de vendas
        model.addAttribute("vendasList", vendasDao.getVendas());
        redirectAttributes.addFlashAttribute("mensagem", "Venda excluída com sucesso!");
        return "redirect:/vendasList";
    }

    @RequestMapping("/editvenda/{codVenda}")
    public String editarVenda(@PathVariable Long codVenda, Model model) {
        Vendas venda = vendasDao.getVenda(codVenda);
        model.addAttribute("venda", venda);
        model.addAttribute("allCod_cliente", vendasDao.getAllCod_cliente());
        model.addAttribute("allCodProdutos", vendasDao.getAllCodProdutos());
        model.addAttribute("vendasList", vendasDao.getVendas());
        model.addAttribute("edit", true);
        return "editVendas";
    }
    

    @PostMapping("/updatevenda")
    public String updateVenda(@ModelAttribute("venda") Vendas venda, Model model,
            RedirectAttributes redirectAttributes) {
        vendasDao.updateVenda(venda);

        redirectAttributes.addFlashAttribute("mensagem", "Venda atualizada com sucesso!");

        return "redirect:/vendasList";
    }

    @GetMapping("/vendasByProduto")
    public String getVendasByProduto(@RequestParam(value = "codProd", required = true) Long codProd,
            @RequestParam(value = "descricaoProduto", required = true) String descricaoProduto,
            Model model) {
        List<Vendas> vendasByProduto = vendasDao.getVendasByProduto(codProd, descricaoProduto);
        model.addAttribute("vendasList", vendasByProduto);

        // Mantenha o nome da página para exibição dos resultados
        return "Vendas";
    }

    @GetMapping("/vendasByCliente")
    public String getVendasByCliente(@RequestParam(value = "cpfCliente", required = true) String cpfCliente,
            @RequestParam(value = "nomeCliente", required = true) String nomeCliente,
            Model model) {
        List<Vendas> vendasByCliente = vendasDao.getVendasByCliente(cpfCliente, nomeCliente);
        model.addAttribute("vendasList", vendasByCliente);

        // Mantenha o nome da página para exibição dos resultados
        return "Vendas";
    }

    @ModelAttribute("allCpfs")
    public List<String> getAllCpfs() {
        // Use o seu VendasDao para buscar todos os CPFs do banco de dados
        return vendasDao.getAllCpfs();
    }

    @ModelAttribute("allCodProdutos")
    public List<Long> getAllCodProdutos() {
        return vendasDao.getAllCodProdutos();
    }

    @ModelAttribute("allCod_cliente")
    public List<String> getAllCod_cliente() {
        return vendasDao.getAllCod_cliente();
    
    

    
}
@GetMapping("vendasToList")
    public String vendasToLogin(){
        return "redirect:/vendasList";
    }
   @GetMapping("clienteToVenda")
   public String clienteToVenda() {
       return "redirect:/vendas";
   }
   
}