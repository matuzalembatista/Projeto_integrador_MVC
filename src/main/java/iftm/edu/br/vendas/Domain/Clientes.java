package iftm.edu.br.vendas.Domain;

import ch.qos.logback.core.net.server.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    private long cod_cliente;
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    private String sexo;
    private String cidade;
    private String uf;


    public Client findByCpf(String cpf2) {
        return null;
    }

}