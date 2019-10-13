package sushigame.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401.sushi.Avocado;
import comp401.sushi.AvocadoPortion;
import comp401.sushi.Crab;
import comp401.sushi.CrabPortion;
import comp401.sushi.Eel;
import comp401.sushi.EelPortion;
import comp401.sushi.Ingredient;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate;
import comp401.sushi.RedPlate;
import comp401.sushi.Rice;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.Salmon;
import comp401.sushi.SalmonPortion;
import comp401.sushi.Sashimi;
import comp401.sushi.Sashimi.SashimiType;
import comp401.sushi.Seaweed;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.Shrimp;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.Sushi;
import comp401.sushi.Tuna;
import comp401.sushi.TunaPortion;

public class PlayerChefView extends JPanel implements ActionListener, ChangeListener {

	private List<ChefViewListener> listeners;
	private int belt_size;
	
	private String[] sushType = { "Nigiri", "Sashimi", "Roll" };
	@SuppressWarnings({"unchecked", "rawtypes" })
	private JComboBox rollOption = new JComboBox(sushType);
	private String selectedSushi;
	
	private JLabel rollName;
	private JTextField insertName;
	private String storedName;
	private JPanel ingredientLayout = new JPanel();
	private JSlider ingredientSlider;
	private JSlider[] sliders = new JSlider[8];
	private JLabel ingredientName;
	private Ingredient[] ingredients = { new Avocado(), new Crab(), new Eel(), new Rice(), new Salmon(), new Shrimp(), new Seaweed(), new Tuna()};
	private JLabel ingredientAmount;
	private JLabel[] ingredientAmountArray = new JLabel[8];
	
	private String[] meatType = { "Crab", "Eel", "Salmon", "Shrimp", "Tuna" };
	@SuppressWarnings({"unchecked", "rawtypes" })
	private JComboBox meatOption = new JComboBox(meatType);
	private String selectedMeat;
	
	private String[] plateType = { "Red", "Green", "Blue", "Gold"};
	@SuppressWarnings({"unchecked", "rawtypes" })
	private JComboBox plateOption = new JComboBox(plateType);
	private String selectedPlate;
	
	private Integer[] positionType = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 ,20};
	@SuppressWarnings({"unchecked", "rawtypes" })
	private JComboBox positionOption = new JComboBox(positionType);
	private int selectedPosition;

	private JPanel goldPlateLayout = new JPanel();
	private JSlider goldSlider;
	private JLabel goldCost;
	private JLabel goldAmount;
	
	private JButton next = new JButton("NEXT");
	private JButton next2 = new JButton("NEXT");
	private JButton next3 = new JButton("NEXT");
	private JButton next4 = new JButton("NEXT");
	private JButton next5 = new JButton("NEXT");
	private JButton next6 = new JButton("NEXT");
	private JButton makePlate= new JButton("MAKE PLATE");
	
	private int ingCount = 0;
	private IngredientPortion[] sushIngs;
	private double goldPlateCost;
	
	private Nigiri createdNigiri;
	private Sashimi createdSashimi;
	private Roll createdRoll;
	

	
	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(rollOption);
		next.addActionListener(this);
		next.setActionCommand("NEXT");
		add(next);
		
		ingredientLayout.setLayout(new GridLayout(8, 3));
			for (int i = 0; i < 8; i++) {
				ingredientSlider = new JSlider(0, 15);
				ingredientSlider.addChangeListener(this);
				sliders[i] = ingredientSlider;
				ingredientLayout.add(ingredientSlider);
				ingredientName = new JLabel(ingredients[i].getName());
				ingredientLayout.add(ingredientName);
				ingredientAmount = new JLabel("0 oz");
				ingredientAmountArray[i] = ingredientAmount;
				ingredientLayout.add(ingredientAmount);				
			}
			rollName = new JLabel();
			rollName.setVisible(false);
			rollName.setText("Name of the Roll: ");
			add(rollName);
			insertName = new JTextField();
			insertName.setVisible(false);
			insertName.setMaximumSize(new Dimension(600, 50));
			insertName.setPreferredSize(new Dimension(600, 50));
			add(insertName);
			ingredientLayout.setVisible(false);
			add(ingredientLayout);
			next2.setVisible(false);
			next2.addActionListener(this);
			next2.setActionCommand("NEXT2");
			add(next2);
			
			
			meatOption.setVisible(false);
			add(meatOption);
			next3.setVisible(false);
			next3.addActionListener(this);
			next3.setActionCommand("NEXT3");
			add(next3);
			
			plateOption.setVisible(false);
			add(plateOption);
			next4.setVisible(false);
			next4.addActionListener(this);
			next4.setActionCommand("NEXT4");
			add(next4);
			
			goldPlateLayout.setLayout(new GridLayout(1, 3));
			goldSlider = new JSlider(5, 10);
			goldSlider.addChangeListener(this);
			goldPlateLayout.add(goldSlider);
			goldCost = new JLabel("Gold Plate Cost: ");
			goldPlateLayout.add(goldCost);
			goldAmount = new JLabel("");
			goldPlateLayout.add(goldAmount);
			goldPlateLayout.setVisible(false);
			add(goldPlateLayout);
			next5.setVisible(false);
			next5.addActionListener(this);
			next5.setActionCommand("NEXT5");
			add(next5);
			
			positionOption.setVisible(false);
			add(positionOption);
			next6.setVisible(false);
			next6.addActionListener(this);
			next6.setActionCommand("NEXT6");
			add(next6);
			
			makePlate.setVisible(false);
			makePlate.addActionListener(this);
			makePlate.setActionCommand("MAKE PLATE");
			add(makePlate);
			
			
			
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "NEXT":
			selectedSushi = (String) rollOption.getSelectedItem();
				if (selectedSushi.equals("Roll")) {
					rollVisibility();
				} else {
					rollOption.setVisible(false);
					next.setVisible(false);
					meatOption.setVisible(true);
					next3.setVisible(true);
				}
			break;
			
		case "NEXT2":
			storedName = insertName.getText();
			
			double sliderValue; 
			for (int i = 0; i < 8; i++) {
				if (sliders[i].getValue() > 0) {
					
					ingCount++;
				}	
			}

			sushIngs = new IngredientPortion[ingCount];
			int sushIngsCount = 0;
			for (int i = 0; i < 8; i++) {
				sliderValue = sliders[i].getValue();
				if (sliders[i].getValue() > 0) {
						switch(i) {
						case 0:
							sushIngs[sushIngsCount] = new AvocadoPortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 1:
							sushIngs[sushIngsCount] = new CrabPortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 2:
							sushIngs[sushIngsCount] = new EelPortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 3:
							sushIngs[sushIngsCount] = new RicePortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 4:
							sushIngs[sushIngsCount] = new SalmonPortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 5:
							sushIngs[sushIngsCount] = new ShrimpPortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 6:
							sushIngs[sushIngsCount] = new SeaweedPortion(sliderValue / 10);
							sushIngsCount++;
							break;
						case 7:
							sushIngs[sushIngsCount] = new TunaPortion(sliderValue / 10);
							sushIngsCount++;
							break;
					}
				}
			}
			rollName.setVisible(false);
			insertName.setVisible(false);
			ingredientLayout.setVisible(false);
			next2.setVisible(false);
			plateOption.setVisible(true);
			next4.setVisible(true);
			break;

		case "NEXT3":
			selectedMeat = (String) meatOption.getSelectedItem();
//			if (selectedMeat.equals("Crab")) {
//				meatOption.setVisible(false);
//				next3.setVisible(false);
//				plateOption.setVisible(true);
//				next4.setVisible(true);
//			}
//			if (selectedMeat.equals("Eel")) {
//				meatOption.setVisible(false);
//				next3.setVisible(false);
//				plateOption.setVisible(true);
//				next4.setVisible(true);
//			}
//			if (selectedMeat.equals("Salmon")) {
//				meatOption.setVisible(false);
//				next3.setVisible(false);
//				plateOption.setVisible(true);
//				next4.setVisible(true);
//			}
//			if (selectedMeat.equals("Shrimp")) {
//				meatOption.setVisible(false);
//				next3.setVisible(false);
//				plateOption.setVisible(true);
//				next4.setVisible(true);
//			}
//			if (selectedMeat.equals("Tuna")) {
//				meatOption.setVisible(false);
//				next3.setVisible(false);
//				plateOption.setVisible(true);
//				next4.setVisible(true);
//			}
			meatOption.setVisible(false);
			next3.setVisible(false);
			plateOption.setVisible(true);
			next4.setVisible(true);
			break;
			
		case "NEXT4":
			selectedPlate = (String) plateOption.getSelectedItem();
			if (selectedPlate.equals("Red")) {
				plateOption.setVisible(false);
				next4.setVisible(false);
				positionOption.setVisible(true);
				next6.setVisible(true);
			} 
			if (selectedPlate.equals("Green")) {
				plateOption.setVisible(false);
				next4.setVisible(false);
				positionOption.setVisible(true);
				next6.setVisible(true);
			} 
			if (selectedPlate.equals("Blue")) {
				plateOption.setVisible(false);
				next4.setVisible(false);
				positionOption.setVisible(true);
				next6.setVisible(true);
			} 
			if (selectedPlate.equals("Gold")) {
				plateOption.setVisible(false);
				next4.setVisible(false);
				goldPlateLayout.setVisible(true);
				next5.setVisible(true);
			} 
			break;
			
		case "NEXT5":
			goldPlateLayout.setVisible(false);
			next5.setVisible(false);
			positionOption.setVisible(true);
			next6.setVisible(true);
			break;
			
		case "NEXT6": 
			selectedPosition = (int) positionOption.getSelectedItem();
			if (selectedPosition == 1) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 2) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 3) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 4) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 5) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 6) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 7) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 8) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 9) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 10) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 11) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 12) {
				positionOption.setVisible(false);
				next6.setVisible(false);
			} 
			if (selectedPosition == 13) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 14) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 15) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 16) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 17) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 18) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 19) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			if (selectedPosition == 20) {
				positionOption.setVisible(false);
				next6.setVisible(false);
				makePlate.setVisible(true);
			} 
			break;

		case "MAKE PLATE": 
			if (selectedPlate.equals("Red")) {
				makePlate.setVisible(false);
				if (rollOption.getSelectedItem().equals("Roll")) {
					createdRoll = new Roll(storedName, sushIngs);
					makeRedPlateRequest(createdRoll, selectedPosition - 1);
				}

				if (rollOption.getSelectedItem().equals("Sashimi")) {
					makeRedPlateRequest(createSashimi(), selectedPosition - 1);
				}	

				if (rollOption.getSelectedItem().equals("Nigiri")) {
					makeRedPlateRequest(createNigiri(), selectedPosition - 1);
				}	
			}

			if (selectedPlate.equals("Green")) {
				makePlate.setVisible(false);
				if (rollOption.getSelectedItem().equals("Roll")) {
					createdRoll = new Roll(storedName, sushIngs);
					makeGreenPlateRequest(createdRoll, selectedPosition - 1);
				}

				if (rollOption.getSelectedItem().equals("Sashimi")) {
					makeGreenPlateRequest(createSashimi(), selectedPosition - 1);
				}	

				if (rollOption.getSelectedItem().equals("Nigiri")) {
					makeGreenPlateRequest(createNigiri(), selectedPosition - 1);
				}	
			}

			if (selectedPlate.equals("Blue")) {
				makePlate.setVisible(false);
				if (rollOption.getSelectedItem().equals("Roll")) {
					createdRoll = new Roll(storedName, sushIngs);
					makeBluePlateRequest(createdRoll, selectedPosition - 1);
				}

				if (rollOption.getSelectedItem().equals("Sashimi")) {
					makeBluePlateRequest(createSashimi(), selectedPosition - 1);
				}	

				if (rollOption.getSelectedItem().equals("Nigiri")) {
					makeBluePlateRequest(createNigiri(), selectedPosition - 1);
				}	
			}

			if (selectedPlate.equals("Gold")) {
				makePlate.setVisible(false);
				double goldValue = goldSlider.getValue();
				goldValue = goldValue / 1.00;
				if (rollOption.getSelectedItem().equals("Roll")) {
					createdRoll = new Roll(storedName, sushIngs);
					makeGoldPlateRequest(createdRoll, selectedPosition - 1, goldValue);
				}

				if (rollOption.getSelectedItem().equals("Sashimi")) {
					makeGoldPlateRequest(createSashimi(), selectedPosition - 1, goldValue);
				}	

				if (rollOption.getSelectedItem().equals("Nigiri")) {
					makeGoldPlateRequest(createNigiri(), selectedPosition - 1, goldValue);
				}	
			}	
			rollOption.setVisible(true);
			next.setVisible(true);
		break;
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		double sliderValue;
		double goldValue;
		for (int i = 0; i < 8; i++) {
			sliderValue = sliders[i].getValue();
			ingredientAmountArray[i].setText(Double.toString(sliderValue / 10) + " oz.");
			goldValue = goldSlider.getValue();
			goldAmount.setText("$ " + Double.toString(goldValue / 1.00));
		}
		
		
	}

	private void rollVisibility() {
		rollOption.setVisible(false);
		next.setVisible(false);
		rollName.setVisible(true);
		insertName.setVisible(true);
		ingredientLayout.setVisible(true);
		next2.setVisible(true);
	}
	
	private Nigiri createNigiri() {
		
		if (selectedMeat.equals("Crab")) {
			createdNigiri = new Nigiri(NigiriType.CRAB);
		}
		if (selectedMeat.equals("Eel")) {
			createdNigiri = new Nigiri(NigiriType.EEL);
		}
		if (selectedMeat.equals("Salmon")) {
			createdNigiri = new Nigiri(NigiriType.SALMON);
		}
		if (selectedMeat.equals("Shrimp")) {
			createdNigiri = new Nigiri(NigiriType.SHRIMP);
		}
		if (selectedMeat.equals("Tuna")) {
			createdNigiri = new Nigiri(NigiriType.TUNA);
		}
		return createdNigiri;
	}
	
	private Sashimi createSashimi() {
		if (selectedMeat.equals("Crab")) {
			createdSashimi = new Sashimi(SashimiType.CRAB);
		}
		if (selectedMeat.equals("Eel")) {
			createdSashimi = new Sashimi(SashimiType.EEL);
		}
		if (selectedMeat.equals("Salmon")) {
			createdSashimi = new Sashimi(SashimiType.SALMON);
		}
		if (selectedMeat.equals("Shrimp")) {
			createdSashimi = new Sashimi(SashimiType.SHRIMP);
		}
		if (selectedMeat.equals("Tuna")) {
			createdSashimi = new Sashimi(SashimiType.TUNA);
		}
		return createdSashimi;
	}
	
	}
