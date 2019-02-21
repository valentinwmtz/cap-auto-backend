package com.aston.capauto.service;

import com.aston.capauto.domain.Eleve;
import com.aston.capauto.exception.ResourceNotFoundException;
import com.aston.capauto.repository.EleveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveService {

    private EleveRepository eleveRepository;

    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    /**
     * Get all the eleve
     *
     * @return Eleve List
     */
    public List<Eleve> findAll() {
        System.out.println("Find all Eleve");
        return eleveRepository.findAll();
    }

    /**
     * Find one eleve
     *
     * @param id of the eleve
     * @return the Eleve or Exception
     */
    public Eleve findOne(Long id) {
        System.out.println("Find one eleve : " + id);
        return eleveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Eleve", "ID", id));
    }

    /**
     * Save an eleve
     *
     * @param eleve
     * @return saved Eleve
     */
    public Eleve save(Eleve eleve) {
        System.out.println("Save eleve : " + eleve);
        return eleveRepository.save(eleve);
    }

    /**
     * Delete an eleve
     *
     * @param id
     */
    public void delete(Long id) {
        eleveRepository.deleteById(id);
    }

}
