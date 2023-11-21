/**
 * Classe Pneu représentant un pneu de vélo.
 * Cette classe contient des informations sur la largeur du pneu et si le pneu est tubeless ou non.
 */
public class Pneu extends Marque {
  private Integer largeur;
  private Boolean contientChambre;

  /**
   * Constructeur qui initialise un nouveau pneu avec la largeur, la présence de chambre à air et la marque.
   *
   * @param largeur La largeur du pneu.
   * @param contientChambre Vrai si le pneu est tubeless, faux autrement.
   * @param nomMarque Le nom de la marque du pneu.
   */
  public Pneu(Integer largeur, Boolean contientChambre, String nomMarque) {
    super(nomMarque);
    this.largeur = largeur;
    this.contientChambre = contientChambre;
  }

  public Integer getLargeur() {
    return largeur;
  }

  public void setLargeur(Integer largeur) {
    this.largeur = largeur;
  }

  public Boolean getContientChambre() {
    return contientChambre;
  }

  public void setContientChambre(Boolean contientChambre) {
    this.contientChambre = contientChambre;
  }
}
