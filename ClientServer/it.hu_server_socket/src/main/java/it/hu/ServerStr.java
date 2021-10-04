package it.hu;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr 
{
    ServerSocket server;//porta
    Socket client;//socket su cui ci andremo a collegare
    String stringaRicevuta; //stringa ricevuta dal client
    String stringaModificata;//stringa di risposta
    BufferedReader inDalClient;//lettura stream dal client
    DataOutputStream outVersoClient;//output stream verso client

    public Socket attendi(){ //attesa del collegamento di un client
        try {
            System.out.println("1 SERVER partito in esecuzione ...");
            server = new ServerSocket(6789);//istanza della porta
            client = server.accept();//accettazione del collegamento con il client
            server.close();//chiusura della porta
            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));//lettura dello stream dal client
            outVersoClient = new DataOutputStream(client.getOutputStream());//invio dello stream verso il client
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");}
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);

        }
        return client;   
    }

    public void comunica() {//comunicazione con il client
        try {
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo, Attendo ...");
            stringaRicevuta = inDalClient.readLine();//lettura stringa proveniente dal clientt
            
            System.out.println("6 ricevuta la stringa dal cliente : "+ stringaRicevuta);
            stringaModificata = stringaRicevuta.toUpperCase();//riscrivere la sttrings in maiuscolo
            System.out.println("7 invio la stringa modificata al cliente ...");
            outVersoClient.writeBytes( stringaModificata + '\n');//invio della stringa di risposta
            System.out.println("9 SERVER: fine elaborazione ... buona notte! "+'\n');
            client.close();//finita comunicazione
        } catch (Exception e) {
    
        }

    }
}
