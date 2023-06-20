package dao;

import connection_handler.ConnectionHandler;
import model.Sala;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class SalaDaoSql implements Dao<Sala> {

    private ConnectionHandler connectionHandler;
    private final Map<Integer, Sala> sale = new HashMap<>();

    public SalaDaoSql() throws SQLException {
        connectionHandler = ConnectionHandler.getInstance();
        connectionHandler.getConnection();
        PreparedStatement ps_all_sale = connectionHandler.getPreparedStatement(
                "select * from sala");

        ResultSet rs = ps_all_sale.executeQuery();
        while(rs.next()) {
            Sala sala = new Sala(rs.getInt("id"),rs.getString("nome"));
            sale.put(rs.getInt("id"),sala);
        }
    }

    @Override
    public boolean insert(Sala sala) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Sala sala) throws SQLException {
        connectionHandler = ConnectionHandler.getInstance();
        connectionHandler.getConnection();

        PreparedStatement ps_update_sale = connectionHandler.getPreparedStatement(
                "UPDATE teatro.sala SET id=?, nome=?, id_sede=? WHERE <condition>");

        ps_update_sale.setInt(1,sala.getId());
        ps_update_sale.setString(2,sala.getNome());
        ps_update_sale.executeUpdate();

        ps_update_sale.close();
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Optional<Sala> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Sala> getAll() {
        return null;
    }
}