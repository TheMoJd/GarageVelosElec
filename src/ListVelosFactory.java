import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class ListVelosFactory {
  public List<Velo> creerVelosDepuisFichier(String cheminFichier) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      // Lire le fichier JSON
      List<Velo> velos = mapper.readValue(new File(cheminFichier), new TypeReference<List<Velo>>() {});

      // Vérifier les numéros de série uniques
      HashSet<Integer> numerosSeries = new HashSet<>();
      for (Velo velo : velos) {
        if (!numerosSeries.add(velo.getNumSerie())) {
          throw new IllegalStateException("Numéro de série dupliqué détecté : " + velo.getNumSerie());
        }
      }

      return velos;
    } catch (IOException e) {
      e.printStackTrace();
      return null; // Ou gérer l'erreur comme vous le souhaitez
    }
  }
}
