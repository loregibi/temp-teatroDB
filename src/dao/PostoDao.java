package dao;

import model.Posto;

import java.sql.SQLException;

public interface PostoDao extends Dao<Posto> {
    public int getPostoByFilaENumero(int fila, int numero) throws SQLException;
}
