package com.atividade.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.apirest.model.Aluno;
import com.atividade.apirest.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public String receberDados(@RequestBody Aluno data) {
        alunoRepository.save(data);
        return "Dados recebidos com sucesso.";
    }

    @GetMapping
public ResponseEntity<?> retornarDados() {
    List<Aluno> alunos = alunoRepository.findAll();
    return ResponseEntity.ok().body(alunos);
}

    @PutMapping("/{ra}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long ra, @RequestBody Aluno alunoAtualizadoRequest) {
        Optional<Aluno> optionalAluno = alunoRepository.findByRa(ra);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();

            aluno.setNome(alunoAtualizadoRequest.getNome());
            aluno.setTelefone(alunoAtualizadoRequest.getTelefone());
            aluno.setEmail(alunoAtualizadoRequest.getEmail());
            aluno.setDataNascimento(alunoAtualizadoRequest.getDataNascimento());

            Aluno alunoAtualizado = alunoRepository.save(aluno);
            return ResponseEntity.ok().body(alunoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{ra}")
public ResponseEntity<?> deletarAluno(@PathVariable Long ra) {
    Optional<Aluno> optionalAluno = alunoRepository.findByRa(ra);
    if (optionalAluno.isPresent()) {
        alunoRepository.delete(optionalAluno.get());
        return ResponseEntity.ok().body("Aluno deletado com sucesso.");
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
