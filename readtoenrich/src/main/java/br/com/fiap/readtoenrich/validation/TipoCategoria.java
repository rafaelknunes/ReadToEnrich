package br.com.fiap.readtoenrich.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(FIELD)
@Constraint(validatedBy = TipoCategoriaValidator.class)
@Retention(RUNTIME)
public @interface TipoCategoria {

    String message() default "Tipo inválido. Tipo deve ser Romance ou Ciência.";

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
