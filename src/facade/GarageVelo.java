package facade;

import factory.ListVelosFactory;
import factory.VeloFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modele.Pneu;
import modele.Velo;
import observer.Observer;
import observer.Subject;
import verification.VerifVelo;
import visiteur.Visiteur;


/**
 * Façade pour la gestion d'un ensemble de vélos.
 * Fournit des opérations CRUD pour les vélos ainsi que des méthodes de vérification.
 */
public class GarageVelo implements Subject {

  private final Map<Integer, Velo> velos;
  private final VeloFactory veloFactory;
  private final List<Observer> observers = new ArrayList<>();
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
    return this.veloSelectionne;
  }

  /**
   * Crée et ajoute un vélo au garage.
   *
   * @param modele            Le modèle du vélo.
   * @param numSerie          Le numéro de série du vélo.
   * @param contientChambre   Si les pneus sont tubeless ou non.
   * @param puissanceBatterie La puissance de la batterie.
   * @return Le vélo créé ou null si la création échoue.
   */
  public Velo ajouterVelo(String modele, Integer numSerie, Boolean contientChambre,
                          Integer puissanceBatterie,
                          String marqueBattrie, String marquePneu,
                          String marqueVelo, Integer largeurPneus) {
    if (!velos.containsKey(numSerie)) {
      Velo velo = veloFactory.creerVelo(modele, numSerie, contientChambre,
              puissanceBatterie, marqueVelo);
      velo.getBatterie().setMarque(marqueBattrie);
      velo.getPneuAr().setMarque(marquePneu);
      velo.getPneuAv().setMarque(marquePneu);
      velo.getPneuAv().setLargeur(largeurPneus);
      velo.getPneuAr().setLargeur(largeurPneus);
      velos.put(numSerie, velo);
      return velo;
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
    return this.velos.get(numSerie);
  }

  /**
   * Met à jour la puissance et la marque de la batterie d'un vélo spécifique.
   *
   * @param numSerie           Le numéro de série du vélo à mettre à jour.
   * @param nouvPuissance      La nouvelle puissance de la batterie.
   * @param nouvMarqueBatterie La nouvelle marque de la batterie.
   * @return true si la mise à jour a réussi, false si le vélo n'existe pas.
   */
  public boolean majBatterieVelo(Integer numSerie, Integer nouvPuissance, String nouvMarqueBatterie) {
    Velo velo = this.velos.get(numSerie);
    if (velo != null) {
      velo.getBatterie().setPuissance(nouvPuissance);
      velo.getBatterie().setMarque(nouvMarqueBatterie);
      return true;
    }
    return false;
  }

  /**
   * Met à jour les caractéristiques du pneu avant d'un vélo spécifique.
   *
   * @param numSerie            Le numéro de série du vélo à mettre à jour.
   * @param nouvContientChambre Indique si le nouveau pneu avant est tubeless.
   * @return true si la mise à jour a réussi, false sinon.
   */

  public boolean updatePneusAvant(Integer numSerie, Boolean nouvContientChambre, Integer largeurPneu, String marquePneu) {
    Velo velo = velos.get(numSerie);
    if (velo != null) {
      if (nouvContientChambre != null) {
        Pneu nouveauPneuAv = this.veloFactory.creerPneu(nouvContientChambre);
        nouveauPneuAv.setMarque(marquePneu);
        nouveauPneuAv.setLargeur(largeurPneu);
        velo.setPneuAv(nouveauPneuAv);
        return true;
      }
    }
    return false;
  }

  /**
   * Met à jour les caractéristiques du pneu arrière d'un vélo spécifique.
   *
   * @param numSerie            Le numéro de série du vélo à mettre à jour.
   * @param nouvContientChambre Indique si le nouveau pneu arrière est tubeless. Si null, cette caractéristique n'est pas modifiée.
   * @return true si la mise à jour a réussi, false sinon.
   */

  public boolean updatePneusArriere(Integer numSerie, Boolean nouvContientChambre, Integer largeurPneu, String marquePneu) {
    Velo velo = velos.get(numSerie);
    if (velo != null) {
      Pneu nouveauPneuAr = this.veloFactory.creerPneu(nouvContientChambre);
      nouveauPneuAr.setLargeur(largeurPneu);
      nouveauPneuAr.setMarque(marquePneu);
      velo.setPneuAr(nouveauPneuAr);
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
    Velo v = this.velos.remove(numSerie);
    if (v != null) {
      this.veloFactory.setNbVelosCrees(this.veloFactory.getNbVelosCrees() - 1);
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

  /**
   * Charge une liste de vélos à partir d'un fichier JSON spécifié et les ajoute au garage.
   * Cette méthode utilise {@link ListVelosFactory} pour lire et créer des objets Velo à partir du fichier JSON.
   * Chaque vélo lu est ajouté au garage à condition que son numéro de série soit unique.
   *
   * @param cheminFichier Le chemin du fichier JSON à partir duquel les vélos seront chargés.
   * @throws IOException Si une erreur se produit lors de la lecture du fichier.
   */
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
   * Méthode utiliser pour affichage des vélos aprés la question 5
   *
   * @param visiteur le visiteur.
   */

  public void afficherVelos(Visiteur visiteur) {
    for (Velo velo : this.velos.values()) {
      velo.accept(visiteur);
    }
  }

  /**
   * Retourne la carte des vélos présents dans le garage.
   * La carte mappe les numéros de série des vélos à leurs instances correspondantes.
   *
   * @return Une Map associant les numéros de série des vélos à leurs instances.
   */
  public Map<Integer, Velo> getVelos() {
    return this.velos;
  }

  /**
   * Ajoute un observateur à la liste des observateurs.
   * Lorsque l'état du garage change, tous les observateurs ajoutés sont notifiés.
   *
   * @param o L'observateur à ajouter.
   */
  @Override
  public void addObserver(Observer o) {
    this.observers.add(o);
  }

  /**
   * Supprime un observateur de la liste des observateurs.
   * L'observateur supprimé ne recevra plus de notifications lorsque l'état du garage change.
   *
   * @param o L'observateur à supprimer.
   */
  @Override
  public void removeObserver(Observer o) {
    this.observers.remove(o);
  }

  /**
   * Notifie tous les observateurs enregistrés d'un changement dans le garage.
   * Cette méthode est généralement appelée après une modification de l'état du garage,
   * comme l'ajout ou la suppression d'un vélo.
   */
  @Override
  public void notifierObservateurs() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

}
