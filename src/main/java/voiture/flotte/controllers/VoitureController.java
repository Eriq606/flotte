package voiture.flotte.controllers;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import voiture.flotte.resources.Marque;
import voiture.flotte.resources.Voiture;
import voiture.flotte.services.VoitureService;

@RestController
public class VoitureController {
    private final VoitureService service;
    
	public VoitureController(JdbcTemplate jdbcTemplate) {
        service=new VoitureService(jdbcTemplate);
    }

    @PostMapping("/voitures")
	public String insert(@RequestParam(value= "id_marque_voiture") int id_marque_voiture, @RequestParam(value= "matricule") String matricule) {
        Voiture voiture=new Voiture();
        voiture.setMatricule(matricule);
        Marque marque=new Marque();
        marque.setId(id_marque_voiture);
        voiture.setMarque(marque);
        service.insert(voiture);
        return "Insere avec succes";
	}
    @GetMapping("/voitures")
    public List<Voiture> selectAll(){
        return service.selectAll();
    }
    @GetMapping("/voitures/{id_voiture}")
    public Voiture selectById(@PathVariable int id_voiture){
        return service.selectByID(id_voiture);
    }
    @PutMapping("/voitures/{id_voiture}")
    public String update(@PathVariable int id_voiture, @RequestParam(value= "id_marque_voiture") int id_marque_voiture, @RequestParam(value= "matricule") String matricule){
        Voiture voiture=new Voiture();
        voiture.setMatricule(matricule);
        Marque marque=new Marque();
        marque.setId(id_marque_voiture);
        voiture.setMarque(marque);
        service.update(id_voiture, voiture);
        return "Modifie avec succes";
    }
    @DeleteMapping("/voitures/{id_voiture}")
    public String delete(@PathVariable int id_voiture){
        service.delete(id_voiture);
        return "Supprime avec succes";
    }
}
