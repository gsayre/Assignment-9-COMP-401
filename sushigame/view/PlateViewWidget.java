package sushigame.view;

import java.awt.event.*;
import javax.swing.*;
import comp401.sushi.*;
import sushigame.model.*;

public class PlateViewWidget implements ActionListener, BeltObserver {
	
	private Belt b;
	private BeltView beltView;
	private int index;
	private JButton button;
	
	public PlateViewWidget(Belt b, BeltView beltView, int index) {
		this.b = b;
		this.beltView = beltView;
		b.registerBeltObserver(this);
		this.index = index;
		button = new JButton("");
		button.addActionListener(this);
		beltView.add(button);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (b.getPlateAtPosition(index) == null) {
			// If null does nothing
		} else {
			String messageDialog = "The color of the plate is: " + b.getPlateAtPosition(index).getColor().toString() + ".\n";
			messageDialog += "The type of sushi is: " + b.getPlateAtPosition(index).getContents().getName() + ".\n";
			messageDialog += "The name of the chef is: " + b.getPlateAtPosition(index).getChef().getName() + ".\n";
			if (b.getPlateAtPosition(index).getContents() instanceof Roll) {
				messageDialog += "Ingredients: " + ".\n";
				for (int i = 0; i < b.getPlateAtPosition(index).getContents().getIngredients().length; i++) {
					IngredientPortion[] ings = b.getPlateAtPosition(index).getContents().getIngredients();
					messageDialog += ings[i].getName() + ": " + ings[i].getAmount() + ".\n";
				}
			}
			messageDialog += "The age of the plate is: " + b.getAgeOfPlateAtPosition(index);
			JOptionPane.showMessageDialog(null, messageDialog);
		}
	}

	@Override
	public void handleBeltEvent(BeltEvent e) { }
	
	public JButton getButton() {
		return button;
	}

		
}
