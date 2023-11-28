package Facade;

import Factory.ListVelosFactory;
import Factory.VeloFactory;
import Modele.Pneu;
import Modele.Velo;
import Visiteur.Visiteur;
import Verification.VerifVelo;
import observer.Observer;
import observer.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Façade pour la gestion d'un ensemble de vélos.
 * Fournit des opérations CRUD pour les vélos ainsi que des méthodes de vérification.
 */
public class GarageVelo implements Subject {

  private Map<Integer, Velo> velos;
  private VeloFactory veloFactory;
  private List<Observer> observers = new ArrayList<>();
  private Velo veloSelectionne;



  /**
   * Constructeur du GarageAVelo.
   *
   * @param veloFactory La factory utilisée pour créer des vélos.
   */
  public GarageVelo(VeloFactory veloFactory) {
    this.velos = new HashMap<>();
    this.veloFactory = veloFactory;
  }

  public void setVeloSelectionne(Velo velo) {
    this.veloSelectionne = velo;
    notifierObservateurs();
  }

  public Velo getVeloSelectionne() {
    return veloSelectionne;
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
      velo.getBatterie().setPuissance(nouvPuissance);
      velo.getBatterie().setMarque(nouvMarqueBatterie);
      return true;
    }
    return false;
  }
  /**
   * Met à jour les caractéristiques du pneu avant d'un vélo spécifique.
   *
   * @param numSerie Le numéro de série du vélo à mettre à jour.
   * @param nouvContientChambre Indique si le nouveau pneu avant est tubeless. Si null, cette caractéristique n'est pas modifiée.
   * @return true si la mise à jour a réussi, false sinon.
   */

  public boolean mettreAJourPneusAvant(Integer numSerie, Boolean nouvContientChambre) {
    Velo velo = velos.get(numSerie);
    if (velo != null) {
      if(nouvContientChambre != null) {
        Pneu nouveauPneuAv = this.veloFactory.creerPneu(nouvContientChambre);
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
   * @param nouvContientChambre Indique si le nouveau pneu arrière est tubeless. Si null, cette caractéristique n'est pas modifiée.
   * @return true si la mise à jour a réussi, false sinon.
   */

  public boolean mettreAJourPneusArriere(Integer numSerie, Boolean nouvContientChambre) {
    Velo velo = velos.get(numSerie);
    if (velo != null) {
      if( nouvContientChambre != null) {
        Pneu nouveauPneuAr = this.veloFactory.creerPneu(nouvContientChambre);
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
   * Vérifie si un velo du garage est conforme aux critères de validation.
   *
   * @return true si tous les vélos sont valides, false sinon.
   */
  public String verifierVelo(Integer numSerie) {
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
   * @param visiteur le visiteur.
   * @return rien.*/

  public void afficherVelos(Visiteur visiteur){
    for(Velo velo : this.velos.values()){
      velo.accept(visiteur);
    }
  }

  public Map<Integer, Velo> getVelos() {
    return this.velos;
  }

  @Override
  public void addObserver(Observer o) {
    this.observers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    this.observers.remove(o);
  }

  @Override
  public void notifierObservateurs() {
    for(Observer observer : observers){
      observer.update();
    }
  }
}
