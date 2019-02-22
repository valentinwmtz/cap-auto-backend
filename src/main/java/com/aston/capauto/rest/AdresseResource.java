package com.aston.capauto.rest;

import com.aston.capauto.domain.Adresse;
import com.aston.capauto.exception.PostWithIdException;
import com.aston.capauto.exception.PutWithoutIdException;
import com.aston.capauto.service.AdresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AdresseResource {

    private AdresseService AdresseService;

    public AdresseResource(AdresseService adresseService) {
        AdresseService = adresseService;
    }

    @GetMapping("/adresse")
    public List<Adresse> getAllAdresses() {
        return AdresseService.findAll();
    }

    @GetMapping("/adresse/{id}")
    public Adresse getAdresse(@PathVariable Long id) {
        return AdresseService.findOne(id);
    }

    @GetMapping("/adresse/eleve/{eleveId}")
    public List<Adresse> getAdresseByEleveId(@PathVariable(value = "eleveId") Long id) {
        return AdresseService.findAllByEleveId(id);
    }

    @PostMapping("/adresse/{eleveId}")
    public Adresse createAdresse(@PathVariable(value = "eleveId") Long id, @Valid @RequestBody Adresse adresse) {
        if (adresse.getId() != null) {
            throw new PostWithIdException("adresse");
        }
        return AdresseService.save(id, adresse);
    }

    @PutMapping("/adresse/{eleveId}")
    public Adresse updateAdresse(@PathVariable(value = "eleveId") Long id, @Valid @RequestBody Adresse adresse) {
        if (adresse.getId() == null) {
            throw new PutWithoutIdException("adresse");
        }
        return AdresseService.save(id, adresse);
    }

    @DeleteMapping("/adresse/{id]")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        AdresseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
