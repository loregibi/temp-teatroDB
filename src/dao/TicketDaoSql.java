package dao;

import connection_handler.ConnectionHandler;
import model.Posto;
import model.Spettacolo;
import model.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoSql implements Dao<Ticket> {

    @Override
    public boolean insert(Ticket ticket) throws SQLException {
        String query = "INSERT INTO teatro.ticket (timestamp,id_utente,id_posto,id_spettacolo) VALUES (?,?,?,?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setString(1, ticket.getTimestamp().toString());
            ps.setInt(2, ticket.getIdUtente());
            ps.setInt(3, ticket.getIdPosto());
            ps.setInt(4, ticket.getIdSpettacolo());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
    }

    @Override
    public boolean update(Ticket ticket) throws SQLException {
        String query = "UPDATE teatro.ticket SET timestamp=? WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(2, ticket.getId());
            ps.setString(1, ticket.getTimestamp().toString());
            int updatedCount = ps.executeUpdate();
            return updatedCount > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM teatro.ticket WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            int deletedCount = ps.executeUpdate();
            return deletedCount > 0;
        }
    }

    @Override
    public Optional<Ticket> get(int id) throws SQLException {
        String query = "SELECT * FROM teatro.ticket WHERE id = ?;";

        Optional<Ticket> ticket = Optional.empty();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) ticket = Optional.of(Ticket.fromResultSet(rs));
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() throws SQLException {
        String query = "SELECT * FROM teatro.ticket;";

        List<Ticket> tickets = new ArrayList<>();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) tickets.add(Ticket.fromResultSet(rs));
        }
        return tickets;
    }
}
