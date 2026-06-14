package com.minutas.api_minutas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minutas.api_minutas.model.Minuta;

@Repository
public interface MinutaRepository extends JpaRepository<Minuta, Long> {

}

