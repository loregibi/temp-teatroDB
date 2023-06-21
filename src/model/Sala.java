package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Sala {

    private int id;
    private String nome;

    public static Sala fromResultSet(ResultSet rs) throws SQLException {
        return new Sala(rs.getInt("id"),
                rs.getString("nome"));
    }
}
