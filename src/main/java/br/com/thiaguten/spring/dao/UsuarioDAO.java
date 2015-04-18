package br.com.thiaguten.spring.dao;

import br.com.thiaguten.spring.dao.base.DAO;
import br.com.thiaguten.spring.model.Usuario;

import java.util.List;

public interface UsuarioDAO extends DAO<Usuario, Long> {

    // definicao de metodos mais especificos de usuario - nao genericos

    Long contarPorEmail(Usuario usuario);

    List<Usuario> pesquisar(Usuario usuario);
}
