package iftm.edu.br.vendas.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {
    private long cod_prod;
    private String descricao;
    private String unidadeMedida;
    private double valorUnitario;
    private int qteComprada;
    private String nomeFornecedor;


}