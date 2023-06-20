package dao;
import model.Spettacolo;
import java.util.*;

public class SpettacoloDao implements Dao<Spettacolo> {

    private Map<Integer, Spettacolo> spettacoli = new HashMap<>();

    @Override
    public boolean insert(Spettacolo v) {
        spettacoli.put(v.getId(), v);
        return spettacoli.get(v.getId()).equals(v);
    }

    @Override
    public boolean update(Spettacolo v) {
        spettacoli.replace(v.getId(), v);
        return spettacoli.get(v.getId()).equals(v);
    }

    @Override
    public boolean delete(int id) {
        return spettacoli.remove(id) != null;
    }

    @Override
    public Optional<Spettacolo> getById(int id) {
        return Optional.ofNullable(spettacoli.get(id));
    }

    @Override
    public Collection<Spettacolo> getAll() {
        return spettacoli.values();
    }
}