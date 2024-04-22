package br.com.fiap.readtoenrich.model;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.readtoenrich.validation.TipoCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder // Anotação para funcionar o builder no DatabaseSeeder
@NoArgsConstructor // Anotação para funcionar o builder no DatabaseSeeder
@AllArgsConstructor // Anotação para funcionar o builder no DatabaseSeeder
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;

    @NotBlank(message = "{categoria.nome.notblank}")
    @Size(min = 3, message =  "{categoria.descricao.size}")
    @TipoCategoria(message = "{categoria.tipo.tipocategoria}")
    private String nome;
}
