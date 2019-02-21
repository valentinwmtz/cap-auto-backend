package com.aston.capauto.Rest;

import com.aston.capauto.domain.Eleve;
import com.aston.capauto.exception.PostWithIdException;
import com.aston.capauto.service.EleveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EleveResource {

    private EleveService eleveService;

    public EleveResource(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @GetMapping("/eleves")
    public List<Eleve> getAllEleves() {
        return eleveService.findAll();
    }

    @GetMapping("/eleve/{id}")
    public Eleve getEleve(@PathVariable Long id) {
        return eleveService.findOne(id);
    }

    @PostMapping("/eleve")
    public Eleve createEleve(@Valid @RequestBody Eleve eleve) {
        if (eleve.getId() != null){
            throw new PostWithIdException("eleve");
        }
        return eleveService.save(eleve);
    }

    @PutMapping("/eleve")
    public Eleve updateEleve(@Valid @RequestBody Eleve eleve) {
        return eleveService.save(eleve);
    }

    @DeleteMapping("/eleve/{id]")
    public ResponseEntity<Void> deleteEleve(@PathVariable Long id) {
        eleveService.delete(id);
        return ResponseEntity.ok().build();
    }
}
