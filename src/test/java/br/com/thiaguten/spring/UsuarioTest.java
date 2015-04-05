package br.com.thiaguten.spring;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.thiaguten.spring.config.RootConfig;
import br.com.thiaguten.spring.model.Usuario;
import br.com.thiaguten.spring.service.UsuarioService;

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

	@Test
	public void emailJaCadastradoTest() {
		try {
			Usuario usuario1 = new Usuario("Usuario1", 27, "email@email.com");
			Usuario usuario2 = new Usuario("Usuario2", 30, "email@email.com");
			assertEquals(usuario1.getEmail(), usuario2.getEmail());
			usuarioService.salvarOuAtualizar(usuario1);
			usuarioService.salvarOuAtualizar(usuario2);
		} catch (PersistenceException e) {
			assertThat(e.getCause().getCause().getMessage(),
					anyOf(
							startsWith("integrity constraint violation: unique constraint or index violation"), // hsqldb constraint message
							startsWith("Unique index or primary key violation") // h2 constraint message
					));
		}
	}
}
