package dao;

import model.Spettacolo;

import java.sql.SQLException;

public interface SpettacoloDao extends Dao<Spettacolo> {
    public int getIdByNome(String nome) throws SQLException;
}
