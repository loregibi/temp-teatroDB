package dao;
import model.Utente;

import java.util.*;

import java.util.HashMap;

public class UtenteDao implements Dao<Utente> {

    private Map<Integer, Utente> utenti = new HashMap<>();

    public boolean insert(Utente u) {
        utenti.put(u.getId(), u);
        return utenti.get(u.getId()).equals(u);
    }


    @Override
    public boolean update(Utente u) {
        utenti.replace(u.getId(), u);
        return utenti.get(u.getId()).equals(u);
    }

    @Override
    public boolean delete(int id) {
        return utenti.remove(id) != null;
    }

    @Override
    public Optional<Utente> getById(int id) {
        return Optional.ofNullable(utenti.get(id));
    }

    @Override
    public Collection<Utente> getAll() {
        return utenti.values();
    }
}