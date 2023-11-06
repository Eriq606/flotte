package voiture.flotte.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import voiture.flotte.resources.Marque;
import voiture.flotte.resources.Voiture;

public class VoitureRowMapper implements RowMapper<Voiture>{

    @Override
    @Nullable
    public Voiture mapRow(ResultSet rs, int rowNum) throws SQLException {
        Voiture voiture=new Voiture();
        voiture.setId(rs.getInt("id_voiture"));
        voiture.setMatricule(rs.getString("matricule"));

        Marque marque=new Marque();
        marque.setId(rs.getInt("id_marque"));
        marque.setNom(rs.getString("nom_marque"));
        voiture.setMarque(marque);
        
        return voiture;
    }
    
}
