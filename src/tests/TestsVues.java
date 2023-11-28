package Tests;

import Facade.GarageVelo;
import Factory.VeloFactory;
import Vues.VueListeVelo;
import Vues.VueVelo;

import java.io.IOException;

public class TestsVues {
  public static void main(String[] args) throws IOException {
    //q6
    VeloFactory factory = new VeloFactory("Nike", 30);
    GarageVelo garageVelo = new GarageVelo(factory);
    garageVelo.ajouterVelo("V11", 143, true, 50);
    garageVelo.ajouterVelo("V12", 144, false, 45);

    VueListeVelo vueListeVelo = new VueListeVelo(garageVelo);
    VueVelo vueVelo = new VueVelo(garageVelo);
  }
}
