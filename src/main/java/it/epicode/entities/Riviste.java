package it.epicode.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table ( name = "riviste")
public class Riviste extends ElementiCatalogo {
    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    public Riviste(long ISBN, String titolo, LocalDate annoPubblicazione, int nPagine, Periodicità periodicità) {
        super(ISBN, titolo, annoPubblicazione, nPagine);
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "periodicità=" + periodicità;
    }

    public Riviste() {
    }
}
