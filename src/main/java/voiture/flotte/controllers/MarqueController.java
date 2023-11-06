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
import voiture.flotte.services.MarqueService;

@RestController
public class MarqueController {
    private final MarqueService service;
    
	public MarqueController(JdbcTemplate jdbcTemplate) {
        service=new MarqueService(jdbcTemplate);
    }

    @PostMapping("/marques")
	public String insert(@RequestParam(value= "nom_marque") String nom_marque) {
        Marque marque=new Marque();
        marque.setNom(nom_marque);
        service.insert(marque);
        return "Insere avec succes";
	}
    @GetMapping("/marques")
    public List<Marque> selectAll(){
        return service.selectAll();
    }
    @GetMapping("/marques/{id_marque}")
    public Marque selectById(@PathVariable int id_marque){
        return service.selectByID(id_marque);
    }
    @PutMapping("/marques/{id_marque}")
    public String update(@PathVariable int id_marque, @RequestParam(value= "nom_marque") String nom_marque){
        Marque marque=new Marque();
        marque.setNom(nom_marque);
        service.update(id_marque, marque);
        return "Modifie avec succes";
    }
    @DeleteMapping("/marques/{id_marque}")
    public String delete(@PathVariable int id_marque){
        service.delete(id_marque);
        return "Supprime avec succes";
    }
}
