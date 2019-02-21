package com.aston.capauto.repository;

import com.aston.capauto.domain.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EleveRepository extends JpaRepository<Eleve, Long> {
}
