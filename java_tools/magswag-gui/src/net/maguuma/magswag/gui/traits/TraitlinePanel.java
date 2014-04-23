package net.maguuma.magswag.gui.traits;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import net.maguuma.magswag.calculator.controller.TraitController;
import net.maguuma.magswag.calculator.controller.listener.TraitChangeListener;
import net.maguuma.magswag.common.datatypes.professions.Trait;

@SuppressWarnings("serial")
public class TraitlinePanel extends JPanel implements TraitChangeListener {
  private final int index;
  private final Trait trait;

  private JButton buttonDecrement;
  private JButton buttonIncrement;
  private JButton buttonTrait1;
  private JButton buttonTrait2;
  private JButton buttonTrait3;
  private JButton buttonTrait4;
  private JButton buttonTrait5;
  private JButton buttonTrait6;
  private Box.Filler glue;
  private JLabel labelPrimary;
  private JLabel labelPrimaryValue;
  private JLabel labelSecondaryValue;
  private JLabel labelSecondary;
  private JLabel labelValue;

  private JButton[] buttons;

  public TraitlinePanel(int index, Trait trait) {
    this.index = index;
    this.trait = trait;
    initialize();
    performLayout();
    validateTraits();
    TraitController.addTraitChangeListener(this);
  }

  private void initialize() {
    this.glue = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
    this.labelPrimary = new JLabel();
    this.labelPrimaryValue = new JLabel();
    this.labelSecondary = new JLabel();
    this.labelSecondaryValue = new JLabel();

    this.buttonIncrement = new JButton();
    this.buttonIncrement.addActionListener(createIncrementListener());
    this.buttonDecrement = new JButton();
    this.buttonDecrement.addActionListener(createDecrementListener());

    this.buttons = new JButton[6];
    this.labelValue = new JLabel();
    this.buttonTrait1 = new JButton();
    this.buttons[0] = this.buttonTrait1;
    this.buttonTrait2 = new JButton();
    this.buttons[1] = this.buttonTrait2;
    this.buttonTrait3 = new JButton();
    this.buttons[2] = this.buttonTrait3;
    this.buttonTrait4 = new JButton();
    this.buttons[3] = this.buttonTrait4;
    this.buttonTrait5 = new JButton();
    this.buttons[4] = this.buttonTrait5;
    this.buttonTrait6 = new JButton();
    this.buttons[5] = this.buttonTrait6;
  }

  private void performLayout() {
    GridBagConstraints gridBagConstraints;

    this.setBorder(BorderFactory.createTitledBorder(this.trait.getName()));

    setLayout(new GridBagLayout());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    add(this.glue, gridBagConstraints);

    this.labelPrimary.setHorizontalAlignment(SwingConstants.TRAILING);
    this.labelPrimary.setText(this.trait.getPrimary().name() + ": +");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    add(this.labelPrimary, gridBagConstraints);

    this.labelPrimaryValue.setText("0");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(this.labelPrimaryValue, gridBagConstraints);

    this.buttonIncrement.setText("+");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(this.buttonIncrement, gridBagConstraints);

    this.labelSecondary.setHorizontalAlignment(SwingConstants.TRAILING);
    this.labelSecondary.setText(this.trait.getSecondary().name() + ": +");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    add(this.labelSecondary, gridBagConstraints);

    this.labelSecondaryValue.setText("0");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(this.labelSecondaryValue, gridBagConstraints);

    this.buttonDecrement.setText("-");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(this.buttonDecrement, gridBagConstraints);

    Font f = this.labelValue.getFont();
    f = new Font(f.getFontName(), Font.BOLD, f.getSize() + 6);
    this.labelValue.setFont(f);
    this.labelValue.setHorizontalAlignment(SwingConstants.CENTER);
    this.labelValue.setText("0");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(this.labelValue, gridBagConstraints);

    this.buttonTrait1.setText("1");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(this.buttonTrait1, gridBagConstraints);

    this.buttonTrait2.setText("2");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    add(this.buttonTrait2, gridBagConstraints);

    this.buttonTrait3.setText("3");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(this.buttonTrait3, gridBagConstraints);

    this.buttonTrait4.setText("4");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    add(this.buttonTrait4, gridBagConstraints);

    this.buttonTrait5.setText("5");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(this.buttonTrait5, gridBagConstraints);

    this.buttonTrait6.setText("6");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    add(this.buttonTrait6, gridBagConstraints);
  }

  private ActionListener createIncrementListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TraitController.incrementTrait(TraitlinePanel.this.index);
      }
    };
  }

  private ActionListener createDecrementListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TraitController.decrementTrait(TraitlinePanel.this.index);
      }
    };
  }

  private void validateTraits() {
    for (int i = 0; i < TraitController.getTraits().length; i++) {
      traitChanged(i, TraitController.getTraits()[i]);
    }
  }

  @Override
  public void traitChanged(int traitLine, int newValue) {
    if (traitLine == this.index) {
      this.labelValue.setText(String.valueOf(newValue));
      this.labelPrimaryValue.setText(String.valueOf(newValue * 50));
      this.labelSecondaryValue.setText(String.valueOf(newValue * 50));

      for (int i = 0; i < this.buttons.length; i++) {
        this.buttons[i].setEnabled(newValue > i);
      }
    }
    this.buttonDecrement
        .setEnabled(!(TraitController.getTraits()[this.index] <= 0 || TraitController.pointsRemaining() >= TraitController.MAX_TRAITS));
    this.buttonIncrement
        .setEnabled(!(TraitController.getTraits()[this.index] >= TraitController.MAX_TRAIT_LINE || TraitController
            .pointsRemaining() <= 0));
  }
}
