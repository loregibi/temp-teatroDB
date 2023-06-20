import connection_handler.ConnectionHandler;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("**** SYSTEM STARTUP ****");
        String databaseDriver = "org.postgresql.Driver";

        ConnectionHandler connectionHandler = ConnectionHandler.getInstance();


        // 1. Carico il driver
        Connection conn = connectionHandler.getConnection();

        // 2. Carico il driver (non più necessario nelle nuove versioni
        Class.forName(databaseDriver);

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


        // Rilascio tutte le risorse aperte.
        try {
            connectionHandler.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ps_all_impiegati.close();
        rs.close();
    }
}