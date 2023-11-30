package visiteur;

import modele.Batterie;
import modele.Pneu;
import modele.Velo;

/**
 * Interface pour le pattern Visiteur.
 * Fournit une méthode de visite pour chaque type d'élément visitable dans le modèle de vélo.
 * Les classes implémentant cette interface peuvent réaliser différentes opérations sur les éléments
 * du modèle de vélo (comme l'affichage des informations).
 */
public interface Visiteur {

  /**
   * Visite un vélo et effectue une opération définie sur celui-ci.
   *
   * @param velo Le vélo à visiter. L'implémentation peut effectuer diverses opérations sur le vélo.
   */
  void visitVelo(Velo velo);

  /**
   * Visite une batterie et effectue une opération définie sur celle-ci.
   *
   * @param batterie La batterie à visiter. L'implémentation peut effectuer
   *                 diverses opérations sur la batterie.
   */
  void visitBatterie(Batterie batterie);

  /**
   * Visite un pneu et effectue une opération définie sur celui-ci.
   *
   * @param pneu Le pneu à visiter. L'implémentation peut effectuer diverses opérations sur le pneu.
   */
  void visitPneu(Pneu pneu);
}
