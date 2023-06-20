package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Spettacolo {

    private int id;
    private String nome;
    private Date orario;
    private int durata;
    private String genere;
    private float prezzo;
}
