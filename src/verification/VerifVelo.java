package verification;

import modele.Pneu;
import modele.Velo;

/**
 * Classe qui permet de vérifier si un vélo est conforme ou pas.
 */
public class VerifVelo {

  static final Integer LARGEUR_MIN = 19;
  static final Integer LARGEUR_MAX = 30;


  /**
   * Vérifie si la largeur d'un pneu est conforme aux normes définies.
   *
   * @param largeur La largeur du pneu à vérifier.
   * @return true si la largeur est conforme, false sinon.
   */

  private static boolean verifLargeur(double largeur) {
    return largeur >= LARGEUR_MIN && largeur <= LARGEUR_MAX;
  }

  /**
   * Vérifie si les largeurs des pneus avant et arrière sont identiques.
   *
   * @param largeurAvant La largeur du pneu avant.
   * @param largeurArriere La largeur du pneu arrière.
   * @return true si les largeurs sont identiques, false sinon.
   */
  private static boolean verifLargeurIdentique(double largeurAvant, double largeurArriere) {
    return largeurAvant == largeurArriere;
  }

  /**
   * Vérifie si les marques des pneus avant et arrière sont identiques.
   *
   * @param marqueAvant La marque du pneu avant.
   * @param marqueArriere La marque du pneu arrière.
   * @return true si les marques sont identiques, false sinon.
   */
  private static boolean verifMarque(String marqueAvant, String marqueArriere) {
    return marqueAvant.equals(marqueArriere);
  }

  /**
   * Vérifie si les pneus avant et arrière sont les mêmes.
   *
   * @param pneuAvant Le pneu avant.
   * @param pneuArriere Le pneu arrière.
   * @return true si les pneus sont les mêmes, false sinon.
   */
  private static boolean verifMemePneu(Pneu pneuAvant, Pneu pneuArriere) {
    return pneuAvant == pneuArriere;
  }


  /**
   * Vérifie si les pneus avant et arrière ont le même état de chambre.
   *
   * @param contientChambreAv Indique si le pneu avant contient une chambre.
   * @param contientChambreAr Indique si le pneu arrière contient une chambre.
   * @return true si les états de chambre sont identiques, false sinon.
   */
  private static boolean verifCompatibiliteChambre(boolean contientChambreAv,
                                                   boolean contientChambreAr) {
    return contientChambreAv == contientChambreAr;
  }


  /**
   * Méthode qui permet de vérifier si les caractéristiques d'un vélo sont correctes.
   *
   * @param velo le vélo
   * @return un message indiquant si le vélo est conforme ou non
   */
  public static String verifVelo(Velo velo) {
    if (!verifLargeur(velo.getPneuAv().getLargeur())) {
      return "Erreur : La largeur du pneu est non conforme .";
    }

    if (!verifLargeur(velo.getPneuAr().getLargeur())) {
      return "Erreur : Largeur du pneu arrière est non conforme.";
    }

    if (!verifLargeurIdentique(velo.getPneuAv().getLargeur(), velo.getPneuAr().getLargeur())) {
      return "Erreur : Largeur du pneu avant et pneu arrière ne sont pas pareils.";
    }

    if (!verifMarque(velo.getPneuAv().getMarque(), velo.getPneuAr().getMarque())) {
      return "Erreur : Marque des deux pneus sont différents.";
    }

    if (verifMemePneu(velo.getPneuAv(), velo.getPneuAr())) {
      return "Erreur : Les pneus avant et arrière sont les mêmes.";
    }

    if (!verifCompatibiliteChambre(velo.getPneuAv().getContientChambre(), velo.getPneuAr().getContientChambre())) {
      return "Erreur : Les pneus avant et arrière n'ont pas le même état de chambre.";
    }

    return "Le vélo est correctement configuré.";
  }


}