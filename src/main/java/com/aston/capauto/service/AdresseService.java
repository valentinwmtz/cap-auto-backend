package com.aston.capauto.service;

import com.aston.capauto.domain.Adresse;
import com.aston.capauto.exception.ResourceNotFoundException;
import com.aston.capauto.repository.AdresseRepository;
import com.aston.capauto.repository.EleveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseService {

    private AdresseRepository adresseRepository;
    private EleveRepository eleveRepository;

    public AdresseService(AdresseRepository adresseRepository, EleveRepository eleveRepository) {
        this.adresseRepository = adresseRepository;
        this.eleveRepository = eleveRepository;
    }

    /**
     * Get all the adresse
     *
     * @return Adresse List
     */
    public List<Adresse> findAll() {
        System.out.println("Find all Adresse");
        return adresseRepository.findAll();
    }

    /**
     * Get all the adresse of Eleve
     *
     * @param id Eleve
     * @return Adresse List
     */
    public List<Adresse> findAllByEleveId(Long id) {
        System.out.println("Find all Adresse by eleve ID");
        return adresseRepository.findAllByEleveId(id);
    }

    /**
     * Find one adresse
     *
     * @param id of the adresse
     * @return the Adresse or Exception
     */
    public Adresse findOne(Long id) {
        System.out.println("Find one adresse : " + id);
        return adresseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Adresse", "ID", id));
    }

    /**
     * Save an adresse
     *
     * @param adresse object
     * @return saved Adresse
     */
    public Adresse save(Long eleveId, Adresse adresse) {
        return this.eleveRepository.findById(eleveId).map(eleve -> {
            eleve.setAdresse(adresse);
            System.out.println("Save adresse : " + adresse + "to" + eleve);
            return this.adresseRepository.save(adresse);
        }).orElseThrow(() -> new ResourceNotFoundException("eleve", "adresse", eleveId));
    }

    /**
     * Delete an adresse
     *
     * @param id Adresse
     */
    public void delete(Long id) {
        adresseRepository.deleteById(id);
    }


}
