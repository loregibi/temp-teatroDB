package dao;

import connection_handler.ConnectionHandler;
import model.Posto;
import model.Sede;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SedeDaoSql implements Dao<Sede> {

    @Override
    public boolean insert(Sede sede) throws SQLException {
        String query = "INSERT INTO sede (nome,indirizzo,comune,open) VALUES (?,?,?,?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setString(2, sede.getNome());
            ps.setString(3, sede.getIndirizzo());
            ps.setString(4, sede.getComune());
            ps.setBoolean(5, sede.isOpen());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
    }

    @Override
    public boolean update(Sede sede) throws SQLException {
        String query = "UPDATE sede SET nome = ?, indirizzo= ?, comune=?, open=? WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, sede.getId());
            ps.setString(2, sede.getNome());
            ps.setString(3, sede.getIndirizzo());
            ps.setString(4, sede.getComune());
            ps.setBoolean(5, sede.isOpen());
            int updatedCount = ps.executeUpdate();
            return updatedCount > 0;

        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM sede WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            int deletedCount = ps.executeUpdate();
            return deletedCount > 0;

        }
    }

    @Override
    public Optional<Sede> get(int id) throws SQLException {
        String query = "SELECT * FROM sede WHERE id = ?;";

        Optional<Sede> sede = Optional.empty();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) sede = Optional.of(Sede.fromResultSet(rs));

        }

        return sede;
    }




    @Override
    public List<Sede> getAll() throws SQLException {
        String query = "SELECT * FROM sede;";

        List<Sede> sedi = new ArrayList<>();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) sedi.add(Sede.fromResultSet(rs));
        }

        return sedi;
    }

}
