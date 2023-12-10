package iftm.edu.br.vendas.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private long cod_produto;
    private String descricao;
    private String unidadeMedida;
    private double valorUnitario;
    private int qteComprada;
    private String nomeFornecedor;


}
