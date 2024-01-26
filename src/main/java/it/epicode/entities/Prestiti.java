package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestiti {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="utente_fk")
    private Utenti utente;

    @OneToOne
    @JoinColumn (name= "elemento_fk")
    private ElementiCatalogo elemento;

    @Column (name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;
    @Column (name = "data_restituzione_prevista")
    private LocalDate dataRestituzionePrevista ;
    @Column (name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    public Prestiti() {
    }

    public Prestiti(int id, Utenti utente, ElementiCatalogo elemento, LocalDate dataInizioPrestito,  LocalDate dataRestituzioneEffettiva) {
        this.id = id;
        this.utente = utente;
        this.elemento = elemento;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista =  dataInizioPrestito.plusDays(30);
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utenti getUtente() {
        return utente;
    }

    public void setUtente(Utenti utente) {
        this.utente = utente;
    }

    public ElementiCatalogo getElemento() {
        return elemento;
    }

    public void setElemento(ElementiCatalogo elemento) {
        this.elemento = elemento;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestiti{" +
                "id=" + id +
                ", utente=" + utente +
                ", elemento=" + elemento +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
