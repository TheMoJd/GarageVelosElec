package vues;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import modele.Velo;
import verification.VerifVelo;


/**
 * Un renderer personnalisé pour afficher les objets Velo dans une JList.
 * Elle affiche les informations de chaque vélo et indique si le vélo est
 * conforme ou non selon les critères de VerifVelo.
 */
class VeloCellRenderer extends JLabel implements ListCellRenderer<Velo> {
  /**
   * Retourne le composant utilisé pour dessiner une cellule de la liste.
   * Cette méthode est appelée pour chaque cellule de la liste contenant un objet Velo.
   *
   * @param list La JList que nous peignons.
   * @param velo L'objet Velo à afficher.
   * @param index L'indice de la cellule.
   * @param isSelected Indique si la cellule est sélectionnée.
   * @param cellHasFocus Indique si la cellule a le focus.
   * @return Le composant affichant le contenu de la cellule.
   */
  @Override
  public Component getListCellRendererComponent(JList<? extends Velo> list, Velo velo, int index, boolean isSelected, boolean cellHasFocus) {
    // Utilisez la méthode toString() de Velo pour obtenir la description de base
    String description = velo.toString();

    // Effectuer la vérification de conformité
    String resultatVerif = VerifVelo.verifVelo(velo);
    String statutConformite = resultatVerif.equals("Le vélo est correctement configuré.") ? "<span style='color:green;'>Conforme</span>" : "<span style='color:red;'>Non conforme</span>";

    // Construire la chaîne HTML finale
    String texte =  description + "<br/>Statut: " + statutConformite + "</html>";
    setText(texte);

    // Autres paramètres de rendu (pour la sélection, etc.)
    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setOpaque(true);
    } else {
      setBackground(list.getBackground());
      setOpaque(false);
    }
    setEnabled(list.isEnabled());
    setFont(list.getFont());
    return this;
  }
}
