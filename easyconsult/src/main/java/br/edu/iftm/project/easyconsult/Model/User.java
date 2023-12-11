package br.edu.iftm.project.easyconsult.Model;



import java.time.LocalDate;


//Configuração Correta


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;




@Data
@AllArgsConstructor
@Entity

@Table(name="Clientes")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String usuario;
 
  


    
   @DateTimeFormat(pattern = "yy-MM-dd")
    private LocalDate data_nascimento;
    private String senha;
    private String confirmeSenha;
    

   

   

    public User(){}
}
