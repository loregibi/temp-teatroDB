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
    private LocalDateTime data;

    public Ticket(LocalDateTime data) {
        this.data = data;
    }

    public static Ticket fromResultSet(ResultSet rs) throws SQLException {
        return new Ticket(rs.getInt("id"),
                 LocalDateTime.parse(rs.getString("data")));
    }
}
