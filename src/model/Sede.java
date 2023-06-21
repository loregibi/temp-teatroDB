package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Sede {

    private int id;
    private String nome;
    private String indirizzo;
    private String comune;
    private boolean open;

    public static Sede fromResultSet(ResultSet rs) throws SQLException {
        return new Sede(rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("indirizzo"),
                rs.getString("comune"),
                rs.getBoolean("open"));
    }
}
