package br.edu.iftm.project.easyconsult.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Crypto {
    private final BCryptPasswordEncoder senhaEnconder = new BCryptPasswordEncoder();

    // Criptografia de senha
    public String criptografarSenha(String senha) {
        return senhaEnconder.encode(senha);
    }

    //verifica senha
    
public boolean verificarSenha(String senha, String senhaCriptografada) {
        return senhaEnconder.matches(senha, senhaCriptografada);
    }
}
