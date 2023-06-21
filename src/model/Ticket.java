package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Ticket {

    private int id;
    private String timestamp;
    private int idPosto;
    private int idSpettacolo;
    private int idUtente;


    public Ticket(String timestamp,int idPosto,int idSpettacolo,int idUtente) {
        this.timestamp = timestamp;
        this.idPosto=idPosto;
        this.idSpettacolo=idSpettacolo;
        this.idUtente= idUtente;
    }

    public static Ticket fromResultSet(ResultSet rs) throws SQLException {
        return new Ticket(rs.getInt("id"),
                rs.getString("timestamp"),
                rs.getInt("id_utente"),
                rs.getInt("id_posto"),
                rs.getInt("id_spettacolo"));
    }
}
