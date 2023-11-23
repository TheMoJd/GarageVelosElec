public class GarageVeloTest {
  public static void main(String[] args) {
    VeloFactory factory = new VeloFactory("Nike", 30);
    GarageVelo garageVelo = new GarageVelo(factory);
    Velo v1 = garageVelo.ajouterVelo("V11", 143,true, 50);

    //Velo resAttenduNull = garageVelo.ajouterVelo(v1.getModele(), v1.getNumSerie(), v1.getPneuAv().getContientChambre(), v1.getBatterie().getPuissance());
    //System.out.println(resAttenduNull);
    if(v1 == null) System.out.println("Echec de l'ajout");
    else{
      Velo resSupp= garageVelo.supprimerVelo(143);
      if(resSupp == null) System.out.println("erreur lors de la suppression");
      else {
        garageVelo.afficherListVelos();
        System.out.println("Nombre de vélos dans le garge : " + factory.getNbVelosCrees());
      }
    }

  }
}
