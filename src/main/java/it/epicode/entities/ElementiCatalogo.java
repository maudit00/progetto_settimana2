package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "elementi_catalogo")
@Inheritance ( strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "elementoPerISBN", query="SELECT e FROM ElementiCatalogo e WHERE e.isbn = :isbn")
public class ElementiCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long isbn;
    private String titolo;
    @Column(name = "anno_pubblicazione")
    private LocalDate annoPubblicazione;
    @Column(name = "numero_pagine")
    private int nPagine;

    public ElementiCatalogo() {
    }

    public long getISBN() {
        return isbn;
    }

    public void setISBN(long ISBN) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getnPagine() {
        return nPagine;
    }

    public void setnPagine(int nPagine) {
        this.nPagine = nPagine;
    }

    @Override
    public String toString() {
        return
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", nPagine=" + nPagine
                ;
    }

    public ElementiCatalogo(long isbn, String titolo, LocalDate annoPubblicazione, int nPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.nPagine = nPagine;
    }
}
