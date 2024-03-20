package br.com.fiap.readtoenrich.model;

import br.com.fiap.readtoenrich.validation.TipoCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{categoria.nome.notblank}")
    @Size(min = 3, message =  "{categoria.descricao.size}")
    @TipoCategoria(message = "{categoria.tipo.tipocategoria}")
    private String nome;


}
