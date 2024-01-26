package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "elementi_catalogo")
@Inheritance ( strategy = InheritanceType.TABLE_PER_CLASS)
public class ElementiCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ISBN;
    private String titolo;
    @Column(name = "anno_pubblicazione")
    private LocalDate annoPubblicazione;
    @Column(name = "numero_pagine")
    private int nPagine;

    public ElementiCatalogo() {
    }

    public ElementiCatalogo(long ISBN, String titolo, LocalDate annoPubblicazione, int nPagine) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.nPagine = nPagine;
    }
}
