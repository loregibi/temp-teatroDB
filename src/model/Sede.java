package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Sede {

    private int id;
    private String nome;
    private String indirizzo;
    private String comune;
    private boolean isOpen;
}
