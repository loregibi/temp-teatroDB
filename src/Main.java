import connection_handler.ConnectionHandler;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("**** SYSTEM STARTUP ****");
        String databaseDriver = "org.postgresql.Driver";

        ConnectionHandler connectionHandler = ConnectionHandler.getInstance();


        // 1. Carico il driver
        Connection conn = connectionHandler.getConnection();


        //1) Gli utenti devono potersi registrare inserendo i loro dati personali.

        System.out.println("** Inserisci utente **");
        Scanner input = new Scanner(System.in);
        System.out.println("nome: ");
        String nome =input.nextLine();
        System.out.println("cognome: ");
        System.out.println("mail: ");
        System.out.println("telefono: ");
        System.out.println("indirizzo: ");

        PreparedStatement ps_insert_utenti = conn.prepareStatement("Insert utente");
        //2) Gli utenti registrati devono poter prenotare dei posti disponibili in uno spettacolo,
        //e il sistema deve calcolare il prezzo da pagare per l’utente.
        //3)  L’utente  deve  poter  ricercare  gli  spettacoli  disponibili  inserendo  la  città  e
        //una  data,  e facoltativamente un genere e un luogo specifico.
        //4) Poter ricevere dal sistema dei suggerimenti sui prossimi spettacoli, in particolare deve
        //ritornare tutti gli spettacoli del prossimo mese che hanno lo stesso genere degli ultimi 3 spettacoli visti.


                /*
        // 3. Creo oggetto PreparedStatement, passando come parametro il template della Query
        PreparedStatement ps_all_impiegati = conn.prepareStatement("select id, nome from impiegati");

        // TODO: Con un model disponibile mi andrei a creare l'oggetto Java (dipendente.setId(...),)
        // TODO: fattorizzare il metodo di lettura di un dipendente con un metodo ausiliario
        ResultSet rs = ps_all_impiegati.executeQuery();

        Impiegati.readImpiegatiFromResultSet(rs);


        // PS per modificare un dato (DML!)
        String queryAggiornaDipendente = "UPDATE impiegati SET nome=? WHERE id=?";
        PreparedStatement ps_aggiorna_dipendente = connectionHandler.getPreparedStatement(queryAggiornaDipendente);
        ps_aggiorna_dipendente.setString(1, "Carmelo");
        ps_aggiorna_dipendente.setInt(2, 1);
        ps_aggiorna_dipendente.executeUpdate();
        ps_aggiorna_dipendente.close();

        */
        // Rilascio tutte le risorse aperte.
        try {
            connectionHandler.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //ps_all_impiegati.close();
        //rs.close();


    }
}