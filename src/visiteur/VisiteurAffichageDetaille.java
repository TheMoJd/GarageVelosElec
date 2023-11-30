package visiteur;

import modele.Batterie;
import modele.Pneu;
import modele.Velo;

/**
 * Classe implémentant l'interface {@link Visiteur} pour un affichage détaillé des vélos et de leurs composants.
 * Cette classe fournit des méthodes pour afficher des informations détaillées sur les vélos, les batteries et les pneus.
 */
public class VisiteurAffichageDetaille implements Visiteur {

  /**
   * Affiche les détails d'un vélo.
   * Inclut des informations sur le modèle, le numéro de série, la marque, et les détails des pneus avant et arrière et de la batterie.
   *
   * @param v Le vélo à visiter. Si {@code null}, un message d'erreur est affiché.
   */
  @Override
  public void visitVelo(Velo v) {
    if (v == null) {
      System.out.println("Erreur dans les paramètres données du vélo");
    } else {
      System.out.println();
      System.out.print("Vélo (" + v.getNumSerie() + " "  + v.getModele() +  ") " + v.getMarque() + "\n");
      System.out.println("Pneus Avant: largeur " + v.getPneuAv().getLargeur() + "mm" + (v.getPneuAv().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + v.getPneuAv().getMarque());
      System.out.println("Batterie : " + v.getBatterie().getPuissance() + "Ah" + ", marque : " + v.getBatterie().getMarque());
      System.out.println("Pneu Arrière : largeur " + v.getPneuAr().getLargeur() + "mm" + (v.getPneuAr().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + v.getPneuAr().getMarque());
      System.out.println();
    }
  }

  /**
   * Affiche les détails d'une batterie.
   * Inclut des informations sur la puissance et la marque de la batterie.
   *
   * @param batterie La batterie à visiter.
   */
  @Override
  public void visitBatterie(Batterie batterie) {
    System.out.println("Batterie Détail: " + batterie.getPuissance() + " - " + batterie.getMarque());
  }

  /**
   * Affiche les détails d'un pneu.
   * Inclut des informations sur la largeur et la marque du pneu.
   *
   * @param pneu Le pneu à visiter.
   */
  @Override
  public void visitPneu(Pneu pneu) {
    System.out.println("Pneu Détail: " + pneu.getLargeur() + " - " + pneu.getMarque());
  }
}
