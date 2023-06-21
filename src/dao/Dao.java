package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Dao <T>{

    boolean insert(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    Optional<T> get(int id) throws Exception;
    List<T> getAll() throws SQLException;
}
