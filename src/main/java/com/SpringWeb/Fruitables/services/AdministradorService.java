package com.SpringWeb.Fruitables.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.repositorio.AdministradoresRepo;

@Service
public class AdministradorService {

    @Autowired
    private AdministradoresRepo administradorRepo;

    public Administrador findById(int id) {
        
        Optional<Administrador> administradorOpt = administradorRepo.findById(id);
        return administradorOpt.orElse(null);
    }

}
