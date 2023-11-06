package voiture.flotte.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import voiture.flotte.resources.Marque;

public class MarqueRowMapper implements RowMapper<Marque>{

    @Override
    @Nullable
    public Marque mapRow(ResultSet rs, int rowNum) throws SQLException {
        Marque marque=new Marque();
        marque.setId(rs.getInt("id_marque"));
        marque.setNom(rs.getString("nom_marque"));
        return marque;
    }
    
}
