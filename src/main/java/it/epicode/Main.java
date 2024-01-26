package it.epicode;

import it.epicode.entities.*;
import jakarta.persistence.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);

    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
    final static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        menu();
    }


    public static void menu (){

        System.out.println("Benvenuto nel sistema gestione Biblioteca");
        String s1 = "1 - Aggiunta di un elemento del Catalogo";
        String s2 = "2 - Rimozione di un elemento del Catalogo da ISBN";
        String s3 = "3 - Ricerca ISBN";
        String s4 = "4 - Ricerca per anno pubblicazione";
        String s5 = "5 - Ricerca per autore";
        String s6 = "6 - Ricerca per titolo o parte di esso";
        String s7 = "7 - Ricerca degli elementi in prestito tramite tessera utente";
        String s8 = "8 - Ricerca prestiti scaduti o non restituiti";
        String exit = "0";
        String choice ;
        List<String> menuMessages =  List.of(s1,s2,s3,s4,s5,s6,s7,s8);
        menuMessages.stream().forEach(message -> System.out.println(message));
            System.out.println("Fai la tua scelta da 1 a 8 e inserisci '0' per uscire dal programma");
            choice = scanner.nextLine();
            if (choice.equals(exit)){
                closeEM(em,emf);
                return;
            } else {
                menuChoice(choice);
            }
    }

    public static void menuChoice(String choosen){
        switch  (choosen){
            case "1" :
                try {
                    add();
                } catch (Exception e){
                    System.out.println(e);
                } finally{
                    menu();
                }
                break;
            case "2" :
                try {
                    remove();
                } catch (Exception e){
                    System.out.println(e);
                } finally{
                menu();
                }
                break;
            case "3" :
                try {
                    getByISBN();
                } catch (Exception e){
                    System.out.println(e);
                } finally{
                menu();
                }
                break;
            case "4" :
                try {
                    getByYear();
                } catch (Exception e){
                    System.out.println(e);
                } finally {
                    menu();
                }
                break;
            case "5" :
                try {
                    getByAuthor();
                } catch (Exception e){
                    System.out.println(e);
                } finally {
                    menu();
                }
                break;
            case "6" :
                try {
                    getByTitle();
                } catch (Exception e){
                    System.out.println(e);
                } finally {
                    menu();
                }
                break;
            case "7" :
                try {
                    getByStatus();
                } catch (Exception e){
                    System.out.println(e);
                } finally {
                    menu();
                }
                break;
            case "8" :
                try {
                    getExpired();
                } catch (Exception e){
                    System.out.println(e);
                } finally {
                    menu();
                }
                break;
        }
    }

    public static void add(){
        int choice = 0;
        EntityTransaction et = em.getTransaction();
        System.out.println("Scegli che tipo di elemento inserire: 1 - Libro , 2 - Rivista");
        choice = scanner.nextInt();
        if (choice == 1){
            try {
                Libri libro = addBook();
                et.begin();
                em.persist(libro);
                et.commit();
                System.out.println("Libro aggiunto con successo");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (choice ==2) {

            try {
                Riviste rivista = addMagazine();
                et.begin();
                em.persist(rivista);
                et.commit();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static Libri addBook () {
        Libri libro = new Libri();
        scanner.nextLine();
        System.out.println("Inserisci il titolo del libro");
        libro.setTitolo(scanner.nextLine());
        scanner.nextLine();
        System.out.println("Inserisci il numero delle pagine");
        libro.setnPagine(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Inserisci l'anno di pubblicazione (YYYY-MM-GG)");
        libro.setAnnoPubblicazione(LocalDate.parse(scanner.nextLine()));
        System.out.println("Inserisci l'autore del libro");
        libro.setAutore(scanner.nextLine());
        System.out.println("Inserisci il genere");
        libro.setGenere(scanner.nextLine());
        return libro;
    }
    public static Riviste addMagazine () {
        Riviste rivista = new Riviste();
        System.out.println("Inserisci il titolo della rivista");
        rivista.setTitolo(scanner.nextLine());
        scanner.nextLine();
        System.out.println("Inserisci il numero delle pagine");
        rivista.setnPagine(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Inserisci l'anno di pubblicazione (YYYY-MM-GG)");
        rivista.setAnnoPubblicazione(LocalDate.parse(scanner.nextLine()));
        scanner.nextLine();
            System.out.println("Inserisci la periodicità : 1 - SETTIMANALE , 2 - MENSILE, 3 - SEMESTRALE");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    rivista.setPeriodicità(Periodicità.SETTIMANALE);
                    break;
                case 2:
                    rivista.setPeriodicità(Periodicità.MENSILE);
                    break;
                case 3:
                    rivista.setPeriodicità(Periodicità.SEMESTRALE);
                    break;
                default:
                    System.out.println("Tipo periodicità corrispondente non disponibile");
                    break;
            }
        return rivista;
    }

    public static ElementiCatalogo getById (long id ){
        ElementiCatalogo e = em.find(ElementiCatalogo.class, id) ;
        return e;
    }
    public static void remove(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();
        System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere");
        long isbn = scanner.nextLong();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            ElementiCatalogo e = getById(isbn);
            em.remove(e);
            et.commit();
            System.out.println("Elemento con ISBN : " + isbn + " eliminato con successo");
        } catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static ElementiCatalogo getByISBN (){
        ElementiCatalogo e;
        Query query = em.createNamedQuery("elementoPerISBN");
        System.out.println("Inserisci l'isbn da cercare");
        long isbn = scanner.nextLong();
        query.setParameter("isbn", isbn);
        e = (ElementiCatalogo) query.getSingleResult();
        if (e.getISBN() == isbn){
            System.out.println("Il libro è il seguente");
            System.out.println(e);
        } else {
            System.out.println("Libro con ISBN: " + isbn + " non trovato.");
        }
        return e;
    }

    public static List<ElementiCatalogo> getByYear (){
        List<ElementiCatalogo> lista;
        Query query = em.createNamedQuery("elementoPerAnno");
        System.out.println("Inserisci l'anno da cercare");
        query.setParameter("anno", scanner.nextLine());
        lista = query.getResultList();
        if (lista.stream().toList().isEmpty()){
            System.out.println("Non ci sono risultati per l'anno cercato");
        }
        else {
            System.out.println("Il libri sono i seguenti");
            lista.stream().forEach(elemento -> System.out.println(elemento));
        }
        return lista;
    }


    public static List<Libri> getByAuthor (){
        List<Libri> lista;
        Query query = em.createNamedQuery("libroPerAutore");
        System.out.println("Inserisci l'autore da cercare");
        query.setParameter("autore", scanner.nextLine());
        lista = query.getResultList();
        if (lista.stream().toList().isEmpty()){
            System.out.println("Non ci sono risultati per l'autore cercato");
        }
        else {
            System.out.println("Il libri sono i seguenti");
            lista.stream().forEach(elemento -> System.out.println(elemento));
        }
        return lista;
    }

    public static List<ElementiCatalogo> getByTitle (){
        List<ElementiCatalogo> lista;
        Query query = em.createNamedQuery("elementoPerTitolo");
        System.out.println("Inserisci il titolo o una parte da cercare");
        query.setParameter("titolo", scanner.nextLine());
        lista = query.getResultList();
        if (lista.stream().toList().isEmpty()){
        System.out.println("Non ci sono risultati per il titolo cercato");
        }
        else {
            System.out.println("Il libri sono i seguenti");
            lista.stream().forEach(elemento -> System.out.println(elemento));
        }
        return lista;
    }

    public static List<ElementiCatalogo> getByStatus (){
        List<Prestiti> lista;
        Query query = em.createNamedQuery("prestitiPerTessera");
        System.out.println("Inserisci il numero della tessera da cercare");
        query.setParameter("utente", scanner.nextLine());
        lista = query.getResultList();
        if (lista.stream().toList().isEmpty()){
            System.out.println("Non ci sono risultati per per questo numero Tessera");
        }
        else {
            System.out.println("Il libri sono i seguenti");
            System.out.println(lista);
        }
        return lista.stream().map(elemento -> elemento.getElemento()).toList();
    }

    public static List<Prestiti> getExpired (){
        List<Prestiti> lista;
        Query query = em.createNamedQuery("prestitiScaduti");
        query.setParameter("data", LocalDate.now());
        lista = query.getResultList();
        if (lista.stream().toList().isEmpty()){
            System.out.println("Non ci sono risultati per prestiti scaduti");
        }
        else {
            System.out.println("I prestiti scaduti o non riconsegnati sono i seguenti");
            System.out.println(lista);
        }
        return lista;
    }
    public static void closeEM(EntityManager em, EntityManagerFactory emf){
        em.close();
        emf.close();
    }

}