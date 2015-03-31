package br.com.thiaguten.spring.service;

import java.util.List;

import br.com.thiaguten.spring.model.Usuario;

public interface UsuarioService {

	boolean isEmailJaCadastrado(Usuario usuario);

	Usuario salvarOuAtualizar(Usuario usuario);

	Usuario recuperar(Long id);

	void deletarPorId(Long id);

	List<Usuario> listar();

	List<Usuario> pesquisar(Usuario usuario);

}
