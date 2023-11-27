package Visiteur;

import Modele.Batterie;
import Modele.Pneu;
import Modele.Velo;

public class VisiteurAffichageDetaille implements Visiteur {
  @Override
  public void visitVelo(Velo v) {
    if (v == null) {
      System.out.println("Erreur dans les paramètres données du vélo");
    } else {
      System.out.println();
      System.out.print("Vélo (" + v.getNumSerie() + " "  + v.getModele() +  ")" + "\n");
      System.out.println("Pneus Avant: largeur " + v.getPneuAv().getLargeur() + "mm" + (v.getPneuAv().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + v.getPneuAv().getMarque());
      System.out.println("Batterie : " + v.getBatterie().getPuissance() + "Ah" + ", marque : " + v.getBatterie().getMarque());
      System.out.println("Pneu Arrière : largeur " + v.getPneuAr().getLargeur() + "mm" + (v.getPneuAr().getContientChambre() ? " et contiennent chambre" : " ne contiennent pas de chambre") + ", marque : " + v.getPneuAr().getMarque());
      System.out.println();
    }
  }

  @Override
  public void visitBatterie(Batterie batterie) {
    System.out.println("Batterie Détail: " + batterie.getPuissance() + " - " + batterie.getMarque());
  }

  @Override
  public void visitPneu(Pneu pneu) {
    System.out.println("Pneu Détail: " + pneu.getLargeur() + " - " + pneu.getMarque());
  }
}


