package voiture.flotte.services;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import voiture.flotte.helpers.AuthentificationHelper;

public class AuthentificationService {
    private final JdbcTemplate jdbcTemplate;

    public AuthentificationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void insert(String auth){
        String[] infos=AuthentificationHelper.getUserInfo(auth);
        jdbcTemplate.update("insert into users values(default, ?, ?)", infos[0], infos[1]);
    }
    public int checkIfExists(String auth){
        String[] infos=AuthentificationHelper.getUserInfo(auth);
        try{
            int user = jdbcTemplate.queryForObject("select id_user from users where nom_user=? and mdp_user=?", int.class, new Object[]{infos[0], infos[1]});
            return user;
        }catch(EmptyResultDataAccessException ex){
            return -1;
        }
    }
    public String getToken(String auth) throws NoSuchAlgorithmException{
        int idUser=checkIfExists(auth);
        if(idUser!=-1){
            LocalDateTime now=LocalDateTime.now();
            String token=AuthentificationHelper.createToken(auth, now);
            jdbcTemplate.update("insert into tokens values(default, ?, ?, ?)", idUser, token, Timestamp.valueOf(now.plusMinutes(30)));
            return token;
        }
        return "informations incorrectes";
    }
    public int checkToken(String token){
        try{
            int iduser=jdbcTemplate.queryForObject("select id_user_token from v_valid_tokens where value_token=?", int.class, new Object[]{token});
            return iduser;
        }catch(EmptyResultDataAccessException ex){
            return -1;
        }
    }
    public void logout(String token){
        jdbcTemplate.update("delete from tokens where value_token=?", token);
    }
}
