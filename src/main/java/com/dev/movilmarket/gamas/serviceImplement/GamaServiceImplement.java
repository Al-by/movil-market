package com.dev.movilmarket.gamas.serviceImplement;

import com.dev.movilmarket.gamas.model.Gama;
import com.dev.movilmarket.gamas.repository.GamaRepository;
import com.dev.movilmarket.gamas.service.GamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GamaServiceImplement implements GamaService {

    @Autowired
    private GamaRepository gamaRepository;

    //TODO: Error en actualizar
    @Override
    public ResponseEntity<Map<String, Object>> listarGamas() {
        List<Gama> gamas = gamaRepository.findAll();
        Map<String, Object> respuesta = new HashMap<>();
        if (!gamas.isEmpty()) {
            respuesta.put("mensaje", "Nuestras gamas");
            respuesta.put("gamas", gamas);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "No hay registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarGamaPorId(Long id) {
        Optional<Gama> gama = gamaRepository.findById(id);
        Map<String, Object> respuesta = new HashMap<>();
        if (gama.isPresent()) {
            respuesta.put("mensaje", "Gama encontrada");
            respuesta.put("gama", gama);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else {
            respuesta.put("mensaje", "No hay registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarGama(Gama gama) {
        Map<String, Object> respuesta = new HashMap<>();
        gamaRepository.save(gama);
        respuesta.put("mensaje", "Gama agregada");
        respuesta.put("gama", gama);
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarGama(Gama gama, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Gama> gamaOptional = gamaRepository.findById(id);
        if (gamaOptional.isPresent()) {
            Gama gam = gamaOptional.get();
            gam.setNombreGama(gam.getNombreGama());
            gamaRepository.save(gam);
            respuesta.put("mensaje", "Gama actualizada");
            respuesta.put("gama", gama);
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
            respuesta.put("mensaje", "No hay registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarGama(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Gama> gamaOptional = gamaRepository.findById(id);
        if (gamaOptional.isPresent()) {
            Gama gam = gamaOptional.get();
            gamaRepository.delete(gam);
            respuesta.put("mensaje", "Gama eliminada");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        } else {
            respuesta.put("mensaje", "No se puede borrar la gama con el ID" + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}
