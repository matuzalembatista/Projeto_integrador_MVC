package br.edu.iftm.project.easyconsult.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;




@Repository
public class LoginDAO {
    

    @Autowired
    private br.edu.iftm.project.easyconsult.Service.Crypto crypto;

    @Autowired JdbcTemplate jdbcTemplate;



    
    public boolean VerificaSenha(String senha, String usuario){
        String query ="SELECT senha FROM Clientes WHERE senha =?";
        String senhaArmazenada = jdbcTemplate.queryForObject(query, String.class, usuario);
        return crypto.verificarSenha(senhaArmazenada, senha);
    }
}
