package br.com.thiaguten.spring.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.thiaguten.spring.dao.base.AbstractDAO;
import br.com.thiaguten.spring.model.Usuario;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDAO<Usuario, Long> implements UsuarioDAO {
	
	@Override
	public List<Usuario> pesquisar(Usuario usuario) {
		if (usuario != null) {
			List<Criterion> criterios = new ArrayList<Criterion>();
			if (StringUtils.hasText(usuario.getNome())) {
				criterios.add(Restrictions.ilike("nome", usuario.getNome(), MatchMode.ANYWHERE));
			}
			return pesquisarPorCriteria(criterios);
		}
		return Collections.emptyList();
	}
	
	@Override
	public Long contarPorEmail(Usuario usuario) {
		if (usuario != null && StringUtils.hasText(usuario.getNome())) {
			List<Criterion> criterios = new ArrayList<Criterion>();
			criterios.add(Restrictions.ilike("email", usuario.getEmail(), MatchMode.EXACT));
			if (usuario.hasID()) {
				// necessario para excluir da contagem a propria entidade que esta sendo alterada
				criterios.add(Restrictions.ne("id", usuario.getId()));
			}
			return contarPorCriteria(criterios);
		}
		return 0L;
	}
	
}
