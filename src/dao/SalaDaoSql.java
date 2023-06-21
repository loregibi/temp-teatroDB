package dao;

import connection_handler.ConnectionHandler;
import dao.Dao;
import model.Sala;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaDaoSql implements Dao<Sala> {

    @Override
    public boolean insert(Sala sala) throws SQLException {
        String query = "INSERT INTO teatro.sala (nome) VALUES (?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setString(1, sala.getNome());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
    }

    @Override
    public boolean update(Sala sala) throws SQLException {
        String query = "UPDATE teatro.sala SET name = ? WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setString(1, sala.getNome());
            ps.setInt(2, sala.getId());
            int updatedCount = ps.executeUpdate();
            return updatedCount > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM teatro.sala WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, id);
            int deletedCount = ps.executeUpdate();
            return deletedCount > 0;

        }
    }

    @Override
    public Optional<Sala> get(int id) throws SQLException {
        String query = "SELECT * FROM teatro.sala WHERE id = ?;";

        Optional<Sala> sala = Optional.empty();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) sala = Optional.of(Sala.fromResultSet(rs));

        }

        return sala;
    }

    @Override
    public List<Sala> getAll() throws SQLException {
        String query = "SELECT * FROM teatro.sala;";

        List<Sala> sale = new ArrayList<>();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) sale.add(Sala.fromResultSet(rs));
        }
        return sale;
    }

}
