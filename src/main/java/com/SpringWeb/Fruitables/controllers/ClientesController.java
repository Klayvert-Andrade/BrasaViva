package com.SpringWeb.Fruitables.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.repositorio.ClientesRepo;


@Controller
public class ClientesController {

    @Autowired
    private ClientesRepo repo;

    @GetMapping("/clientes")
    public String index(Model model) {
        List<Cliente> clientes = (List<Cliente>)repo.findAll();
        model.addAttribute("clientes", clientes);
        
        return "administrativo/clientes/index";
    }

    // Exibir formulário para criar um novo cliente
    @GetMapping("/clientes/novo")
    public String novo() {
        return "administrativo/clientes/novo";
    }

    // Processar o formulário de criação de novo cliente
    @PostMapping("/clientes/criar")
    public String criar(Cliente cliente) {
        repo.save(cliente);
        return "redirect:/clientes";
    }

    // Processar o formulário de criação de novo cliente
    @PostMapping("/checkout/criar")
    public String criarCheckout(Cliente cliente, RedirectAttributes redirectAttributes) {
        repo.save(cliente);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente cadastrado com sucesso!");
        return "redirect:/checkout"; 
    }

    @GetMapping("/clientes/{id}/excluir")
    public String excluir(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}")
    public String buscar(@PathVariable int id, Model model) {
        Optional <Cliente> cliente =  repo.findById(id);
        try {
            model.addAttribute("cliente", cliente.get());
        } catch(Exception erro){
            return "redirect:/clientes";
        }
        
        return "administrativo/clientes/editar";
    }

    @PostMapping("/clientes/{id}/atualizar")
    public String atualizar(@PathVariable int id, Cliente cliente) {
        if(!repo.existsById(id)) {
            return "redirect:/clientes";
        }
        cliente.setId(id); 
        repo.save(cliente);
        
        return "redirect:/clientes";
    }

    @GetMapping("/relatorios/clientes")
    public String listar(Model model) {
        List<Cliente> clientes = (List<Cliente>)repo.findAll();
        model.addAttribute("clientes", clientes);

        return "administrativo/clientes/lista";
    }  

    
        
} 


