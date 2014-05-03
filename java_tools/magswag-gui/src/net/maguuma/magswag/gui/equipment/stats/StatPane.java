package net.maguuma.magswag.gui.equipment.stats;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.calculator.controller.listener.CharacterChangeListener;
import net.maguuma.magswag.common.constants.Stat;

@SuppressWarnings("serial")
public class StatPane extends JPanel implements CharacterChangeListener {
	private final Stat stat;
	private JLabel label;
	private JTextField value;
	private JTextField weight;
	private JTextField overage;
	
	public StatPane(Stat stat){
		this.stat = stat;
		initialize();
		performLayout();
		refresh();
		CharacterController.addCharacterChangeListener(this);
	}
	
	private void initialize(){
		label = new JLabel(stat.name());
		value = new JTextField();
		value.setEditable(false);
		overage = new JTextField();
		overage.setEditable(false);
		weight = new JTextField();
		weight.setEditable(false);
	}
	
	private void performLayout(){
		this.add(label);
		this.add(value);
		this.add(overage);
		this.add(weight);
	}
	
	private void refresh(){
		int value = CharacterController.getStats().get(this.stat);
		this.value.setText(String.valueOf(value));
	}

	@Override
	public void characterStatsChanged() {
		refresh();
	}
}
