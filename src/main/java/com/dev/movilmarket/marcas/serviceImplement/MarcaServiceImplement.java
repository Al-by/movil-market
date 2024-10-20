package com.dev.movilmarket.marcas.serviceImplement;

import com.dev.movilmarket.marcas.model.Marca;
import com.dev.movilmarket.marcas.repository.MarcaRepository;
import com.dev.movilmarket.marcas.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarcaServiceImplement implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listarMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        Map<String, Object> respuesta = new HashMap<>();
        if(!marcas.isEmpty()) {
            respuesta.put("mensaje", "Lista de marcas");
            respuesta.put("marcas", marcas);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarMarcaPorId(Long id) {
        Optional<Marca> marcas = marcaRepository.findById(id);
        Map<String, Object> respuesta = new HashMap<>();
        if(marcas.isPresent()) {
            respuesta.put("mensaje", "Busqueda correcta");
            respuesta.put("marcas", marcas);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarMarca(Marca marca) {
        Map<String, Object> respuesta = new HashMap<>();
        marcaRepository.save(marca);
        respuesta.put("mensaje", "Registro agregado");
        respuesta.put("marca", marca);
        respuesta.put("status", HttpStatus.OK);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarMarca(Marca marca, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Marca> marcaExiste = marcaRepository.findById(id);
        if(marcaExiste.isPresent()) {
            Marca mrc = marcaExiste.get();
            marca.setNombreMarca(mrc.getNombreMarca());
            marcaRepository.save(marca);
            respuesta.put("mensaje", "Registro actualizado");
            respuesta.put("marca", marca);
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
            respuesta.put("mensaje", "No existen registros con el ID" + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarMarca(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Marca> marcaExiste = marcaRepository.findById(id);
        if(marcaExiste.isPresent()) {
            Marca mrc = marcaExiste.get();
            marcaRepository.delete(mrc);
            respuesta.put("mensaje", "Registro eliminado");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        }else {
            respuesta.put("mensaje", "No se puede borrar la marca con ID" + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}
