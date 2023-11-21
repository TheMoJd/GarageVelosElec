/**
 * Classe représentant une batterie associée à une marque.
 * La classe hérite de la classe Marque pour associer chaque batterie à une marque spécifique.
 */
public class Batterie extends Marque {
  private Integer puissance;

  /**
   * Constructeur.
   *
   * @param puissance puissance de la batterie.
   * @param nomMarque nom de la marque de la batterie.
   * */

  public Batterie(Integer puissance, String nomMarque) {
    super(nomMarque);
    this.puissance = puissance;
  }

  public Integer getPuissance() {
    return this.puissance;
  }

  public void setPuissance(Integer puissance) {
    this.puissance = puissance;
  }
}
