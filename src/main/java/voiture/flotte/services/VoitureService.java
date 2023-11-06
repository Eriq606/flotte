package voiture.flotte.services;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import voiture.flotte.mappers.VoitureRowMapper;
import voiture.flotte.resources.Voiture;

public class VoitureService {
    private final JdbcTemplate jdbcTemplate;

    public VoitureService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void insert(Voiture voiture){
        jdbcTemplate.update("insert into voitures values(default, ?, ?)", voiture.getMarque().getId(), voiture.getMatricule());
    }
    public List<Voiture> selectAll(){
        return jdbcTemplate.query("select * from v_voiture_marques", new VoitureRowMapper());
    }
    public Voiture selectByID(int idVoiture){
        try{
            return (Voiture)jdbcTemplate.queryForObject("select * from v_voiture_marques where id_voiture=?", new VoitureRowMapper(), new Object[]{idVoiture});
        }catch(EmptyResultDataAccessException es){
            return null;
        }
    }
    public void update(int idVoiture, Voiture voiture){
        jdbcTemplate.update("update voitures set matricule=?, id_marque_voiture=? where id_voiture=?", voiture.getMatricule(), voiture.getMarque().getId(), idVoiture);
    }
    public void delete(int idVoiture){
        jdbcTemplate.update("delete from voitures where id_voiture=?", idVoiture);
    }
}
