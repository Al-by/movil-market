package com.dev.movilmarket.gamas.service;

import com.dev.movilmarket.gamas.model.Gama;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GamaService {
    public ResponseEntity<Map<String,Object>> listarGamas();
    public ResponseEntity<Map<String,Object>> listarGamaPorId(Long id);
    public ResponseEntity<Map<String,Object>> agregarGama(Gama gama);
    public ResponseEntity<Map<String,Object>> actualizarGama(Gama gama, Long id);
    public ResponseEntity<Map<String,Object>> eliminarGama(Long id);
}
