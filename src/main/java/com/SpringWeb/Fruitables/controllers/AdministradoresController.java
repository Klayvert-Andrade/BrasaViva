package com.SpringWeb.Fruitables.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.repositorio.AdministradoresRepo;


@Controller
public class AdministradoresController {

    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/administradores")
    public String index(Model model) {
        List<Administrador> administradores = (List<Administrador>)repo.findAll();
        model.addAttribute("administradores", administradores);
        
        return "administrativo/administradores/index";
    }

    @GetMapping("/administradores/novo")
    public String novo() {
        return "administrativo/administradores/novo";
    }

    @PostMapping("/administradores/criar")
    public String criar(Administrador administrador) {
        repo.save(administrador);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}/excluir")
    public String excluir(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}")
    public String buscar(@PathVariable int id, Model model) {
        Optional <Administrador> admin =  repo.findById(id);
        try {
            model.addAttribute("administrador", admin.get());
        } catch(Exception erro){
            return "redirect:/administradores";
        }
        
        return "administrativo/administradores/editar";
    }

    @PostMapping("/administradores/{id}/atualizar")
    public String atualizar(@PathVariable int id, Administrador administrador) {
        if(!repo.existsById(id)) {
            return "redirect:/administradores";
        }

        repo.save(administrador);
        
        return "redirect:/administradores";
    }    
    
    @GetMapping("/relatorios/administradores")
    public String listar(Model model) {
        List<Administrador> administradores = (List<Administrador>)repo.findAll();
        model.addAttribute("administradores", administradores);

        return "administrativo/administradores/lista";
    }  
} 


