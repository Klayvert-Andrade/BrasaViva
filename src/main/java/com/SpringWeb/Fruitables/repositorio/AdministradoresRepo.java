package com.SpringWeb.Fruitables.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringWeb.Fruitables.models.Administrador;


public interface AdministradoresRepo extends JpaRepository<Administrador, Integer>{
    
    @Query(value="SELECT CASE WHEN COUNT(1) > 0 THEN 'true' ELSE 'false' END FROM administradores WHERE id = :id", nativeQuery = true)
    public boolean existById(int id);

    @Query(value = "SELECT * FROM administradores WHERE email = :email AND senha = :senha", nativeQuery = true)
    public Administrador login(String email, String senha);
    
}
