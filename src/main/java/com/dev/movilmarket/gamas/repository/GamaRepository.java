package com.dev.movilmarket.gamas.repository;

import com.dev.movilmarket.gamas.model.Gama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamaRepository extends JpaRepository<Gama, Long> {

}
