public class VisiteurAffichageSimple implements Visiteur {
  @Override
  public void visiter(Velo velo) {
    System.out.println("Vélo Modèle: " + velo.getModele());
    System.out.println("Vélo Numéro de serie: " + velo.getNumSerie());
  }

  @Override
  public void visiter(Batterie batterie) {
    System.out.println("Batterie Puissance: " + batterie.getPuissance());
  }

  @Override
  public void visiter(Pneu pneu) {
    System.out.println("Pneu Largeur: " + pneu.getLargeur());
  }



}
