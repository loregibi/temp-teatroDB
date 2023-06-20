package dao;
import model.T;
import java.util.*;

public class PostoDao implements Dao<T> {

    private Map<Integer, T> posti = new HashMap<>();

    @Override
    public boolean insert(T v) {
        posti.put(v.getId(), v);
        return posti.get(v.getId()).equals(v);
    }

    @Override
    public boolean update(T v) {
        posti.replace(v.getId(), v);
        return posti.get(v.getId()).equals(v);
    }

    @Override
    public boolean delete(int id) {
        return posti.remove(id) != null;
    }

    @Override
    public Optional<T> getById(int id) {
        return Optional.ofNullable(posti.get(id));
    }

    @Override
    public Collection<T> getAll() {
        return posti.values();
    }
}