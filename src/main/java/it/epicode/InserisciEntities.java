package it.epicode;

import it.epicode.entities.Libri;
import it.epicode.entities.Prestiti;
import it.epicode.entities.Utenti;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class InserisciEntities {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();
        Prestiti p1 = new Prestiti();
        Utenti u1 = new Utenti();
        Libri l1 = new Libri();

        EntityTransaction et = em.getTransaction();
        et.begin();
        u1.setNome("Mario");
        u1.setCognome("Rossi");
        em.persist(u1);

        l1.setTitolo("Se questo è un uomo");
        em.persist(l1);
        p1.setDataInizioPrestito(LocalDate.now());
        p1.setElemento(l1);
        p1.setUtente(u1);
        em.persist(p1);
        et.commit();
    }
}
