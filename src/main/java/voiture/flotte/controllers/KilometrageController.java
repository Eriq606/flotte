package voiture.flotte.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import voiture.flotte.resources.Kilometrage;
import voiture.flotte.resources.Voiture;
import voiture.flotte.services.KilometrageService;

@RestController
public class KilometrageController {
    private final KilometrageService service;
    
	public KilometrageController(JdbcTemplate jdbcTemplate) {
        service=new KilometrageService(jdbcTemplate);
    }
    @GetMapping("/header")
    public Map<String, String> getHeader(@RequestHeader HttpHeaders header){
        return header.toSingleValueMap();
    }
    @PostMapping("/kilometrages")
	public String insert(@RequestParam(value= "id_voiture") int id_voiture,
                            @RequestParam(value= "date_kilometrage") LocalDateTime dateKilometrage,
                            @RequestParam(value= "debut_kilometrage") double debutKilometrage,
                            @RequestParam(value= "fin_kilometrage") double finKilometrage,
                            @RequestHeader HttpHeaders header) {
        Voiture voiture=new Voiture();
        voiture.setId(id_voiture);
        Kilometrage kilometrage=new Kilometrage();
        kilometrage.setDateKilometrage(dateKilometrage);
        kilometrage.setVoiture(voiture);
        kilometrage.setDebutKilometrage(debutKilometrage);
        kilometrage.setFinKilometrage(finKilometrage);
        service.insert(kilometrage);
        return "Insere avec succes";
	}
    @GetMapping("/kilometrages")
    public List<Kilometrage> selectAll(){
        return service.selectAll();
    }
    @GetMapping("/kilometrages/{id_kilometrage}")
    public Kilometrage selectById(@PathVariable int id_kilometrage){
        return service.selectByID(id_kilometrage);
    }
    @PutMapping("/kilometrages/{id_kilometrage}")
    public String update(@PathVariable int id_kilometrage, 
                            @RequestParam(value= "id_voiture") int id_voiture, 
                            @RequestParam(value= "date_kilometrage") LocalDateTime date_kilometrage,
                            @RequestParam(value= "debut_kilometrage") double debut_kilometrage,
                            @RequestParam(value= "fin_kilometrage") double fin_kilometrage){
        Kilometrage km=new Kilometrage();
        km.setDateKilometrage(date_kilometrage);
        km.setDebutKilometrage(fin_kilometrage);
        km.setFinKilometrage(fin_kilometrage);
        Voiture v=new Voiture();
        v.setId(id_voiture);
        km.setVoiture(v);
        service.update(id_kilometrage, km);
        return "Modifie avec succes";
    }
    @DeleteMapping("/kilometrages/{id_kilometrage}")
    public String delete(@PathVariable int id_kilometrage){
        service.delete(id_kilometrage);
        return "Supprime avec succes";
    }
}
