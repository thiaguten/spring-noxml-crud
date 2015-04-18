package br.com.thiaguten.spring.web.validator;

import br.com.thiaguten.spring.model.Usuario;
import br.com.thiaguten.spring.service.UsuarioService;
import br.com.thiaguten.spring.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("usuarioValidator")
public class UsuarioValidator implements Validator {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioValidator(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Usuario.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotBlank.usuario.nome");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idade", "NotNull.usuario.idade");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank.usuario.email");
        ValidationUtils.rejectIfEmailIsNotValid(errors, "email", "Email.usuario.email");

        Usuario usuario = (Usuario) target;
        boolean emailJaCadastrado = usuarioService.isEmailJaCadastrado(usuario);
        if (emailJaCadastrado) {
            errors.reject("error.email.ja.cadastrado", new Object[]{usuario.getEmail()}, "Email \"{0}\" j\u00E1 cadastrado!");
        }
    }
}
