package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String telefono;
    private String indirizzo;
}
