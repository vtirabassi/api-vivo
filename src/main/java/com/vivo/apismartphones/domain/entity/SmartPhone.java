package com.vivo.apismartphones.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Validated
@Entity
@Table(name = "PHONES")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmartPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{phone.nome.obrigatorio}")
    private String nome;

    @Column(name = "marca", length = 100)
    @NotEmpty(message = "{phone.marca.obrigatorio}")
    private String marca;

    @Column(name = "preco", columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "{phone.preco.obrigatorio}")
    private BigDecimal preco;
}
