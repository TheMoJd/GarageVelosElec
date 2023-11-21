/**
 * classe Velo.
 * */
public class Velo {
  private String modele;
  private Integer numSerie;
  private Pneu pneuAv;
  private Pneu pneuAr;

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

  private Batterie batterie;
  /**
 * Constructeur.
   * *
 * @param modele modele du vélo.
 * @param numSerie numéro de série du vélo.
 * @param b Batterie du vélo.
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

  public String getModele() {
    return modele;
  }

  public void setModele(String modele) {
    modele = modele;
  }

  public Integer getNumSerie() {
    return numSerie;
  }

  public void setNumSerie(Integer numSerie) {
    this.numSerie = numSerie;
  }
}
