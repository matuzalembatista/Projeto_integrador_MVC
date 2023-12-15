package br.edu.iftm.project.easyconsult.DAO;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import br.edu.iftm.project.easyconsult.Service.Crypto;

@Repository
public class LoginDAO {

    private final JdbcTemplate jdbcTemplate;

    public LoginDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Autowired
    Crypto crypto;

    public boolean VerificaSenha(String senha, String usuario) {
        String query = "SELECT senha FROM Login  WHERE usuario = ?";

        try {
            String senhaArmazenada = jdbcTemplate.queryForObject(query, String.class, usuario);
            return crypto.verificarSenha(senhaArmazenada, senha);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean SenhaMatch(String usuario, String senha) {
        String query = "SELECT senhaEncoded FROM Login WHERE usuario = ?";
        
    
        try {
            String senhaArmazenada = jdbcTemplate.queryForObject(query, String.class, usuario);
            
            if (senhaArmazenada == null) {
                return false;
            }else{
           System.out.println("Senha verificada!");
          return crypto.verificarSenha(senha, senhaArmazenada);
          
            }

           
           

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Deu erro ou alguma coisa está errada!\n Verficar se valores estão nulos");
            return false;
        }
    }
}
