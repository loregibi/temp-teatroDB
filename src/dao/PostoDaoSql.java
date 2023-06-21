package dao;

import connection_handler.ConnectionHandler;
import model.Posto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostoDaoSql implements Dao<Posto> {

    @Override
    public boolean insert(Posto posto) throws SQLException {
        String query = "INSERT INTO teatro.posto (fila,numero) VALUES (?,?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, posto.getFila());
            ps.setInt(2, posto.getNumero());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
    }

    @Override
    public boolean update(Posto posto) throws SQLException {
        String query = "UPDATE teatro.posto SET fila = ?, numero= ? WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(3, posto.getId());
            ps.setInt(1, posto.getFila());
            ps.setInt(2, posto.getNumero());
            int updatedCount = ps.executeUpdate();
            return updatedCount > 0;

        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM teatro.posto WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, id);
            int deletedCount = ps.executeUpdate();
            return deletedCount > 0;

        }
    }

    @Override
    public Optional<Posto> get(int id) throws SQLException {
        String query = "SELECT * FROM teatro.posto WHERE id = ?;";

        Optional<Posto> posto = Optional.empty();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) posto = Optional.of(Posto.fromResultSet(rs));

        }

        return posto;
    }


    @Override
    public List<Posto> getAll() throws SQLException {
        String query = "SELECT * FROM teatro.posto;";

        List<Posto> posti = new ArrayList<>();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) posti.add(Posto.fromResultSet(rs));
        }

        return posti;
    }

}
