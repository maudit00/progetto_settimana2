package it.epicode;

import it.epicode.entities.ElementiCatalogo;
import it.epicode.entities.Libri;
import it.epicode.entities.Periodicità;
import it.epicode.entities.Riviste;
import jakarta.persistence.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
    final static EntityManager em = emf.createEntityManager();
    static Scanner scanner = new Scanner(System.in);

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
        String choice = "";
        List<String> menuMessages =  List.of(s1,s2,s3,s4,s5,s6,s7,s8);
        menuMessages.stream().forEach(message -> System.out.println(message));
        while (true){
            System.out.println("Fai la tua scelta da 1 a 8 e inserisci '0' per uscire dal programma");
            choice = scanner.nextLine();
            if (choice.equals(exit)){
                break;
            }
            menuChoice(choice);
        }
    }

    public static void menuChoice(String choosen){
        switch  (choosen){
            case "1" :
                add();
                break;
            case "2" :
                remove();
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
            } catch (Exception e){
                System.out.println(e);
            } finally {
                em.close();
                emf.close();
            }
        } else if (choice ==2) {

            try {
                Riviste rivista = addMagazine();
                et.begin();
                em.persist(rivista);
                et.commit();
            } catch (Exception e){
                System.out.println(e);
            } finally {
                em.close();
                emf.close();
            }
        }

    menu();
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
        scanner.nextLine();
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

    public static ElementiCatalogo getById (long id){
        return em.find(ElementiCatalogo.class, id) ;
    }
    public static void remove(){
        System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere");
        long id = scanner.nextLong();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            ElementiCatalogo e = getById(id);
            em.remove(e);
            et.commit();
            System.out.println("Elemento con id : " + id + " eliminato con successo");
        } catch (Exception e ){
            System.out.println(e);
        } finally {
       em.close();
       emf.close();
        }
    }

}