package net.maguuma.magswag.gui.equipment;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.maguuma.magswag.common.constants.Slot;

@SuppressWarnings("serial")
public class EquipmentPanel extends JPanel {
  public EquipmentPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {

  }

  private void performLayout() {
	    this.setBorder(BorderFactory.createTitledBorder("Equipment"));
    this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    
    int i = 0;
    JPanel current = null;
    for (Slot slot : Slot.values()) {
    	if(current == null || i >= 6){
    		i = 0;
    		current= createNewColumn();
    		this.add(current);
    	}
    	current.add(new SlotPanel(slot));
    	i++;
    }
  }
  
  private JPanel createNewColumn(){
	  JPanel panel = new JPanel();
	  panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	  return panel;
  }
}
