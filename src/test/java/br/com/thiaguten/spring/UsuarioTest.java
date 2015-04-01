package br.com.thiaguten.spring;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
		Assert.assertNull(usuario.getId());
		Usuario usuarioSalvo = usuarioService.salvarOuAtualizar(usuario);

		// recuperar
		Assert.assertNotNull(usuarioSalvo.getId());
		Usuario usuarioRecuperado = usuarioService.recuperar(usuarioSalvo.getId());
		Assert.assertEquals(usuarioRecuperado, usuarioSalvo);

		// alterar
		usuarioRecuperado.setNome("Dayana");
		Assert.assertNotNull(usuarioRecuperado.getId());
		Usuario usuarioAlterado = usuarioService.salvarOuAtualizar(usuarioRecuperado);
		Assert.assertEquals(usuarioAlterado, usuarioRecuperado);

		// listar
		List<Usuario> listaUsuario = usuarioService.listar();
		Assert.assertEquals(1, listaUsuario.size());
		Assert.assertEquals(listaUsuario.get(0), usuarioAlterado);

		// deletar
		Assert.assertNotNull(usuarioAlterado.getId());
		usuarioService.deletarPorId(usuarioAlterado.getId());
		Usuario usuarioDeletado = usuarioService.recuperar(usuarioAlterado.getId());
		Assert.assertNull(usuarioDeletado);
	}

	@Test
	public void emailJaCadastradoTest() {
		try {
			Usuario usuario1 = new Usuario("Usuario1", 27, "email@email.com");
			Usuario usuario2 = new Usuario("Usuario2", 30, "email@email.com");
			Assert.assertEquals(usuario1.getEmail(), usuario2.getEmail());
			usuarioService.salvarOuAtualizar(usuario1);
			usuarioService.salvarOuAtualizar(usuario2);
		} catch (PersistenceException e) {
			Assert.assertThat(
					e.getCause().getCause().getMessage(),
					CoreMatchers.startsWith("integrity constraint violation: unique constraint or index violation"));
		}
	}
}
