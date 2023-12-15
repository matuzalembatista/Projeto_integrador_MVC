package br.edu.iftm.project.easyconsult.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.edu.iftm.project.easyconsult.Model.Vendas;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Date;


@Component
public class VendasDao {

    @Autowired
    private JdbcTemplate db;

    public List<Vendas> getVendas() {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, " +
                     "p.cod_prod AS cod_prodProduto, p.descricao, " +
                     "COALESCE(iv.qteVendida, 0) AS qteVendida, COALESCE(iv.precoVenda, 0.00) AS precoVenda, " +
                     "v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod " +
                     "LEFT JOIN itemVenda iv ON v.codVenda = iv.codVenda AND p.cod_prod = iv.cod_prod";
        return db.query(sql, new VendasRowMapper());
    }
    

    public Vendas getVenda(Long codVenda) {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod " +
                     "WHERE v.codVenda = ?";
        return db.queryForObject(sql, new VendasRowMapper(), codVenda);
    }
    
    
    public void inserirVenda(Vendas venda) {
        if (venda != null) {
            db.update("INSERT INTO Vendas(Cliente_cod_cliente, Produto_cod_prod, valorTotal, dataVenda) VALUES (?, ?, ?, ?)",
                    venda.getCod_cliente(), venda.getCod_prodProduto(), venda.getValorTotal(), venda.getDataVenda());
        }
    }
    

    public void updateVenda(Vendas venda) {
        if (venda != null) {
            db.update("UPDATE Vendas SET Cliente_cod_cliente=?, Produto_cod_prod=?, valorTotal=?, dataVenda=? WHERE codVenda=?", 
                    venda.getCod_cliente(), venda.getCod_prodProduto(), venda.getValorTotal(), venda.getDataVenda(), venda.getCodVenda());
    
            // Atualize a tabela itemVenda com os novos valores
            db.update("UPDATE itemVenda SET qteVendida=?, precoVenda=? WHERE codVenda=?", 
                    venda.getQteVendida(), venda.getPrecoVenda(), venda.getCodVenda());
        }
    }
    
    public void deleteVenda(Long codVenda) {
        db.update("DELETE FROM Vendas WHERE codVenda=?", codVenda);
    }

    private static class VendasRowMapper implements RowMapper<Vendas> {
        @Override
        public Vendas mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vendas venda = new Vendas();
            venda.setCodVenda(rs.getLong("codVenda"));
            Date dataVenda = rs.getDate("dataVenda");
            venda.setDataVenda(dataVenda != null ? ((java.sql.Date) dataVenda).toLocalDate() : null);
            venda.setCpfCliente(rs.getString("cpfCliente"));
            venda.setNomeCliente(rs.getString("nomeCliente"));
            venda.setCod_prodProduto(rs.getLong("cod_prodProduto"));
            venda.setDescricao(rs.getString("descricao"));
            venda.setValorTotal(rs.getDouble("valorTotal"));
    
            // Adicione estas linhas para verificar se as colunas existem antes de tentar extrair
            if (columnExists(rs, "qteVendida")) {
                venda.setQteVendida(rs.getDouble("qteVendida"));
            }
            if (columnExists(rs, "precoVenda")) {
                venda.setPrecoVenda(rs.getDouble("precoVenda"));
            }
    
            return venda;
        }
    
        private boolean columnExists(ResultSet rs, String columnName) {
            try {
                rs.findColumn(columnName);
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
    }
    

    public List<Vendas> getVendasByCliente(String cpfCliente, String nomeCliente) {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod " +
                     "WHERE c.CPF = ? AND c.nome = ?";
        return db.query(sql, new VendasRowMapper(), cpfCliente, nomeCliente);
    }

    public List<Vendas> getVendasByProduto(Long codProd, String descricaoProduto) {
        String sql = "SELECT v.codVenda, v.dataVenda, c.CPF AS cpfCliente, c.nome AS nomeCliente, p.cod_prod AS cod_prodProduto, p.descricao, v.valorTotal " +
                     "FROM Vendas v " +
                     "INNER JOIN Cliente c ON v.Cliente_cod_cliente = c.cod_cliente " +
                     "INNER JOIN Produto p ON v.Produto_cod_prod = p.cod_prod " +
                     "WHERE p.cod_prod = ? AND p.descricao = ?";
        return db.query(sql, new VendasRowMapper(), codProd, descricaoProduto);
    }

    public List<String> getAllCpfs() {
        String sql = "SELECT DISTINCT CPF FROM Cliente";
        return db.queryForList(sql, String.class);
    }

    public List<String> getAllCod_cliente() {
        String sql = "SELECT DISTINCT cod_cliente FROM Cliente";
        return db.queryForList(sql, String.class);
    }

    public List<Long> getAllCodProdutos() {
        String sql = "SELECT DISTINCT cod_prod FROM Produto";
        return db.queryForList(sql, Long.class);
    }

    public String getNomeByCPF(String cpf){
        try {
        String sql = "SELECT nome FROM Cliente WHERE CPF = ?";
        return db.queryForObject(sql, String.class, cpf);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }
    


    public Long getCodigoClienteByCPF(String cpfCliente) {
        return null;
    }
    
    



    
}
