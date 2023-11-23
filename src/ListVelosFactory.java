/*
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ListVelosFactory {
  private List<Velo> listVelos;
  public List<Velo> creerVelosDepuisFichier(String cheminFichier) {
    //TODO Créer et retourner une liste de vélos
    //TODO S'assurer que chaque vélo a un numéro de série unique
    public List<Velo> creerVelosDepuisFichier(String cheminFichier) {
      ObjectMapper mapper = new ObjectMapper();
      try {
        // Structure pour contenir la liste de vélos
        TypeReference<List<Velo>> typeReference = new TypeReference<List<Velo>>() {};

        // Lecture et conversion du fichier JSON en objets Velo
        List<Velo> velos = mapper.readValue(new File(cheminFichier), typeReference);

        // Ici, ajoutez une vérification pour les numéros de série uniques si nécessaire

        return velos;
      } catch (IOException e) {
        e.printStackTrace();
        return null; // Ou gérer l'erreur comme vous le souhaitez
      }
    }
  }
}
*/
