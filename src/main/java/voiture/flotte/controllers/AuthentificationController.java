package voiture.flotte.controllers;

import java.security.NoSuchAlgorithmException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import voiture.flotte.helpers.AuthentificationHelper;
import voiture.flotte.services.AuthentificationService;

@RestController
public class AuthentificationController {
    private final AuthentificationService service;
    
	public AuthentificationController(JdbcTemplate jdbcTemplate) {
        service=new AuthentificationService(jdbcTemplate);
    }
    @PostMapping("/tokens")
    public String basicAuth(@RequestHeader(value="authorization") String authorization) throws NoSuchAlgorithmException{
        service.insert(AuthentificationHelper.getAuthorizationContent(authorization));
        return "insere avec succes";
    }
    @GetMapping("/tokens")
    public String getToken(@RequestHeader(value="authorization") String authorization) throws NoSuchAlgorithmException{
        return service.getToken(AuthentificationHelper.getAuthorizationContent(authorization));
    }
    @DeleteMapping("/tokens")
    public String logout(@RequestHeader(value="authorization") String authorization){
        service.logout(AuthentificationHelper.getAuthorizationContent(authorization));
        return "Deconnecte";
    }
}
