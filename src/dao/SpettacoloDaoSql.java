package dao;

import connection_handler.ConnectionHandler;
import model.Posto;
import model.Spettacolo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpettacoloDaoSql implements Dao<Spettacolo> {

    @Override
    public boolean insert(Spettacolo spettacolo) throws SQLException {
        String query = "INSERT INTO spettacolo (nome,orario,durata,genere,prezzo) VALUES (?,?,?,?,?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setString(2, spettacolo.getNome());
            ps.setString(3, spettacolo.getOrario().toString());
            ps.setInt(4, spettacolo.getDurata());
            ps.setString(5, spettacolo.getGenere());
            ps.setFloat(6, spettacolo.getPrezzo());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
    }

    @Override
    public boolean update(Spettacolo spettacolo) throws SQLException {
        String query = "UPDATE spettacolo SET nome = ?, orario= ?, durata=?, genere=?, prezzo=? WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {

            ps.setInt(1, spettacolo.getId());
            ps.setString(2, spettacolo.getNome());
            ps.setString(3, spettacolo.getOrario().toString());
            ps.setInt(4, spettacolo.getDurata());
            ps.setString(5, spettacolo.getGenere());
            ps.setFloat(6, spettacolo.getPrezzo());
            int updatedCount = ps.executeUpdate();
            return updatedCount > 0;

        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM spettacolo WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            int deletedCount = ps.executeUpdate();
            return deletedCount > 0;

        }
    }

    @Override
    public Optional<Spettacolo> get(int id) throws SQLException {
        String query = "SELECT * FROM spettacolo WHERE id = ?;";

        Optional<Spettacolo> spettacolo = Optional.empty();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) spettacolo = Optional.of(Spettacolo.fromResultSet(rs));

        }

        return spettacolo;
    }




    @Override
    public List<Spettacolo> getAll() throws SQLException {
        String query = "SELECT * FROM spettacolo;";

        List<Spettacolo> spettacoli = new ArrayList<>();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) spettacoli.add(Spettacolo.fromResultSet(rs));
        }

        return spettacoli;
    }

}
