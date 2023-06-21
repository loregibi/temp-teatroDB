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

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String telefono;
    private String indirizzo;

    public static Utente fromResultSet(ResultSet rs) throws SQLException {
        return new Utente(rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("mail"),
                rs.getString("telefono"),
                rs.getString("indirizzo"));
    }
}
