package com.SpringWeb.Fruitables.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import com.SpringWeb.Fruitables.models.Cliente;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addClienteToModel(HttpServletRequest request, Model model) {
        Cliente clienteLogado = (Cliente) request.getSession().getAttribute("cliente");
        if (clienteLogado != null) {
            model.addAttribute("cliente", clienteLogado); // Adiciona o cliente ao modelo para todas as páginas
        }
    }
}
