package com.dev.movilmarket.marcas.controller;

import com.dev.movilmarket.marcas.model.Marca;
import com.dev.movilmarket.marcas.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Controller
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarMarcas(){
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listaMarcaPorID(@PathVariable Long id){
        return marcaService.listarMarcaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarMarca(@Valid @RequestBody Marca marca){
        return marcaService.agregarMarca(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarMarca(@RequestBody Marca marca, @PathVariable Long id){
        return marcaService.actualizarMarca(marca,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarMarca(@PathVariable Long id){
        return marcaService.eliminarMarca(id);
    }
}
