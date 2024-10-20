package com.dev.movilmarket.gamas.controller;

import com.dev.movilmarket.gamas.model.Gama;
import com.dev.movilmarket.gamas.service.GamaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Controller
@RequestMapping("/api/gamas")
public class GamaController {

    @Autowired
    private GamaService gamaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarGamas(){
        return gamaService.listarGamas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarGamaPorId(@PathVariable Long id){
        return gamaService.listarGamaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarGama(@Valid @RequestBody Gama gama){
        return gamaService.agregarGama(gama);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarGama(@RequestBody Gama gama, @PathVariable Long id){
        return gamaService.actualizarGama(gama, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarGama(@PathVariable Long id){
        return gamaService.eliminarGama(id);
    }
}
