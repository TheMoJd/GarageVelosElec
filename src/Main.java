/**
 * Main.
 * */
public class Main {

  /**
  * main */
  public static void main(String[] args) {
    VeloFactory factory = new VeloFactory("Nike",true, 10);
    System.out.println("Nombre de vélos crées " + factory.getNbVelosCrees());
    Velo v = factory.creerVelo(100, "V1", 111);
    if (v == null) {
      System.out.println("Erreur dans les paramètres données du vélo");
    } else {
      System.out.print("Nv vélo crée(" + v.getModele() + " " + v.getNumSerie() + ")" + " :\n Marque : " + v.getBatterie().getMarque() + "\n");
      System.out.println("Pneus : de largeur " + v.getPneuAv().getLargeur() + "mm" + (v.getPneuAv().getContientChambre() ? " qui contiennent des chambres" : "qui ne contiennent pas des chambres"));
      System.out.println("Batterie : " + v.getBatterie().getPuissance() + "Ah");
      System.out.println("Pneus : de largeur " + v.getPneuAv().getLargeur() + (v.getPneuAv().getContientChambre() ? " qui contiennent des chambres" : "qui ne contiennent pas des chambres"));
    }
  }
}