/**
 * classe Velo.
 * */
public class Velo implements Visitable{
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
  /**
   * Constructeur vide pour JSON.
   * */

  private Velo(){
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
  public void afficherVelo(Velo v){
    if (v == null) {
      System.out.println("Erreur dans les paramètres données du vélo");
    } else {
      System.out.println();
      System.out.print("Vélo (" + v.getNumSerie() + " "  + v.getModele() +  ")" + "\n");
      System.out.println("Pneus Avant: largeur " + v.getPneuAv().getLargeur() + "mm" + (v.getPneuAv().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + v.getPneuAv().getMarque());
      System.out.println("Batterie : " + v.getBatterie().getPuissance() + "Ah" + ", marque : " + v.getBatterie().getMarque());
      System.out.println("Pneu Arrière : largeur " + v.getPneuAr().getLargeur() + "mm" + (v.getPneuAr().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + v.getPneuAr().getMarque());
      System.out.println();
    }
  }

  @Override
  public void accept(Visiteur visiteur) {
    visiteur.visiter(this);
    // Assurez-vous également de visiter les composants du vélo
    this.batterie.accept(visiteur);
    this.pneuAv.accept(visiteur);
    this.pneuAr.accept(visiteur);
  }
}
