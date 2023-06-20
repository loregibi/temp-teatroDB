package dao;
import model.Sede;
import model.Spettacolo;

import java.util.*;

import java.util.HashMap;

public class SedeDao implements Dao<Sede> {

    private Map <Integer,Sede> sedi = new HashMap<>();

    public boolean insert(Sede s) {
        sedi.put(s.getId(), s);
        return sedi.get(s.getId()).equals(s);
    }

    @Override
    public boolean update(Sede s) {
        sedi.replace(s.getId(), s);
        return sedi.get(s.getId()).equals(s);
    }

    @Override
    public boolean delete(int id) {
        return sedi.remove(id) != null;
    }

    @Override
    public Optional<Sede> getById(int id) {
        return Optional.ofNullable(sedi.get(id));
    }

    @Override
    public Collection<Sede> getAll() {
        return sedi.values();
    }
}
