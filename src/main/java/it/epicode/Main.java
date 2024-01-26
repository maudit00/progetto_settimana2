package it.epicode;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
    final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        System.out.println("Benvenuto nel sistema gestione Biblioteca");
        String s1 = "1 - Aggiunta di un elemento del Catalogo";
        String s2 = "2 - Rimozione di un elemento del Catalogo da ISBN";
        String s3 = "3 - Ricerca ISBN";
        String s4 = "4 - Ricerca per anno pubblicazione";
        String s5 = "5 - Ricerca per autore";
        String s6 = "6 - Ricerca per titolo o parte di esso";
        String s7 = "7 - Ricerca degli elementi in prestito tramite tessera utente";
        String s8 = "8 - Ricerca prestiti scaduti o non restituiti";
        
        List<String> menuMessages =  List.of(s1,s2,s3,s4,s5,s6,s7,s8);
    }
}