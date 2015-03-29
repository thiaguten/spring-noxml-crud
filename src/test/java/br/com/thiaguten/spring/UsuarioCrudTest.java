package br.com.thiaguten.spring;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import br.com.thiaguten.spring.config.RootConfig;
import br.com.thiaguten.spring.model.Usuario;
import br.com.thiaguten.spring.service.UsuarioService;

public class UsuarioCrudTest {

	private static final Logger log = LoggerFactory.getLogger(UsuarioCrudTest.class);
	
	private static AbstractApplicationContext ctx = null;
	
	private static UsuarioService usuarioService;
	
	@BeforeClass
	public static void setUp() {
		// classes de configuracao do spring
		ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(RootConfig.class);
		
		// xml de configuracao do spring
//		ctx = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath:spring/root-appContext.xml");
		
		usuarioService = ctx.getBean(UsuarioService.class);
	}
	
	@AfterClass
	public static void tearDown() {
		if (ctx != null) {
			ctx.close();
		}
	}
	
	@Test
	public void crudTest() {
		log.info("****************** INICIO ******************");
		// incluir
		Usuario usuario = new Usuario("Thiago", 27, "email@email.com");
		Assert.assertNull(usuario.getId());
		log.info("Incluindo: " + usuario);
		Usuario usuarioSalvo = usuarioService.salvarOuAtualizar(usuario);
		
		Assert.assertEquals(usuario, usuarioService.recuperar(usuarioSalvo.getId()));
		
		// alterar
		usuarioSalvo.setNome("Dayana");
		Assert.assertNotNull(usuarioSalvo.getId());
		Usuario usuarioAlterado = usuarioService.salvarOuAtualizar(usuarioSalvo);
		log.info("Atualizando: " + usuarioAlterado);
		
		Assert.assertEquals(usuarioAlterado, usuarioService.recuperar(usuarioAlterado.getId()));
		
		// listar
		List<Usuario> usuarios = usuarioService.listar();
		log.info("Listando: " + Arrays.deepToString(usuarios.toArray()));
		Assert.assertEquals(usuarios.size(), 1);
		
		// deletar
		usuarioService.deleteById(usuarioAlterado.getId());
		log.info("Deletando: " + usuarioAlterado);
		Assert.assertNull(usuarioService.recuperar(usuarioAlterado.getId()));
		log.info("****************** FIM ******************");
	}
}
