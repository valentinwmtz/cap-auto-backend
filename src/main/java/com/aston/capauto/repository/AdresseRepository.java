package com.aston.capauto.repository;

import com.aston.capauto.domain.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    List<Adresse> findAllByEleveId(Long eleve_id);
}
