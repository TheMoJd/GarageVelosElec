package modele;

/**
 * Class Modele.Marque.
 */
public class Marque {

  protected String marque;
  //l'attribut est accessible dans la classe elle-même,
  // dans toutes les classes qui en héritent (sous-classes),
  // ainsi que dans toutes les classes du même paquet (package)

  /**
   * Constructeur Marque.
   *
   * @param marque La marque.
   */


  public Marque(String marque) {
    this.marque = marque;
  }

  private Marque() {
  }

  public String getMarque() {
    return marque;
  }

  public void setMarque(String marque) {
    this.marque = marque;
  }
}
