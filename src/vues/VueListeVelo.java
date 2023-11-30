package vues;

import facade.GarageVelo;
import observer.Observer;
import modele.Velo;

import javax.swing.*;
import java.awt.*;

/**
 * Vue qui gère l'ajout et la suppresion d'un vélo dans la liste de vélos.
 * VueListeVelo.
 */
public class VueListeVelo implements Observer {
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

    listVelos.setCellRenderer(new VeloCellRenderer());

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
   * Met à jour la liste des vélos affichée dans l'interface utilisateur.
   * Cette méthode est appelée en réponse à une notification indiquant qu'un changement a eu lieu dans le garage.
   * Elle rafraîchit la liste des vélos pour refléter l'état actuel du garage.
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

  /**
   * Ouvre une boîte de dialogue pour saisir les informations d'un nouveau vélo et l'ajoute au garage.
   * Si les informations saisies sont valides, le vélo est ajouté au garage. Sinon, un message d'erreur est affiché.
   */
  public void ajouterVelo() {
    // Création d'une boîte de dialogue pour saisir les informations du nouveau vélo
    JTextField modeleField = new JTextField(5);
    JTextField numSerieField = new JTextField(5);
    JTextField puissanceBatterieField = new JTextField(5);
    JTextField marquePneusField = new JTextField(5);
    JTextField largeurPneusField = new JTextField(5);

    JTextField marqueBatterieField = new JTextField(5);
    JTextField marqueVeloField = new JTextField(5);

    JCheckBox contientChambreCheck = new JCheckBox();

    JPanel myPanel = new JPanel();
    myPanel.add(Box.createHorizontalStrut(15)); // un espace
    myPanel.add(new JLabel("Numéro de Série:"));
    myPanel.add(numSerieField);
    myPanel.add(new JLabel("Modele:"));
    myPanel.add(modeleField);
    myPanel.add(new JLabel("Puissance Batterie:"));
    myPanel.add(puissanceBatterieField);
    myPanel.add(new JLabel("Marque Batterie:"));
    myPanel.add(marqueBatterieField);
    myPanel.add(new JLabel("Marque Vélo:"));
    myPanel.add(marqueVeloField);
    myPanel.add(new JLabel("Marque Pneu:"));
    myPanel.add(marquePneusField);
    myPanel.add(new JLabel("Largeur Pneus:"));
    myPanel.add(largeurPneusField);

    myPanel.add(new JLabel("Contient Chambre:"));
    myPanel.add(contientChambreCheck);

    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Veuillez entrer les détails du Vélo", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        String modele = modeleField.getText();
        Integer numSerie = Integer.parseInt(numSerieField.getText());
        Integer puissanceBatterie = Integer.parseInt(puissanceBatterieField.getText());
        Integer largeurPneus = Integer.parseInt(largeurPneusField.getText());
        Boolean contientChambre = contientChambreCheck.isSelected();
        String marqueBatterie = marqueBatterieField.getText();
        String marqueVelo = marqueVeloField.getText();
        String marquePneu = marquePneusField.getText();

        // Ajouter le vélo au garage
        garage.ajouterVelo(modele, numSerie, contientChambre, puissanceBatterie, marqueBatterie, marquePneu, marqueVelo, largeurPneus);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erreur dans la saisie des données", "Erreur", JOptionPane.ERROR_MESSAGE);
      }
    }
    this.garage.notifierObservateurs();
  }

  /**
   * Supprime le vélo actuellement sélectionné dans le garage.
   * Si aucun vélo n'est sélectionné, aucune action n'est effectuée. Après la suppression, les observateurs sont notifiés.
   */
  public void supprimerVelo() {
    Velo velo = garage.getVeloSelectionne();

    if (velo != null) {
      this.garage.supprimerVelo(velo.getNumSerie());
    }
    this.garage.notifierObservateurs();
  }


}
