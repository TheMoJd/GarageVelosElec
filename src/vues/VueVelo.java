package vues;

import facade.GarageVelo;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modele.Velo;
import observer.Observer;

/** Classe VueVelo qui affiche les caractéristique d'un vélo.
 * */

public class VueVelo implements Observer {
  private final GarageVelo garage;
  private final JFrame frame;
  private final JTextField txtPuissanceBatterie;
  private final JTextField txtMarqueBatterie;
  private final JTextField txtLargeurPneuAv;
  private final JTextField txtLargeurPneuAr;

  private final JTextField txtMarquePneuAv;

  private final JTextField txtMarquePneuAr;

  private final JCheckBox chkContientChambreAv;
  private final JCheckBox chkContientChambreAr;

  private JButton btnSave;
  private Velo veloCourant;
  /**Constructeur de la VueVelo.
   * @param garage c'est un garage à vélo.
   * */

  public VueVelo(GarageVelo garage) {
    this.garage = garage;
    garage.addObserver(this);

    this.frame = new JFrame("Détails du Vélo");
    this.frame.setLayout(new GridLayout());
    GridBagConstraints gbc = new GridBagConstraints();


    txtPuissanceBatterie = new JTextField(10);
    txtMarqueBatterie = new JTextField(10);
    txtMarquePneuAr = new JTextField(10);
    txtMarquePneuAv = new JTextField(10);
    txtLargeurPneuAv = new JTextField(10);
    txtLargeurPneuAr = new JTextField(10);

    chkContientChambreAv = new JCheckBox();
    chkContientChambreAr = new JCheckBox();

    btnSave = new JButton("Enregistrer");

    // Configuration de base pour GridBagConstraints
    gbc.insets = new Insets(4, 4, 4, 4);
    gbc.gridx = 0; // Colonne 0
    gbc.gridy = 0; // Ligne 0
    gbc.anchor = GridBagConstraints.WEST;

    frame.add(new JLabel("Puissance Batterie:"), gbc);
    gbc.gridy++;
    frame.add(txtPuissanceBatterie, gbc);

    gbc.gridy++;
    frame.add(new JLabel("Marque Batterie:"), gbc);
    gbc.gridy++;
    frame.add(txtMarqueBatterie, gbc);

    // Continuer pour les autres champs
    gbc.gridy++;
    frame.add(new JLabel("Largeur Pneu Avant:"), gbc);
    gbc.gridy++;
    frame.add(txtLargeurPneuAv, gbc);

    gbc.gridy++;
    frame.add(new JLabel("Marque Pneu Avant:"), gbc);
    gbc.gridy++;
    frame.add(txtMarquePneuAv, gbc);

    gbc.gridy++;
    frame.add(new JLabel("Largeur Pneu Arrière:"), gbc);
    gbc.gridy++;
    frame.add(txtLargeurPneuAr, gbc);

    gbc.gridy++;
    frame.add(new JLabel("Marque Pneu Arrière:"), gbc);
    gbc.gridy++;
    frame.add(txtMarquePneuAr, gbc);

    gbc.gridy++;
    frame.add(new JLabel("Contient Chambre Avant:"), gbc);
    gbc.gridy++;
    frame.add(chkContientChambreAv, gbc);

    gbc.gridy++;
    frame.add(new JLabel("Contient Chambre Arrière:"), gbc);
    gbc.gridy++;
    frame.add(chkContientChambreAr, gbc);
    btnSave.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        enregistrerModifications();
      }
    });

    gbc.gridy++; // Ligne suivante
    gbc.gridx = 0; // Revenir à la première colonne
    gbc.gridwidth = 2; // S'étend sur deux colonnes
    btnSave.addActionListener(e -> enregistrerModifications());
    frame.add(btnSave, gbc);

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  /**
   * Enregistre les modifications apportés aux caractéristique d'un vélo.
   * */

  private void enregistrerModifications() {
    if (veloCourant != null) {
      try {
        Integer puissance = Integer.parseInt(txtPuissanceBatterie.getText());
        Boolean contientChambreAv = chkContientChambreAv.isSelected();
        Boolean contientChambreAr = chkContientChambreAr.isSelected();

        String marqueBatterie = txtMarqueBatterie.getText();
        String marquePneuAv = txtMarquePneuAv.getText();
        String marquePneuAr = txtMarquePneuAr.getText();

        int largeurPneuAv = Integer.parseInt(txtLargeurPneuAv.getText());
        int largeurPneuAr = Integer.parseInt(txtLargeurPneuAr.getText());


        // Vérifier si la chaîne n'est pas vide et contient uniquement des lettres (et des espaces)
        if (marqueBatterie.matches("[a-zA-Z]+")) {
          // Utiliser les méthodes de GarageVelo pour mettre à jour le vélo
          garage.majBatterieVelo(veloCourant.getNumSerie(), puissance, marqueBatterie);
        } else {
          JOptionPane.showMessageDialog(frame, "Données de la marque de "
                  + "Batterie ne sont pas valides");
        }


        if (marquePneuAv.matches("[a-zA-Z]+")) {
          garage.updatePneusAvant(veloCourant.getNumSerie(), contientChambreAv, largeurPneuAv, marquePneuAv); // la largeur et la marque des pneus ne changent pas
        } else {
          JOptionPane.showMessageDialog(frame, "Données de la marque du "
                  + "Pneu avant ne sont pas valides");
        }


        if (marquePneuAr.matches("[a-zA-Z]+")) {
          garage.updatePneusArriere(veloCourant.getNumSerie(), contientChambreAr, largeurPneuAr, marquePneuAr); // la largeur et la marque des pneus ne changent pas
        } else {
          JOptionPane.showMessageDialog(frame, "Données de la marque du Pneu arrière "
                  + "ne sont pas valides");
        }

        // Notifier le garage du changement
        garage.notifierObservateurs();
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Erreur dans la saisie des données");
      }
    }
  }


  /**
   * Définit le vélo actuellement sélectionné et met à jour les champs de l'interface utilisateur.
   * Cette méthode est utilisée pour mettre à jour l'interface utilisateur avec
   * les informations du vélo sélectionné.
   * Si le vélo passé en paramètre est {@code null}, les champs de l'interface utilisateur
   * ne sont pas mis à jour.
   *
   * @param velo Le vélo à définir comme le vélo courant dans l'interface utilisateur.
   */
  public void setVeloCourant(Velo velo) {
    this.veloCourant = velo;
    // Mettre à jour directement les champs de l'interface utilisateur
    if (velo != null) {
      txtPuissanceBatterie.setText(Integer.toString(velo.getBatterie().getPuissance()));
      txtMarqueBatterie.setText(velo.getBatterie().getMarque());
      txtLargeurPneuAv.setText(Integer.toString(velo.getPneuAv().getLargeur()));
      txtMarquePneuAv.setText(velo.getPneuAv().getMarque());
      txtLargeurPneuAr.setText(Integer.toString(velo.getPneuAr().getLargeur()));
      txtMarquePneuAr.setText(velo.getPneuAr().getMarque());
      chkContientChambreAv.setSelected(velo.getPneuAv().getContientChambre());
      chkContientChambreAr.setSelected(velo.getPneuAr().getContientChambre());
    }
  }

  /**
   * Met à jour l'état de l'interface utilisateur en fonction du vélo sélectionné dans le garage.
   * Cette méthode est appelée chaque fois qu'un changement est observé dans le garage
   * (par exemple, lors de la sélection d'un nouveau vélo).
   * Elle met à jour l'interface utilisateur pour refléter les informations
   * du vélo actuellement sélectionné.
   */
  @Override
  public void update() {
    Velo v = garage.getVeloSelectionne();
    if (v != null && !v.equals(veloCourant)) {
      setVeloCourant(v);
    }
  }
}


