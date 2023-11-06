package voiture.flotte.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import voiture.flotte.mappers.KilometrageRowMapper;
import voiture.flotte.resources.Kilometrage;

public class KilometrageService {
    private final JdbcTemplate jdbcTemplate;

    public KilometrageService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void insert(Kilometrage kilometrage){
        jdbcTemplate.update("insert into kilometrages values(default, ?, ?, ?, ?)", 
            kilometrage.getVoiture().getId(), 
            Timestamp.valueOf(kilometrage.getDateKilometrage()), 
            kilometrage.getDebutKilometrage(),
            kilometrage.getFinKilometrage());
    }
    public List<Kilometrage> selectAll(){
        return jdbcTemplate.query("select * from v_kilometrage_voitures", new KilometrageRowMapper());
    }
    public Kilometrage selectByID(int idKilometrage){
        try{
            return (Kilometrage)jdbcTemplate.queryForObject("select * from v_kilometrage_voitures where id_kilometrage=?", new KilometrageRowMapper(), new Object[]{idKilometrage});
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    public void update(int idKilometrage, Kilometrage kilometrage){
        jdbcTemplate.update("update kilometrages set date_kilometrage=?, id_voiture_kilometrage=?, debut_kilometrage=?, fin_kilometrage=? where id_kilometrage=?",
            Timestamp.valueOf(kilometrage.getDateKilometrage()),
            kilometrage.getVoiture().getId(),
            kilometrage.getDebutKilometrage(),
            kilometrage.getFinKilometrage());
    }
    public void delete(int idKilometrage){
        jdbcTemplate.update("delete from KILOMETRAGES where id_kilometrage=?", idKilometrage);
    }
}
