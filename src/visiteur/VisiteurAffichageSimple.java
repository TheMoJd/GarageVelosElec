package visiteur;

import modele.Batterie;
import modele.Pneu;
import modele.Velo;

/**
 * Classe implémentant l'interface  Visiteur pour un affichage simple des vélos et de leurs composants.
 * Cette classe fournit des méthodes pour afficher des informations de base sur les vélos, les batteries et les pneus.
 */
public class VisiteurAffichageSimple implements Visiteur {

  /**
   * Affiche des informations de base sur un vélo.
   * Affiche le modèle et le numéro de série du vélo.
   *
   * @param velo Le vélo à visiter. Les informations de base du vélo sont affichées.
   */
  @Override
  public void visitVelo(Velo velo) {
    System.out.println("Vélo Modèle: " + velo.getModele());
    System.out.println("Vélo Numéro de série: " + velo.getNumSerie());
  }

  /**
   * Affiche des informations de base sur une batterie.
   * Affiche la puissance de la batterie.
   *
   * @param batterie La batterie à visiter. La puissance de la batterie est affichée.
   */
  @Override
  public void visitBatterie(Batterie batterie) {
    System.out.println("Batterie Puissance: " + batterie.getPuissance());
  }

  /**
   * Affiche des informations de base sur un pneu.
   * Affiche la largeur du pneu.
   *
   * @param pneu Le pneu à visiter. La largeur du pneu est affichée.
   */
  @Override
  public void visitPneu(Pneu pneu) {
    System.out.println("Pneu Largeur: " + pneu.getLargeur());
  }
}
