package br.com.thiaguten.spring.service;

import br.com.thiaguten.spring.model.Usuario;

import java.util.List;

public interface UsuarioService {

    boolean isEmailJaCadastrado(Usuario usuario);

    Usuario salvarOuAtualizar(Usuario usuario);

    Usuario recuperar(Long id);

    void deletarPorId(Long id);

    List<Usuario> listar();

    List<Usuario> pesquisar(Usuario usuario);

}
