package sushigame.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import sushigame.model.*;


public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private String[] filters = { "Sort by Balance", "Sort by Amount Consumed", "Sort by Amount Spoiled" };
	@SuppressWarnings({"unchecked", "rawtypes" })
	private JComboBox filterOption = new JComboBox(filters);
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
	
		filterOption.addActionListener(this);
		
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		add(filterOption, BorderLayout.SOUTH);
		display.setText(makeScoreboardHTML());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		display.setText(makeScoreboardHTML(filterOption.getSelectedIndex()));
	}
	
	private String makeScoreboardHTML(int index) {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		switch(index) {
		case 0:
			Arrays.sort(chefs, new HighToLowBalanceComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
			}
			break;
		case 1:
			Arrays.sort(chefs, new HighToLowSoldComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " (" + Math.round(c.getConsumed()*100.0)/100.0 + " oz.) <br>";
			}
			break;
		case 2:
			Arrays.sort(chefs, new LowToHighSpoiledComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " (" + Math.round(c.getSpoiled()*100.0)/100.0 + " oz.) <br>";
			}
			break;
		}
		
		return sb_html;
	}


	
	public void refresh() {
		display.setText(makeScoreboardHTML(filterOption.getSelectedIndex()));	
	}
	
	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());

		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

}

