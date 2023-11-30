package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import facade.GarageVelo;
import factory.VeloFactory;
import java.io.IOException;
import modele.Velo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visiteur.VisiteurAffichageDetaille;
import visiteur.VisiteurAffichageSimple;


/**
 * Tests Junit pour la Façade.
 * */
public class GarageVeloTest {

  private VeloFactory factory;
  private GarageVelo garageVelo;

  @BeforeEach
  public void setUp() {
    factory = new VeloFactory("Nike", 30);
    garageVelo = new GarageVelo(factory);
  }

  @Test
  public void testAjoutVelo() {
    Velo v1 = garageVelo.ajouterVelo("V11", 143, true, 50, "Michelin", "Michelin", "Michelin", 25);
    assertNotNull(v1);
  }

  @Test
  public void testMajBatterie() {
    garageVelo.ajouterVelo("V11", 143, true, 50, "Michelin", "Michelin", "Michelin", 25);
    boolean majBatterie = garageVelo.majBatterieVelo(143, 60, "Bosch");
    assertTrue(majBatterie);
  }

  @Test
  public void testSuppressionVelo() {
    garageVelo.ajouterVelo("V11", 143, true, 50, "Michelin", "Michelin", "Michelin", 25);
    Velo veloSupprime = garageVelo.supprimerVelo(143);
    assertNotNull(veloSupprime);
  }

  @Test
  public void testVerifierVelo() {
    garageVelo.ajouterVelo("V11", 143, true, 50, "Michelin", "Michelin", "Michelin", 25);
    garageVelo.ajouterVelo("V11", 144, true, 50, "Michelin", "Michelin", "Michelin", 100);

    String resultat = garageVelo.verifierVelo(143);
    String resultat2 = garageVelo.verifierVelo(144);

    assertEquals("Le vélo est correctement configuré.", resultat);
    assertFalse("Le vélo est correctement configuré.".equals(resultat2));

  }

  //q5
  @Test
  public void testChargementVelosDepuisFichier() throws IOException {
    String cheminFic = "C:\\Users\\jaoue\\Desktop\\S7\\IL\\ArchitectureLogicielle\\Garage2\\GarageVelosElec\\src\\Ressources\\listesVelos.json";
    garageVelo.chargerVelosDepuisFichier(cheminFic);

    System.out.println("Affichage Simple : ");
    garageVelo.afficherVelos(new VisiteurAffichageSimple());
    System.out.println();
    System.out.println("------------------------------------");
    System.out.println("Affichage Détaillé : ");
    garageVelo.afficherVelos(new VisiteurAffichageDetaille());
  }

}
