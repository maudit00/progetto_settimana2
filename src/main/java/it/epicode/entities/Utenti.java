package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name ="utenti")
@NamedQuery( name = "utentePerTessera", query = "SELECT u.prestiti FROM Utenti u WHERE u.numeroTessera = :utente")
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

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public List<Prestiti> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestiti> prestiti) {
        this.prestiti = prestiti;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "numeroTessera=" + numeroTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", prestiti=" + prestiti +
                '}';
    }

    public Utenti(int numeroTessera, String nome, String cognome, LocalDate dataDiNascita, List<Prestiti> prestiti) {
        this.numeroTessera = numeroTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.prestiti = prestiti;
    }

    public Utenti() {
    }

}
