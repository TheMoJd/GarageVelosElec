package Vues;

import Facade.GarageVelo;
import observer.Observer;
import Modele.Velo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueVelo implements Observer {
  private GarageVelo garage;
  private JFrame frame;
  private JTextField  txtPuissanceBatterie;
  private JTextField txtMarqueBatterie;
  private JCheckBox chkContientChambre;
  private JButton btnSave;
  private Velo veloCourant;

  public VueVelo(GarageVelo garage) {
    this.garage = garage;
    garage.addObserver(this);

    this.frame = new JFrame("Détails du Vélo");
    this.frame.setLayout(new GridLayout(4, 2));

    txtPuissanceBatterie = new JTextField();
    txtMarqueBatterie = new JTextField();
    chkContientChambre = new JCheckBox();
    btnSave = new JButton("Enregistrer");

    btnSave.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        enregistrerModifications();
      }
    });

    this.frame.add(new JLabel("Puissance Batterie:"));
    this.frame.add(txtPuissanceBatterie);
    this.frame.add(new JLabel("Marque Batterie:"));
    this.frame.add(txtMarqueBatterie);
    this.frame.add(new JLabel("Contient Chambre:"));
    this.frame.add(chkContientChambre);
    this.frame.add(btnSave);

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

    private void enregistrerModifications() {
      if (veloCourant != null) {
        try {
          int puissance = Integer.parseInt(txtPuissanceBatterie.getText());
          boolean contientChambre = chkContientChambre.isSelected();
          String marqueBatterie = txtMarqueBatterie.getText();

          // Vérifier si la chaîne n'est pas vide et contient uniquement des lettres (et des espaces)
          if (marqueBatterie != null && marqueBatterie.matches("[a-zA-Z]+")) {
            // Utiliser les méthodes de GarageVelo pour mettre à jour le vélo
            garage.mettreAJourBatterieVelo(veloCourant.getNumSerie(), puissance, marqueBatterie);
          } else {
            JOptionPane.showMessageDialog(frame, "La marque de la batterie ne peut pas être vide.");
          }
          garage.mettreAJourPneusAvant(veloCourant.getNumSerie(),  contientChambre); // la largeur et la marque des pneus ne changent pas
          garage.mettreAJourPneusArriere(veloCourant.getNumSerie(), contientChambre); // la largeur et la marque des pneus ne changent pas

          // Notifier le garage du changement
          garage.notifierObservateurs();
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(frame, "Erreur dans la saisie des données");
        }
      }
    }

  @Override
  public void update() {
    Velo v = garage.getVeloSelectionne();
    // Mettre à jour les champs avec les informations du vélo courant
    if (v != null) {
      setVeloCourant(v);
      this.txtPuissanceBatterie.setText(Integer.toString(this.veloCourant.getBatterie().getPuissance()));
      this.chkContientChambre.setSelected(this.veloCourant.getPneuAv().getContientChambre());
    }
    this.garage.notifierObservateurs();
  }

  public void setVeloCourant(Velo velo) {
    this.veloCourant = velo;
    update();
  }
}
