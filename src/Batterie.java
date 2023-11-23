/**
 * Classe représentant une batterie associée à une marque.
 * La classe hérite de la classe Marque pour associer chaque batterie à une marque spécifique.
 */
public class Batterie extends Marque {
  private Integer puissance;

  /**
   * Constructeur.
   *
   * @param nomMarque nom de la marque de la batterie.
   * */

  public Batterie(String nomMarque) {
    super(nomMarque);
  }

  public Integer getPuissance() {
    return this.puissance;
  }

  public boolean setPuissance(Integer puissance) {
    if(puissance == null) return false;
    this.puissance = puissance;
    return true;
  }
}
