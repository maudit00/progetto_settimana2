package it.epicode.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LibriDAO {
    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
    final static EntityManager em = emf.createEntityManager();
}
