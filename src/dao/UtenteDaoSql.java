package dao;

import connection_handler.ConnectionHandler;
import model.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtenteDaoSql implements Dao<Utente> {

    @Override
    public boolean insert(Utente utente) throws SQLException {
        String query = "INSERT INTO teatro.utente (nome,cognome,mail,telefono,indirizzo) VALUES (?,?,?,?,?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getMail());
            ps.setString(4, utente.getTelefono());
            ps.setString(5, utente.getIndirizzo());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
    }

    @Override
    public boolean update(Utente utente) throws SQLException {
        String query = "UPDATE teatro.utente SET nome = ?, cognome= ?, mail=?, telefono=?, indirizzo=? WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {

            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getMail());
            ps.setString(4, utente.getTelefono());
            ps.setString(5, utente.getIndirizzo());
            ps.setInt(6, utente.getId());
            int updatedCount = ps.executeUpdate();
            return updatedCount > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM teatro.utente WHERE id = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, id);
            int deletedCount = ps.executeUpdate();
            return deletedCount > 0;
        }
    }

    @Override
    public Optional<Utente> get(int id) throws SQLException {
        String query = "SELECT * FROM teatro.utente WHERE id = ?;";

        Optional<Utente> utente = Optional.empty();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) utente = Optional.of(Utente.fromResultSet(rs));
        }
        return utente;
    }

    @Override
    public List<Utente> getAll() throws SQLException {
        String query = "SELECT * FROM teatro.utente;";

        List<Utente> utenti = new ArrayList<>();

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) utenti.add(Utente.fromResultSet(rs));
        }
        return utenti;
    }

}
