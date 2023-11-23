/**
 * Classe qui permet de vérifier si un vélo est disponible ou non.
 */
public class VerifVelo {

  static final Integer LARGEUR_MIN = 19;
  static final Integer LARGEUR_MAX = 30;



  private static boolean verifierLargeur(double largeur) {
    return largeur >= LARGEUR_MIN && largeur <= LARGEUR_MAX;
  }

  private static boolean verifierLargeurIdentique(double largeurAvant, double largeurArriere) {
    return largeurAvant == largeurArriere;
  }

  private static boolean verifierMarque(String marqueAvant, String marqueArriere) {
    return marqueAvant.equals(marqueArriere);
  }

  private static boolean verifierMemePneu(Pneu pneuAvant, Pneu pneuArriere) {
    return pneuAvant == pneuArriere;
  }

  private static boolean verifierCompatibiliteChambre(boolean contientChambreAv, boolean contientChambreAr) {
    return contientChambreAv == contientChambreAr;
  }


  /**
   * Méthode qui permet de vérifier si les caractéristiques d'un vélo sont correctes.
   *
   * @param velo le vélo
   * @return un message indiquant si le vélo est conforme ou non
   */
  public static String verifVelo(Velo velo) {
    if (!verifierLargeur(velo.getPneuAv().getLargeur())) {
      return "Erreur : La largeur du pneu est non conforme .";
    }

    if (!verifierLargeur(velo.getPneuAr().getLargeur())) {
      return "Erreur : Largeur du pneu arrière est non conforme.";
    }

    if (verifierMemePneu(velo.getPneuAv(), velo.getPneuAr())) {
      return "Erreur : Les pneus avant et arrière sont les mêmes.";
    }

    if (!verifierCompatibiliteChambre(velo.getPneuAv().getContientChambre(), velo.getPneuAr().getContientChambre())) {
      return "Erreur : Les pneus avant et arrière n'ont pas le même état de chambre.";
    }

    return "Le vélo est correctement configuré.";
  }


}