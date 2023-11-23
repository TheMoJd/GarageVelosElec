/**
 * Classe VeloFactory. Les pneus d'un vélo possédent la même largeur de pneu et la même marque. La marque de la
 * batterie d'un vélo est la même aussi. Donc ces derniers sont des attribut privés appartenant à la Factory déja déterminé.
 *
 * */
public class VeloFactory {
  private int nbVelosCrees = 0;
  private String marque;
  private final Integer largeurPneu;
  /**
   * Constructeur.
   *
   * @param marqueVelo marque de l'usine.
   */

  public VeloFactory(String marqueVelo, Integer largeurPneu) {
    this.marque = marqueVelo;
    this.largeurPneu = largeurPneu;
  }

  public int getNbVelosCrees() {
    return nbVelosCrees;
  }
  /**
   * Crée un pneu avec la marque et la largeur spécifiées.
   *
   * @return Un pneu nouvellement créé
   */
  public Pneu creerPneu(Boolean contientChambre) {
    return new Pneu(this.largeurPneu, contientChambre, this.marque);
  }

  /**
   * Crée une batterie avec la puissance spécifiée.
   *
   * @return Une batterie nouvellement créée
   */
  public Batterie creerBatterie(Integer puissanceBatterie) {
    Batterie b = new Batterie(this.marque);
    b.setPuissance(puissanceBatterie);
    return b;
  }

  /**
   * @param modele le modèle du vélo
   * @param numSerie le numéro de série
   * @param contientChambre Indique si les pneus du vélo sont tubeless ou non.
   * @param puissanceBatterie La puissance de la batterie du vélo.
   *
   * @return un vélo
   * */
  public Velo creerVelo(String modele, Integer numSerie, Boolean contientChambre, Integer puissanceBatterie) {
    Pneu pneuAv = creerPneu(contientChambre);
    Pneu pneuArr = creerPneu(contientChambre);
    Batterie b = creerBatterie(puissanceBatterie);
    Velo v = new Velo(modele, numSerie, pneuAv, pneuArr, b);
    if (VerifVelo.verifVelo(v).equals("Le vélo est correctement configuré.")) {
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

  public void setNbVelosCrees(int nbVelosCrees) {
    this.nbVelosCrees = nbVelosCrees;
  }

  public Integer getLargeurPneu() {
    return largeurPneu;
  }
}
