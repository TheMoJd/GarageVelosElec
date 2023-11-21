/**
 * class VerifVelo.
* */
public class VerifVelo {
  /**
   * Méthode qui vérifie la conformité des pneus du vélo.
   *
   * @param v prends un vélo en paramètre.
   * @return true si les pneus correspondent aux conditions de la création d'un pneu si non false.
   * */
  public static Boolean verifPneus(Velo v) {
    Pneu pneuAvant = v.getPneuAv();
    Pneu pneuArriere = v.getPneuAr();
    return pneuAvant.getMarque().equals(pneuArriere.getMarque()) && pneuAvant.getLargeur().equals(pneuArriere.getLargeur())
            && pneuArriere.getContientChambre() == pneuAvant.getContientChambre()
            && pneuAvant.getLargeur() >= 19 && pneuArriere.getLargeur() <= 30;
  }
}
