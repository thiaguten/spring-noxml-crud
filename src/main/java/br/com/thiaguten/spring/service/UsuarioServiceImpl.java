package br.com.thiaguten.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiaguten.spring.dao.UsuarioDAO;
import br.com.thiaguten.spring.model.Usuario;

@Service("usuarioService")
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioDAO usuarioDAO;

	@Autowired
	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Usuario salvarOuAtualizar(Usuario usuario) {
		if (usuario.hasID()) {
			return usuarioDAO.alterar(usuario);
		} else {
			return usuarioDAO.salvar(usuario);
		}
	}

	@Override
	public Usuario recuperar(Long id) {
		return usuarioDAO.recuperar(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletarPorId(Long id) {
		usuarioDAO.deletarPorId(id);
	}

	@Override
	public List<Usuario> listar() {
		return usuarioDAO.listar();
	}

	@Override
	public List<Usuario> pesquisar(Usuario usuario) {
		return usuarioDAO.pesquisar(usuario);
	}

	@Override
	public boolean isEmailJaCadastrado(Usuario usuario) {
		return usuarioDAO.contarPorEmail(usuario) > 0;
	}

}
