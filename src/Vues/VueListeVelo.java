package Vues;

import Facade.GarageVelo;
import observer.Observer;
import Modele.Velo;
import javax.swing.*;
import java.awt.*;

/**
 * Vue qui gère l'ajout et la suppresion d'un vélo dans la liste de vélos.
 * VueListeVelo.*/
public class VueListeVelo  implements Observer {
  private GarageVelo garage;

  private JFrame frame;
  private JList<Velo> listVelos;
  private JButton btnAjouter, btnSupprimer;
  public VueListeVelo(GarageVelo garage) {
    this.garage = garage;
    garage.addObserver(this);

    // Création de l'IHM
    frame = new JFrame("Liste des Vélos");
    listVelos = new JList<>();
    btnAjouter = new JButton("Ajouter Vélo");
    btnSupprimer = new JButton("Supprimer Vélo");

    btnAjouter.addActionListener(e -> ajouterVelo());
    btnSupprimer.addActionListener(e -> supprimerVelo());

    JPanel panel = new JPanel();
    panel.add(btnAjouter);
    panel.add(btnSupprimer);

    // Ajout de l'écouteur pour la sélection de vélo
    listVelos.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        Velo selectedVelo = listVelos.getSelectedValue();
        if (selectedVelo != null) {
          garage.setVeloSelectionne(selectedVelo);
        }
      }
    });


    frame.add(panel, BorderLayout.NORTH);
    frame.add(new JScrollPane(listVelos), BorderLayout.CENTER);
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    // Initialisation de la liste
    update();
  }

  /**
   * Réception de notification qui indique qu'il y a un changement dons la réaction
   */
  @Override
  public void update() {
    // Mettre à jour la liste des vélos
    DefaultListModel<Velo> model = new DefaultListModel<>();
    for (Velo velo : garage.getVelos().values()) {
      model.addElement(velo);
    }
    listVelos.setModel(model);
  }

  public void ajouterVelo(){
    // Création d'une boîte de dialogue pour saisir les informations du nouveau vélo
    JTextField modeleField = new JTextField(5);
    JTextField numSerieField = new JTextField(5);
    JTextField puissanceBatterieField = new JTextField(5);
    JCheckBox contientChambreCheck = new JCheckBox();

    JPanel myPanel = new JPanel();
    myPanel.add(Box.createHorizontalStrut(15)); // un espace
    myPanel.add(new JLabel("Numéro de Série:"));
    myPanel.add(numSerieField);
    myPanel.add(new JLabel("Puissance Batterie:"));
    myPanel.add(puissanceBatterieField);
    myPanel.add(new JLabel("Contient Chambre:"));
    myPanel.add(contientChambreCheck);

    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Veuillez entrer les détails du Vélo", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        String modele = modeleField.getText();
        int numSerie = Integer.parseInt(numSerieField.getText());
        int puissanceBatterie = Integer.parseInt(puissanceBatterieField.getText());
        boolean contientChambre = contientChambreCheck.isSelected();

        // Ajouter le vélo au garage
        garage.ajouterVelo(modele, numSerie, contientChambre, puissanceBatterie);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erreur dans la saisie des données", "Erreur", JOptionPane.ERROR_MESSAGE);
      }
    }
    this.garage.notifierObservateurs();
  }

  public void supprimerVelo(){
    Velo velo = garage.getVeloSelectionne();

    if (velo != null) {
      this.garage.supprimerVelo(velo.getNumSerie());
    }
    this.garage.notifierObservateurs();
  }




}
