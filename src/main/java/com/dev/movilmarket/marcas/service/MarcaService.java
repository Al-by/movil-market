package com.dev.movilmarket.marcas.service;

import com.dev.movilmarket.marcas.model.Marca;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

public interface MarcaService {
    public ResponseEntity<Map<String, Object>> listarMarcas();
    public ResponseEntity<Map<String, Object>> listarMarcaPorId(Long id);
    public ResponseEntity<Map<String, Object>> agregarMarca(Marca marca);
    public ResponseEntity<Map<String, Object>> actualizarMarca(Marca marca, Long id);
    public ResponseEntity<Map<String, Object>> eliminarMarca(Long id);
}
