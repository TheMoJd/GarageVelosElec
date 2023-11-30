package tests;

import facade.GarageVelo;
import factory.VeloFactory;
import java.io.IOException;
import vues.VueListeVelo;
import vues.VueVelo;

/** Classe de tests unitaire pour les vues.*/
public class TestsVues {
  public static void main(String[] args) throws IOException {
    //q6
    VeloFactory factory = new VeloFactory("Nike", 30);
    GarageVelo garageVelo = new GarageVelo(factory);
    garageVelo.ajouterVelo("V11", 143, true, 50, "Noraauto", "Golf", "Michelin", 30);
    garageVelo.ajouterVelo("V12", 144, false, 45, "Noraauto", "Golf", "Michelin", 40);

    VueListeVelo vueListeVelo = new VueListeVelo(garageVelo);
    VueVelo vueVelo = new VueVelo(garageVelo);
  }
}
