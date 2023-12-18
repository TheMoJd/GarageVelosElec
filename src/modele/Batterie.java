package modele;

import visiteur.Visitable;
import visiteur.Visiteur;

/**
 * Classe représentant une batterie associée à une marque.
 * La classe hérite de la classe Modele.Marque pour associer chaque batterie à une marque spécifique.
 */
public class Batterie extends Marque implements Visitable {
  private Integer puissance;

  /**
   * Constructeur.
   *
   * @param nomMarque nom de la marque de la batterie.
   */

  public Batterie(String nomMarque) {
    super(nomMarque);
  }

  public Batterie() {
    super("");

  }

  public Integer getPuissance() {
    return this.puissance;
  }


  public void setPuissance(Integer puissance) {
    this.puissance = puissance;
  }

  public void accept(Visiteur visiteur) {
    visiteur.visitBatterie(this);
  }
}
