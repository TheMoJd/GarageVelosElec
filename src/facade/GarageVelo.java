package Facade;

import Factory.ListVelosFactory;
import Factory.VeloFactory;
import Modele.Pneu;
import Modele.Velo;
import Visiteur.Visiteur;
import Visiteur.Visitable;
import Verification.VerifVelo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Façade pour la gestion d'un ensemble de vélos.
 * Fournit des opérations CRUD pour les vélos ainsi que des méthodes de vérification.
 */
public class GarageVelo {

  private Map<Integer, Velo> velos;
  private VeloFactory veloFactory;

  /**
   * Constructeur du GarageAVelo.
   *
   * @param veloFactory La factory utilisée pour créer des vélos.
   */
  public GarageVelo(VeloFactory veloFactory) {
    this.velos = new HashMap<>();
    this.veloFactory = veloFactory;
  }

  /**
   * Crée et ajoute un vélo au garage.
   *
   * @param modele Le modèle du vélo.
   * @param numSerie Le numéro de série du vélo.
   * @param contientChambre Si les pneus sont tubeless ou non.
   * @param puissanceBatterie La puissance de la batterie.
   * @return Le vélo créé ou null si la création échoue.
   */
  public Velo ajouterVelo(String modele, Integer numSerie, Boolean contientChambre, Integer puissanceBatterie) {
    if (!velos.containsKey(numSerie)) {
      Velo velo = veloFactory.creerVelo(modele, numSerie, contientChambre, puissanceBatterie);
      if (velo != null) {
        velos.put(numSerie, velo);
        return velo;
      }
    }
    return null;
  }

  /**
   * Récupère un vélo par son numéro de série.
   *
   * @param numSerie Le numéro de série du vélo.
   * @return Le vélo si trouvé, sinon null.
   */
  public Velo obtenirVelo(Integer numSerie) {
    return velos.get(numSerie);
  }

  /**
   * Met à jour la puissance et la marque de la batterie d'un vélo spécifique.
   *
   * @param numSerie Le numéro de série du vélo à mettre à jour.
   * @param nouvPuissance La nouvelle puissance de la batterie. Si null, la puissance n'est pas modifiée.
   * @param nouvMarqueBatterie La nouvelle marque de la batterie. Si null, la marque n'est pas modifiée.
   * @return true si la mise à jour a réussi, false si le vélo n'existe pas ou si les paramètres ne sont pas fournis.
   */

  public boolean mettreAJourBatterieVelo(Integer numSerie, Integer nouvPuissance, String nouvMarqueBatterie) {
    Velo velo = this.velos.get(numSerie);
    if (velo != null){
      if (nouvPuissance != null || nouvMarqueBatterie != null) {
        velo.getBatterie().setPuissance(nouvPuissance);
        velo.getBatterie().setMarque(nouvMarqueBatterie);
        return true;
      }
    }
    return false;
  }
  /**
   * Met à jour les caractéristiques du pneu avant d'un vélo spécifique.
   *
   * @param numSerie Le numéro de série du vélo à mettre à jour.
   * @param nouvLargeur La nouvelle largeur du pneu avant. Si null, la largeur n'est pas modifiée.
   * @param nouvContientChambre Indique si le nouveau pneu avant est tubeless. Si null, cette caractéristique n'est pas modifiée.
   * @param nouvMarquePneu La nouvelle marque du pneu avant. Si null, la marque n'est pas modifiée.
   * @return true si la mise à jour a réussi, false sinon.
   */

  public boolean mettreAJourPneusAvant(Integer numSerie, Integer nouvLargeur, Boolean nouvContientChambre, String nouvMarquePneu) {
    Velo velo = velos.get(numSerie);
    if (velo != null) {
      if(nouvMarquePneu != null || nouvContientChambre != null || nouvLargeur != null) {
        VeloFactory factory = new VeloFactory(nouvMarquePneu, nouvLargeur);
        Pneu nouveauPneuAv = factory.creerPneu(nouvContientChambre);
        velo.setPneuAv(nouveauPneuAv);
        return true;
      }
    }
    return false;
  }

  /**
   * Met à jour les caractéristiques du pneu arrière d'un vélo spécifique.
   *
   * @param numSerie Le numéro de série du vélo à mettre à jour.
   * @param nouvLargeur La nouvelle largeur du pneu arrière. Si null, la largeur n'est pas modifiée.
   * @param nouvContientChambre Indique si le nouveau pneu arrière est tubeless. Si null, cette caractéristique n'est pas modifiée.
   * @param nouvMarquePneu La nouvelle marque du pneu arrière. Si null, la marque n'est pas modifiée.
   * @return true si la mise à jour a réussi, false sinon.
   */

  public boolean mettreAJourPneusArriere(Integer numSerie, Integer nouvLargeur, Boolean nouvContientChambre, String nouvMarquePneu) {
    Velo velo = velos.get(numSerie);
    if (velo != null) {
      if(nouvMarquePneu != null || nouvContientChambre != null) {
        VeloFactory factory = new VeloFactory(nouvMarquePneu, nouvLargeur);
        Pneu nouveauPneuAr = factory.creerPneu(nouvContientChambre);
        velo.setPneuAr(nouveauPneuAr);
        return true;
      }
    }
    return false;
  }
  /**
   * Supprime un vélo du garage.
   *
   * @param numSerie Le numéro de série du vélo à supprimer.
   * @return true si le vélo a été supprimé, false sinon.
   */
  public Velo supprimerVelo(Integer numSerie) {
    Velo v = this.velos.remove(numSerie) ;
    if(v != null) {
      this.veloFactory.setNbVelosCrees(this.veloFactory.getNbVelosCrees()-1);
    }
    return v;
  }

  /**
   * Vérifie si tous les vélos du garage sont conformes aux critères de validation.
   *
   * @return true si tous les vélos sont valides, false sinon.
   */
  public String verifierVelos(Integer numSerie) {
    Velo v = this.velos.get(numSerie);
    return VerifVelo.verifVelo(v);
  }

  public void chargerVelosDepuisFichier(String cheminFichier) throws IOException {
    ListVelosFactory factory = new ListVelosFactory();
    List<Velo> velosLus = factory.creerVelosDepuisFichier(cheminFichier);
    for (Velo velo : velosLus) {
      if (!this.velos.containsKey(velo.getNumSerie())) {
        this.velos.put(velo.getNumSerie(), velo);
      }
    }
  }
  /**
   * Affiche tous les vélos existant dans le garage.
   * Méthode utiliser pour affichage des vélos avant la question 5  */

  public void afficherListVelos(){
    System.out.println("Liste de vélos : ");
    for (Velo v : this.velos.values()){
      v.afficherVelo();
      System.out.println();
    }
  }

  /**
   * Affiche tous les vélos existant dans le garage.
   * Méthode utiliser pour affichage des vélos après la question 5
   * */

  public void afficherVelos(Visiteur visiteur){
    for(Velo velo : this.velos.values()){
      velo.accept(visiteur);
    }
  }
}
