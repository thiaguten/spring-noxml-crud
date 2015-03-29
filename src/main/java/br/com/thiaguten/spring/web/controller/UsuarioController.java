package br.com.thiaguten.spring.web.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
//
//import br.com.exemplo.model.Usuario;
//import br.com.exemplo.service.UsuarioService;



import br.com.thiaguten.spring.model.Usuario;
import br.com.thiaguten.spring.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	private static final String LISTAR_PAGE = "/usuario/listarUsuario";
    private static final String MANTER_PAGE = "/usuario/manterUsuario";
    private static final String USUARIO_KEY = "usuario";
    private static final String USUARIOS_KEY = "usuarios";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Validator usuarioValidator;
    
    @Autowired
    private MessageSource messageSource;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(usuarioValidator);
    }
    
    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo() {
        return new ModelAndView(MANTER_PAGE, USUARIO_KEY, new Usuario());
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute(USUARIO_KEY, new Usuario());
        model.addAttribute(USUARIOS_KEY, usuarioService.listar());
        return LISTAR_PAGE;
    }

    @RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
    public String remover(@PathVariable("id") long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuario/listar";
    }

    @RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
    public ModelAndView alterar(@PathVariable("id") long id) {
        return new ModelAndView(MANTER_PAGE, USUARIO_KEY, usuarioService.recuperar(id));
    }

    @RequestMapping(value = "/manter", method = {RequestMethod.GET, RequestMethod.POST})
    public String manter(@Valid @ModelAttribute(USUARIO_KEY) Usuario usuario, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
        	usuarioService.salvarOuAtualizar(usuario);
            status.setComplete();
            return "redirect:/usuario/listar";
        }
        return MANTER_PAGE;
    }

    @RequestMapping(value = "/pesquisar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView pesquisar(@ModelAttribute(USUARIO_KEY) Usuario usuario, Model model) {
        List<Usuario> usuarios = usuarioService.pesquisar(usuario);
        if (usuarios.isEmpty()) {
        	Locale locale = LocaleContextHolder.getLocale();
        	String mensagem = messageSource.getMessage("label.nenhum.registro.encontrado", null, locale);
            setMensagemNegocial(model, mensagem);
        }
        return new ModelAndView(LISTAR_PAGE, USUARIOS_KEY, usuarios);
    }

    private void setMensagemNegocial(Model model, String mensagem) {
        model.addAttribute("usuario_mensagem_negocial", mensagem);
    }
	
}
