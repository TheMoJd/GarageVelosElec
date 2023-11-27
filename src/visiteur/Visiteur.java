package Visiteur;

import Modele.Batterie;
import Modele.Pneu;
import Modele.Velo;

public interface Visiteur {
  void visitVelo(Velo velo);
  void visitBatterie(Batterie batterie);
  void visitPneu(Pneu pneu);
}
