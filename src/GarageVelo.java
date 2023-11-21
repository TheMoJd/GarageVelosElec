import java.util.HashMap;
import java.util.Map;

/**
 * Façade pour gérer un ensemble de vélos, offrant des opérations CRUD et de vérification.
 * */
public class GarageVelo {
  private Map<Integer, Velo> velos;
  private VeloFactory veloFactory;

  public GarageVelo(VeloFactory velofactory){
    this.velos = new HashMap<>();
    this.veloFactory = velofactory;
  }
  /**
   * Crée et ajoute un nouveau vélo dans le garage.
   *
   * @param largeurPneu La largeur des pneus du vélo.
   * @param modele Le modèle du vélo.
   * @param numSerie Le numéro de série du vélo.
   * @return Le vélo créé ou null si la création échoue.
   */

  public Velo creerVelo(Integer largeurPneu, String modele, Integer numSerie) {
    if (!this.velos.containsKey(numSerie)) {
      //si ça existe pas déja dans la Factory
      Velo velo = this.veloFactory.creerVelo(largeurPneu, modele, numSerie);
      if (velo != null) {
        this.velos.put(numSerie, velo);
        return velo;
      }
    }
    return null;
  }
}




