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

public class Posto {

    private int id;
    private int fila;
    private int numero;

    public Posto(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
    }

    public static Posto fromResultSet(ResultSet rs) throws SQLException {
        return new Posto(rs.getInt("id"),
                rs.getInt("fila"),
                rs.getInt("numero"));
    }
}
