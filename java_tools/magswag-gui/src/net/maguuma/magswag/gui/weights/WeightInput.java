package net.maguuma.magswag.gui.weights;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.maguuma.magswag.calculator.controller.WeightController;
import net.maguuma.magswag.common.constants.Stat;

@SuppressWarnings("serial")
public class WeightInput extends JPanel {
  private final Stat stat;

  private SpinnerNumberModel spinnerModel;

  public WeightInput(Stat stat) {
    this.stat = stat;
    initialize();
    performLayout();
  }

  private void initialize() {
    this.spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
    this.spinnerModel.addChangeListener(createChangeListener());
    updateWeightController();
  }

  private void performLayout() {
    this.add(new JLabel(this.stat.name()));
    this.add(new JSpinner(this.spinnerModel));
  }

  protected void updateWeightController() {
    WeightController.setValue(WeightInput.this.stat, (int) WeightInput.this.spinnerModel.getValue());
  }

  protected ChangeListener createChangeListener() {
    return new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        updateWeightController();
      }
    };
  };
}
