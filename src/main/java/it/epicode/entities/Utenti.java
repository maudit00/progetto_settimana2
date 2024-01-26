package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name ="utenti")
public class Utenti {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column (name = "numero_tessera")
    private int numeroTessera;

    private String nome;

    private String cognome;
    @Column (name = "data_di_nascita")
    private LocalDate dataDiNascita;
    @OneToMany (mappedBy = "utente")
    private List<Prestiti> prestiti;
}
