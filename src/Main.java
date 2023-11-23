/**
 * Main.
 * */
public class Main {

  /**
  * main. */
  public static void main(String[] args) {
    VeloFactory factory = new VeloFactory("Nike",20);
    System.out.println("Nombre de vélos crées " + factory.getNbVelosCrees());
    Velo v = factory.creerVelo("V11", 11111,true,10);
    v.afficherVelo(v);
  }

}