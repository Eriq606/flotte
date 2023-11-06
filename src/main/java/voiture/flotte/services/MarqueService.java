package voiture.flotte.services;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import voiture.flotte.mappers.MarqueRowMapper;
import voiture.flotte.resources.Marque;

public class MarqueService {
    private final JdbcTemplate jdbcTemplate;

    public MarqueService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void insert(Marque marque){
        jdbcTemplate.update("insert into marques values(default, ?)", marque.getNom());
    }
    public List<Marque> selectAll(){
        return jdbcTemplate.query("select * from marques", new MarqueRowMapper());
    }
    public Marque selectByID(int idMarque){
        try{
            return (Marque)jdbcTemplate.queryForObject("select * from marques where id_marque=?", new MarqueRowMapper(), new Object[]{idMarque});
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    public void update(int idMarque, Marque marque){
        jdbcTemplate.update("update marques set nom_marque=? where id_marque=?", marque.getNom(), idMarque);
    }
    public void delete(int idMarque){
        jdbcTemplate.update("delete from marques where id_marque=?", idMarque);
    }
}
