package minha.aplicacao.api.controller;

import minha.aplicacao.api.models.MedicoModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody MedicoModel medicoModel){
        System.out.println(medicoModel.endereco().cep());
    }
}
