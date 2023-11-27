package Visiteur;

import Modele.Batterie;
import Modele.Pneu;
import Modele.Velo;

public class VisiteurAffichageSimple implements Visiteur {
  @Override
  public void visitVelo(Velo velo) {
    System.out.println("Vélo Modèle: " + velo.getModele());
    System.out.println("Vélo Numéro de serie: " + velo.getNumSerie());
  }

  @Override
  public void visitBatterie(Batterie batterie) {
    System.out.println("Batterie Puissance: " + batterie.getPuissance());
  }

  @Override
  public void visitPneu(Pneu pneu) {
    System.out.println("Pneu Largeur: " + pneu.getLargeur());
  }



}
