package voiture.flotte.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import voiture.flotte.resources.Kilometrage;
import voiture.flotte.resources.Marque;
import voiture.flotte.resources.Voiture;

public class KilometrageRowMapper implements RowMapper<Kilometrage>{

    @Override
    @Nullable
    public Kilometrage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Kilometrage kilometrage=new Kilometrage();
        kilometrage.setId(rs.getInt("id_kilometrage"));

        Voiture voiture=new Voiture();
        voiture.setId(rs.getInt("id_voiture"));
        voiture.setMatricule(rs.getString("matricule"));

        Marque marque=new Marque();
        marque.setId(rs.getInt("id_marque"));
        marque.setNom(rs.getString("nom_marque"));
        voiture.setMarque(marque);

        kilometrage.setVoiture(voiture);
        kilometrage.setDateKilometrage(rs.getTimestamp("date_kilometrage").toLocalDateTime());
        kilometrage.setDebutKilometrage(rs.getDouble("debut_kilometrage"));
        kilometrage.setFinKilometrage(rs.getDouble("fin_kilometrage"));
        return kilometrage;
    }
    
}
