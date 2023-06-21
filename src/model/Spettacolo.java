package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Spettacolo {

    private int id;
    private String nome;
    private LocalDateTime orario;
    private int durata;
    private String genere;
    private float prezzo;

    public Spettacolo(String nome, LocalDateTime orario, int durata, String genere, float prezzo) {
        this.nome = nome;
        this.orario = orario;
        this.durata = durata;
        this.genere = genere;
        this.prezzo = prezzo;
    }

    public static Spettacolo fromResultSet(ResultSet rs) throws SQLException {
        return new Spettacolo(rs.getInt("id"),
                rs.getString("nome"),
                LocalDateTime.parse(rs.getString("orario")),
                rs.getInt("durata"),
                rs.getString("genere"),
                rs.getFloat("prezzo"));
    }
}
