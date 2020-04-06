import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class VueClickChallenge extends JPanel {
	
	private static final long serialVersionUID = -5596290658746833282L;
	
	private JRadioButton dix, trente, soixante;
	static JLabel countdown, click, cps;
	static JButton buttonClick, replay;
	static ButtonGroup bgGroup;

	public VueClickChallenge() {
		
		ControlleurClickChallenge controleur = new ControlleurClickChallenge(this);
		
		// Ensemble
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
		
		// Partie NORTH
		JPanel p_north = new JPanel();
		p_north.setLayout(new FlowLayout());
		
		// interieur partie NORTH
		JLabel time = new JLabel("Temps :");
		
		bgGroup = new ButtonGroup();
		
		dix = new JRadioButton("10s");
		dix.addActionListener(controleur);
		dix.setActionCommand("dixSec");
		
		trente = new JRadioButton("30s");
		trente.addActionListener(controleur);
		trente.setActionCommand("trenteSec");
		
		soixante = new JRadioButton("60s");
		soixante.addActionListener(controleur);
		soixante.setActionCommand("soixanteSec");
		
		countdown = new JLabel("00:00");
		
		countdown.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		
		bgGroup.add(dix);
		bgGroup.add(trente);
		bgGroup.add(soixante);
		
		p_north.add(time);
		p_north.add(dix);
		p_north.add(trente);
		p_north.add(soixante);
		p_north.add(countdown);
		
		this.add(p_north, BorderLayout.NORTH);
		
		// Partie CENTER
		JPanel p_center = new JPanel();
		this.add(p_center, BorderLayout.CENTER);
		p_center.setLayout(new GridLayout(0,1));
		//interieur partie CENTER
		click = new JLabel("Nombre de cliques / seconde : 0");
		click.setHorizontalAlignment(SwingConstants.CENTER);
		buttonClick = new JButton("Cliquez moi");
		buttonClick.addActionListener(controleur);
		p_center.add(click);
		p_center.add(buttonClick);
		
		//Partie SOUTH
		JPanel p_south = new JPanel();
		p_north.setLayout(new FlowLayout());
		//interieur partie SOUTH
		cps = new JLabel("Vous avez : 0 clique/seconde ");
		p_south.add(cps);
		replay = new JButton("Rejouer ?");
		replay.addActionListener(controleur);
		replay.setEnabled(false);
		replay.setActionCommand("rejouer");
		p_south.add(replay);
		p_south.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		this.add(p_south, BorderLayout.SOUTH);
		
	}
	
	public void activerBouton() {
		replay.setEnabled(true);
	}
	
	public void initialiser() {
		countdown.setText("00:00");
		click.setText("Vous avez : 0 clique/seconde ");
		cps.setText("Vous avez : 0 clique/seconde ");
		bgGroup.clearSelection();
	}
	
}
