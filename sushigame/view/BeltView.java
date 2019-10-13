package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private PlateViewWidget[] plateObjects;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		plateObjects = new PlateViewWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			PlateViewWidget plateWidget = new PlateViewWidget(belt, this, i);
			plateWidget.getButton().setMinimumSize(new Dimension(300, 20));
			plateWidget.getButton().setPreferredSize(new Dimension(300, 20));
			plateWidget.getButton().setOpaque(true);
			plateWidget.getButton().setBackground(Color.GRAY);
			plateObjects[i] = plateWidget;
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}


	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			PlateViewWidget plateWidget = plateObjects[i];

			if (p == null) {
				plateWidget.getButton().setText("");
				plateWidget.getButton().setBackground(Color.GRAY);
			} else {
				plateWidget.getButton().setText(p.toString());
				switch (p.getColor()) {
				case RED:
					plateWidget.getButton().setForeground(Color.RED); 
					plateWidget.getButton().setText(belt.getPlateAtPosition(i).getContents().getName());
					break;
				case GREEN:
					plateWidget.getButton().setForeground(Color.GREEN); 
					plateWidget.getButton().setText(belt.getPlateAtPosition(i).getContents().getName());
					break;
				case BLUE:
					plateWidget.getButton().setForeground(Color.BLUE); 
					plateWidget.getButton().setText(belt.getPlateAtPosition(i).getContents().getName());
					break;
				case GOLD:
					plateWidget.getButton().setForeground(Color.YELLOW); 
					plateWidget.getButton().setText(belt.getPlateAtPosition(i).getContents().getName());
					break;
				}
			}
		}
	}
}
