/**
 * Classe VeloFactory.
 * */
public class VeloFactory {

  private int nbVelosCrees = 0;
  private String marque;
  private Integer puissanceBatterie;
  private boolean contientChambre;
  /**
   * Constructeur.
   *
   * @param marqueVelo marque de l'usine.
   */

  public VeloFactory(String marqueVelo, Boolean contientChambre, Integer puissanceBatterie) {
    this.marque = marqueVelo;
    this.contientChambre = contientChambre;
    this.puissanceBatterie = puissanceBatterie;
  }

  public int getNbVelosCrees() {
    return nbVelosCrees;
  }

  /**
   * @param largeurPneu la largeur du pneu
   * @param modele le modèle du vélo
   * @param numSerie le numéro de série
   * @return un vélo
   * */
  public Velo creerVelo(Integer largeurPneu, String modele, Integer numSerie) {
    Pneu pneuAv = new Pneu(largeurPneu, this.contientChambre, this.marque);
    Pneu pneuArr = new Pneu(largeurPneu, this.contientChambre, this.marque);
    Batterie b = new Batterie(this.puissanceBatterie, this.marque);
    Velo v = new Velo(modele, numSerie, pneuAv, pneuArr, b);
    if (VerifVelo.verifPneus(v)) {
      this.nbVelosCrees++;
    } else {
      v = null;
    }
    return v;
  }

  public String getMarque() {
    return this.marque;
  }

  public void setMarque(String marque) {
    this.marque = marque;
  }

  public Integer getPuissanceBatterie() {
    return this.puissanceBatterie;
  }

  public void setPuissanceBatterie(Integer puissanceBatterie) {
    this.puissanceBatterie = puissanceBatterie;
  }

  public boolean isContientChambre() {
    return this.contientChambre;
  }

  public void setContientChambre(boolean contientChambre) {
    this.contientChambre = contientChambre;
  }
}
