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
package br.com.thiaguten.spring;

import br.com.thiaguten.spring.config.RootConfig;
import br.com.thiaguten.spring.model.Usuario;
import br.com.thiaguten.spring.service.UsuarioService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void crudTest() {
        // incluir
        Usuario usuario = new Usuario("Thiago", 27, "email@email.com");
        assertNull(usuario.getId());
        Usuario usuarioSalvo = usuarioService.salvarOuAtualizar(usuario);

        // recuperar
        assertNotNull(usuarioSalvo.getId());
        Usuario usuarioRecuperado = usuarioService.recuperar(usuarioSalvo.getId());
        assertEquals(usuarioRecuperado, usuarioSalvo);

        // alterar
        usuarioRecuperado.setNome("Dayana");
        assertNotNull(usuarioRecuperado.getId());
        Usuario usuarioAlterado = usuarioService.salvarOuAtualizar(usuarioRecuperado);
        assertEquals(usuarioAlterado, usuarioRecuperado);

        // listar
        List<Usuario> listaUsuario = usuarioService.listar();
        assertEquals(1, listaUsuario.size());
        assertEquals(listaUsuario.get(0), usuarioAlterado);

        // deletar
        assertNotNull(usuarioAlterado.getId());
        usuarioService.deletarPorId(usuarioAlterado.getId());
        Usuario usuarioDeletado = usuarioService.recuperar(usuarioAlterado.getId());
        assertNull(usuarioDeletado);
    }

    @Test(expected = ConstraintViolationException.class)
    public void emailJaCadastradoTest() {
//        try {
            Usuario usuario1 = new Usuario("Usuario1", 27, "email@email.com");
            Usuario usuario2 = new Usuario("Usuario2", 30, "email@email.com");
            assertEquals(usuario1.getEmail(), usuario2.getEmail());
            usuarioService.salvarOuAtualizar(usuario1);
            usuarioService.salvarOuAtualizar(usuario2);
//        } catch (Exception e) {
//            assertThat(e.getCause().getCause().getMessage(),
//                    anyOf(
//                            startsWith("integrity constraint violation: unique constraint or index violation"), // hsqldb constraint message
//                            startsWith("Unique index or primary key violation") // h2 constraint message
//                    ));
//        }
    }
}
