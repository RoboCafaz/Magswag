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

import net.maguuma.magswag.calculator.character.TraitModel;
import net.maguuma.magswag.calculator.character.listener.TraitChangeListener;
import net.maguuma.magswag.calculator.controller.CharacterController;
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
    CharacterController.getCharacter().getTraitModel().addTraitChangeListener(this);
  }

  private void initialize() {
    glue = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
    labelPrimary = new JLabel();
    labelPrimaryValue = new JLabel();
    labelSecondary = new JLabel();
    labelSecondaryValue = new JLabel();

    buttonIncrement = new JButton();
    buttonIncrement.addActionListener(createIncrementListener());
    buttonDecrement = new JButton();
    buttonDecrement.addActionListener(createDecrementListener());

    buttons = new JButton[6];
    labelValue = new JLabel();
    buttonTrait1 = new JButton();
    buttons[0] = buttonTrait1;
    buttonTrait2 = new JButton();
    buttons[1] = buttonTrait2;
    buttonTrait3 = new JButton();
    buttons[2] = buttonTrait3;
    buttonTrait4 = new JButton();
    buttons[3] = buttonTrait4;
    buttonTrait5 = new JButton();
    buttons[4] = buttonTrait5;
    buttonTrait6 = new JButton();
    buttons[5] = buttonTrait6;
  }

  private void performLayout() {
    GridBagConstraints gridBagConstraints;

    setBorder(BorderFactory.createTitledBorder(trait.getName()));

    setLayout(new GridBagLayout());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    add(glue, gridBagConstraints);

    labelPrimary.setHorizontalAlignment(SwingConstants.TRAILING);
    labelPrimary.setText(trait.getPrimary().name() + ": +");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    add(labelPrimary, gridBagConstraints);

    labelPrimaryValue.setText("0");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(labelPrimaryValue, gridBagConstraints);

    buttonIncrement.setText("+");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(buttonIncrement, gridBagConstraints);

    labelSecondary.setHorizontalAlignment(SwingConstants.TRAILING);
    labelSecondary.setText(trait.getSecondary().name() + ": +");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    add(labelSecondary, gridBagConstraints);

    labelSecondaryValue.setText("0");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(labelSecondaryValue, gridBagConstraints);

    buttonDecrement.setText("-");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    add(buttonDecrement, gridBagConstraints);

    Font f = labelValue.getFont();
    f = new Font(f.getFontName(), Font.BOLD, f.getSize() + 6);
    labelValue.setFont(f);
    labelValue.setHorizontalAlignment(SwingConstants.CENTER);
    labelValue.setText("0");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(labelValue, gridBagConstraints);

    buttonTrait1.setText("1");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(buttonTrait1, gridBagConstraints);

    buttonTrait2.setText("2");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    add(buttonTrait2, gridBagConstraints);

    buttonTrait3.setText("3");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(buttonTrait3, gridBagConstraints);

    buttonTrait4.setText("4");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    add(buttonTrait4, gridBagConstraints);

    buttonTrait5.setText("5");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    add(buttonTrait5, gridBagConstraints);

    buttonTrait6.setText("6");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    add(buttonTrait6, gridBagConstraints);
  }

  private ActionListener createIncrementListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CharacterController.getCharacter().getTraitModel().incrementTrait(index);
      }
    };
  }

  private ActionListener createDecrementListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CharacterController.getCharacter().getTraitModel().decrementTrait(index);
      }
    };
  }

  private void validateTraits() {
    for (int i = 0; i < CharacterController.getCharacter().getTraitModel().getTraits().length; i++) {
      traitChanged(i, CharacterController.getCharacter().getTraitModel().getTraits()[i]);
    }
  }

  @Override
  public void traitChanged(int traitLine, int newValue) {
    if (traitLine == index) {
      labelValue.setText(String.valueOf(newValue));
      labelPrimaryValue.setText(String.valueOf(newValue * 50));
      labelSecondaryValue.setText(String.valueOf(newValue * 50));

      for (int i = 0; i < buttons.length; i++) {
        buttons[i].setEnabled(newValue > i);
      }
    }
    buttonDecrement.setEnabled(!(CharacterController.getCharacter().getTraitModel().getTraits()[index] <= 0 || CharacterController.getCharacter().getTraitModel().pointsRemaining() >= TraitModel.MAX_TRAITS));
    buttonIncrement.setEnabled(!(CharacterController.getCharacter().getTraitModel().getTraits()[index] >= TraitModel.MAX_TRAIT_LINE || CharacterController.getCharacter().getTraitModel().pointsRemaining() <= 0));
  }
}
