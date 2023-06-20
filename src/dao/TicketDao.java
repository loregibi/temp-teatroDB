package dao;
import model.Ticket;


import java.util.*;

import java.util.HashMap;

public class TicketDao implements Dao<Ticket> {

    private Map<Integer, Ticket> tickets = new HashMap<>();

    public boolean insert(Ticket t) {
        tickets.put(t.getId(), t);
        return tickets.get(t.getId()).equals(t);
    }

    @Override
    public boolean update(Ticket t) {
        tickets.replace(t.getId(), t);
        return tickets.get(t.getId()).equals(t);
    }

    @Override
    public boolean delete(int id) {
        return tickets.remove(id) != null;
    }

    @Override
    public Optional<Ticket> getById(int id) {
        return Optional.ofNullable(tickets.get(id));
    }

    @Override
    public Collection<Ticket> getAll() {
        return tickets.values();
    }
}