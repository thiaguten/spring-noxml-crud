package br.com.thiaguten.spring.dao;

import java.util.List;

import br.com.thiaguten.spring.dao.base.DAO;
import br.com.thiaguten.spring.model.Usuario;

public interface UsuarioDAO extends DAO<Usuario, Long> {

	// definicao de metodos mais especificos de usuario - nao genericos

	Long contarPorEmail(Usuario usuario);

	List<Usuario> pesquisar(Usuario usuario);
}
