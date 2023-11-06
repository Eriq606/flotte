package voiture.flotte.resources;

import java.time.LocalDateTime;

public class Kilometrage {
    private int id;
    private Voiture voiture;
    private LocalDateTime dateKilometrage;
    private double debutKilometrage, finKilometrage;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Voiture getVoiture() {
        return voiture;
    }
    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }
    public LocalDateTime getDateKilometrage() {
        return dateKilometrage;
    }
    public void setDateKilometrage(LocalDateTime dateKilometrage) {
        this.dateKilometrage = dateKilometrage;
    }
    public double getDebutKilometrage() {
        return debutKilometrage;
    }
    public void setDebutKilometrage(double debutKilometrage) {
        this.debutKilometrage = debutKilometrage;
    }
    public double getFinKilometrage() {
        return finKilometrage;
    }
    public void setFinKilometrage(double finKilometrage) {
        this.finKilometrage = finKilometrage;
    }
    
}
