package Tests;

import Facade.GarageVelo;
import Factory.VeloFactory;
import Visiteur.VisiteurAffichageDetaille;
import Visiteur.VisiteurAffichageSimple;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    VeloFactory factory = new VeloFactory("Nike", 30);
    GarageVelo garageVelo = new GarageVelo(factory);

    /*
    Test question 3
    // Test d'ajout d'un vélo
    Modele.Velo v1 = garageVelo.ajouterVelo("V11", 143, true, 50);
    if (v1 != null) {
      System.out.println("Vélo ajouté avec succès.");
    } else {
      System.out.println("Échec de l'ajout du vélo.");
    }


    // Affichage de la liste des vélos
    garageVelo.afficherListVelos();



    // Test de mise à jour de la batterie
    boolean majBatterie = garageVelo.mettreAJourBatterieVelo(143, 60, "Bosch");
    if (majBatterie) {
      System.out.println("Mise à jour de la batterie réussie.");
    } else {
      System.out.println("Échec de la mise à jour de la batterie.");
    }

    // Test de suppression d'un vélo
    Modele.Velo veloSupprime = garageVelo.supprimerVelo(143);
    if (veloSupprime != null) {
      System.out.println("Vélo supprimé avec succès.");
    } else {
      System.out.println("Échec de la suppression du vélo.");
    }
    */
    /*  Test question 4 : fichier JSON
    // Test d'ajout d'un vélo avec un numéro de série en double
    Modele.Velo v2 = garageVelo.ajouterVelo("V12", 143, false, 45);
    if (v2 == null) {
      System.out.println("Échec de l'ajout du vélo avec un numéro de série en double.");
    }


   // Test de vérification d'un vélo

    String verification = garageVelo.verifierVelos(143);
    System.out.println("Résultat de la vérification : " + verification);

    // Affichage de la liste des vélos
    //garageVelo.afficherListVelos();

    //test chargement de vélos depuis Json
    String cheminFic = "C:\\Users\\jaoue\\Desktop\\S7\\IL\\ArchitectureLogicielle\\Garage2\\GarageVelosElec\\src\\listesVelos.json";
    System.out.println(cheminFic);
    garageVelo.chargerVelosDepuisFichier(cheminFic);

    // Vérification que les vélos ont été chargés
    System.out.println("Vélos chargés depuis le fichier JSON :");*/
    //garageVelo.afficherListVelos();

    // test q5
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
