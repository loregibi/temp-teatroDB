import connection_handler.ConnectionHandler;
import dao.*;
import model.Posto;
import model.Spettacolo;
import model.Ticket;
import model.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Teatro {

    private PostoDaoSql postoDaoSql = new PostoDaoSql();
    private SalaDaoSql salaDaoSql = new SalaDaoSql();
    private SedeDaoSql sedeDaoSql = new SedeDaoSql();
    private SpettacoloDaoSql spettacoloDaoSql = new SpettacoloDaoSql();
    private TicketDaoSql ticketDaoSql = new TicketDaoSql();
    private UtenteDaoSql utenteDaoSql = new UtenteDaoSql();

    public void inserisciUtente() throws SQLException {
        System.out.println("** Inserisci utente **");
        Scanner input = new Scanner(System.in);
        System.out.print("nome: ");
        String nome = input.nextLine();
        System.out.print("cognome: ");
        String cognome = input.nextLine();
        System.out.print("mail: ");
        String mail = input.nextLine();
        System.out.print("telefono: ");
        String telefono = input.nextLine();
        System.out.print("indirizzo: ");
        String indirizzo = input.nextLine();

        Utente utente = new Utente(nome,cognome,mail,telefono,indirizzo);
        utenteDaoSql.insert(utente);
    }

    public void prenotaSpettacolo(int idUtente) throws SQLException {

        List<Spettacolo> spettacoli = spettacoloDaoSql.getAll();
        ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
        System.out.println("Lista spettacoli: ");
        int count = 1;
        for (Spettacolo spettacolo : spettacoli) {
            System.out.println(count + ") " +  spettacolo.getNome());
            count++;
        }
        System.out.println("Inserisci il nome dello spettacolo: ");
        Scanner input = new Scanner(System.in);
        String spett = input.nextLine();

        PreparedStatement preparedStatement = connectionHandler.getPreparedStatement("Select id_posto,fila,numero  " +
                "From teatro.spettacolo sp " +
                "Join teatro.sala sa ON sp.id_sala = sa.id " +
                "Join teatro.posto p On p.id_sala=sa.id " +
                "Where sp.nome= '" + spett + "'");
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("Lista posti per lo spettacolo " + spett + " : ");
        while (rs.next()){
            System.out.println(Posto.fromResultSet(rs));
        }
        System.out.println("Inserisci la fila e il numero del posto da selezionare: ");
        Scanner input1= new Scanner(System.in);
        System.out.print("Fila: ");
        int fila = input1.nextInt();
        System.out.print("Numero: ");
        int numero = input1.nextInt();

        int idSpettacolo = spettacoloDaoSql.getIdByNome(spett);
        int idPosto = postoDaoSql.getPostoByFilaENumero(fila,numero);

        Ticket ticket = new Ticket(LocalDateTime.now().toString(),idPosto,idSpettacolo,idUtente);

        ticketDaoSql.insert(ticket);
    }




}
