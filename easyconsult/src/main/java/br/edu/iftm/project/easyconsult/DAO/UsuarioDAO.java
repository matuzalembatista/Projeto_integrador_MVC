package br.edu.iftm.project.easyconsult.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.project.easyconsult.Model.User;

@Repository
public class UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirCliente(User cliente) {
        String sql = "INSERT INTO Login (nome, usuario, data_nascimento, senha, senhaEncoded) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                cliente.getNome(),
                cliente.getUsuario(),
                cliente.getData_nascimento(),
                cliente.getSenha(),
                cliente.getSenhaEncoded()

        );

    }

    public boolean usuarioExiste(String usuario) {
        String query = "SELECT COUNT(*) FROM Login WHERE usuario = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, usuario);
        return count > 0;
    }

}
