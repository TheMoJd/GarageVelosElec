package factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import modele.Velo;

/**
 * Factory pour créer une liste de vélos à partir d'un fichier JSON.
 * Cette classe utilise la bibliothèque Jackson pour la désérialisation de JSON en objets Velo.
 */
public class ListVelosFactory {

  /**
   * Crée une liste de vélos à partir des données d'un fichier JSON.
   * Cette méthode lit un fichier JSON spécifié par son chemin et convertit son contenu
   * en une liste d'objets Velo. Elle vérifie également l'unicité des numéros de série
   * des vélos.
   *
   * @param cheminFichier Le chemin du fichier JSON contenant les données des vélos.
   * @return Une liste de vélos construite à partir des données du fichier JSON.
   * @throws IOException Si une erreur se produit lors de la lecture du fichier.
   */
  public List<Velo> creerVelosDepuisFichier(String cheminFichier) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    // Lire le fichier JSON
    List<Velo> velos = mapper.readValue(new File(cheminFichier), new TypeReference<List<Velo>>() {
    });

    // Vérifier les numéros de série uniques
    HashSet<Integer> numerosSeries = new HashSet<>();
    for (Velo velo : velos) {
      if (!numerosSeries.add(velo.getNumSerie())) {
        System.out.println("Numéro de série dupliqué détecté : " + velo.getNumSerie());
      }
    }
    return velos;
  }
}