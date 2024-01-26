package it.epicode.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="libri")
public class Libri extends ElementiCatalogo {
    private String autore;
    private String genere;

    public Libri() {
    }

    public Libri(long ISBN, String titolo, LocalDate annoPubblicazione, int nPagine, String autore, String genere) {
        super(ISBN, titolo, annoPubblicazione, nPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "autore='" + autore + '\'' +
                ", genere='" + genere ;
    }
}
