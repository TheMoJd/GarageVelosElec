package Modele;
import Visiteur.Visiteur;
import Visiteur.Visitable;

/**
 * classe Modele.Velo.
 * */
public class Velo implements Visitable {
  private String modele;
  private Integer numSerie;
  private Pneu pneuAv;
  private Pneu pneuAr;
  private Batterie batterie;


  public Pneu getPneuAv() {
    return pneuAv;
  }

  public void setPneuAv(Pneu pneuAv) {
    this.pneuAv = pneuAv;
  }

  public Pneu getPneuAr() {
    return pneuAr;
  }

  public void setPneuAr(Pneu pneuAr) {
    this.pneuAr = pneuAr;
  }

  public Batterie getBatterie() {
    return batterie;
  }

  public void setBatterie(Batterie batterie) {
    this.batterie = batterie;
  }

  /**
 * Constructeur.
   * *
 * @param modele modele du vélo.
 * @param numSerie numéro de série du vélo.
 * @param b Modele.Batterie du vélo.
 * @param pneuAv pneu Avant.
 * @param pneuAr pneu Arrière.
 * */

  public Velo(String modele, Integer numSerie, Pneu pneuAv, Pneu pneuAr, Batterie b) {
    this.modele = modele;
    this.numSerie = numSerie;
    this.pneuAv = pneuAv;
    this.pneuAr = pneuAr;
    this.batterie = b;
  }
  /**
   * Constructeur vide pour JSON.
   * */

  private Velo(){
  }

  public String getModele() {
    return this.modele;
  }

  public void setModele(String modele) {
    this.modele = modele;
  }

  public Integer getNumSerie() {
    return this.numSerie;
  }

  public void setNumSerie(Integer numSerie) {
    this.numSerie = numSerie;
  }

  @Override
  public String toString() {
    return "<html>Vélo Modèle: " + this.modele +
            ", Numéro de Série: " + this.numSerie +
            "<br/>Pneus Avant: largeur " + this.getPneuAv().getLargeur() + "mm" + (this.getPneuAv().getContientChambre() ? ", contiennent chambre" : ", ne contiennent pas de chambre") + ", marque : " + this.getPneuAv().getMarque() +
            "<br/>Pneu Arrière: largeur " + this.getPneuAr().getLargeur() + "mm" + (this.getPneuAr().getContientChambre() ? ", contiennent chambre" : ", ne contiennent pas de chambre") + ", marque : " + this.getPneuAr().getMarque() +
            "<br/>Batterie: " + this.getBatterie().getPuissance() + "Ah, marque : " + this.getBatterie().getMarque() +
            "</html>";
  }



  public void afficherVelo(){
    System.out.println();
    System.out.print("Vélo (" + this.getNumSerie() + " "  + this.getModele() +  ")" + "\n");
    System.out.println("Pneus Avant: largeur " + this.getPneuAv().getLargeur() + "mm" + (this.getPneuAv().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + this.getPneuAv().getMarque());
    System.out.println("Batterie : " + this.getBatterie().getPuissance() + "Ah" + ", marque : " + this.getBatterie().getMarque());
    System.out.println("Pneu Arrière : largeur " + this.getPneuAr().getLargeur() + "mm" + (this.getPneuAr().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + this.getPneuAr().getMarque());
    System.out.println();
  }

  @Override
  public void accept(Visiteur visiteur) {
    visiteur.visitVelo(this);
    // Assurez-vous également de visiter les composants du vélo
    this.batterie.accept(visiteur);
    this.pneuAv.accept(visiteur);
    this.pneuAr.accept(visiteur);
  }
}
