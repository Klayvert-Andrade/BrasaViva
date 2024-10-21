package com.SpringWeb.Fruitables.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringWeb.Fruitables.models.Cliente;


public interface ClientesRepo extends JpaRepository<Cliente, Integer>{
    @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from clientes where id = :id", nativeQuery = true)
    public boolean exist(int id);

    @Query(value="select * from clientes where email = :email and senha = :senha", nativeQuery = true)
    public Cliente login(String email, String senha);
    
    @Query(value = "select * from clientes where email = :email", nativeQuery = true)
    public Cliente findByEmail(String email);
}
