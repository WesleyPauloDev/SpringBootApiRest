package com.atividade.apirest.model;
import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ra;
   
    @Column(name = "nome", nullable = false)
    private String nome;

    private String telefone;
    
    @Column(name = "email", nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @JsonProperty("data_nascimento")
    private Date dataNascimento;
}
