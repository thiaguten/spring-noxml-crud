/*
 * #%L
 * %%
 * Copyright (C) 2015 - 2016 Thiago Gutenberg Carvalho da Costa.
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Thiago Gutenberg Carvalho da Costa. nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package br.com.thiaguten.spring.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.spi.provider.hibernate.HibernateCriteriaPersistenceProvider;
import br.com.thiaguten.spring.model.Usuario;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends GenericBaseDAO<Usuario, Long> implements UsuarioDAO {

    @Autowired
    private HibernateCriteriaPersistenceProvider persistenceProvider;

    @Override
    public HibernateCriteriaPersistenceProvider getPersistenceProvider() {
        return persistenceProvider;
    }

    @Override
    public List<Usuario> pesquisar(Usuario usuario) {
        if (usuario != null) {
            List<Criterion> criterios = new ArrayList<>();
            if (StringUtils.hasText(usuario.getNome())) {
                criterios.add(Restrictions.ilike("nome", usuario.getNome(), MatchMode.ANYWHERE));
            }
            if (usuario.getIdade() != null) {
                criterios.add(Restrictions.eq("idade", usuario.getIdade()));
            }
            if (StringUtils.hasText(usuario.getEmail())) {
                criterios.add(Restrictions.ilike("email", usuario.getEmail(), MatchMode.ANYWHERE));
            }
            return persistenceProvider.findByCriteria(getEntityClass(), criterios);
        }
        return Collections.emptyList();
    }

    @Override
    public Long contarPorEmail(Usuario usuario) {
        if (usuario != null && StringUtils.hasText(usuario.getEmail())) {
            List<Criterion> criterios = new ArrayList<>();
            criterios.add(Restrictions.ilike("email", usuario.getEmail(), MatchMode.EXACT));
            if (usuario.hasID()) {
                // necessario para excluir da contagem a propria entidade que
                // esta sendo alterada
                criterios.add(Restrictions.ne("id", usuario.getId()));
            }
            return persistenceProvider.countByCriteria(getEntityClass(), Long.class, criterios);
        }
        return 0L;
    }

}
