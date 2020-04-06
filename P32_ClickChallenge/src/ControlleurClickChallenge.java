import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ControlleurClickChallenge implements ActionListener {

	public enum Etat{CHOIX_TEMPS, ATTENTE_TEMPS, ATTENTE_CLICK, REJOUER}
	private Etat etat;
	public static float temps;
	private Thread thread;
	private int count;
	private VueClickChallenge vue;
	
	public ControlleurClickChallenge(VueClickChallenge vue) {
		this.etat = Etat.CHOIX_TEMPS;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String o = e.getActionCommand();
		
		switch (this.etat) {
		
		case CHOIX_TEMPS: //on choisit le temps

			if(o.equals("dixSec")) { //10
				VueClickChallenge.countdown.setText("00:10");
				temps=10;
			}
			if(o.equals("trenteSec")) { //30
				VueClickChallenge.countdown.setText("00:30");
				temps=30;
			}
			if(o.equals("soixanteSec")) { //60
				VueClickChallenge.countdown.setText("00:60");
				temps=60;
			}
			this.etat = Etat.ATTENTE_TEMPS; //on passe a l'etat du timer
			break;

		case ATTENTE_TEMPS:
			this.thread = new Thread(new Runnable() {
				@Override
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							Timer t = new Timer(1000, new ActionListener() {
							    float time = temps * 1000; //temps = 10 ou 30 ou 60

							    public void actionPerformed(ActionEvent e) {
							        if (time >= 0) {
							            float s = ((time / 1000) % 60); //conversion en secondes
							            DecimalFormat df = new DecimalFormat();
							            df.setMaximumFractionDigits(0);
							            VueClickChallenge.countdown.setText("00:"+ df.format(s));
										float cps = 0;
										cps = count / temps;
							            DecimalFormat df2 = new DecimalFormat();
							            df2.setMaximumFractionDigits(3);
										VueClickChallenge.cps.setText("Vous avez " +df2.format(cps)+ " cliques/seconde");
							            time -= 1000;
							        }
							        if(time == 0) { //affiche le resultat
							        	etat = Etat.REJOUER;
							        }
							    }
							});
							t.start();
							
						}
					});
				}
			});
			this.thread.start();
			if(this.thread.isAlive()) { //si le thread est toujours actif on passe a l'état du clique du bouton
				this.etat = Etat.ATTENTE_CLICK;
			}
			break;
			
		case ATTENTE_CLICK: //clique bouton
			this.count++;
			VueClickChallenge.click.setText("Nombre de cliques / seconde : " +this.count);
			break;
			
		case REJOUER: //affiche le resultat

			this.vue.activerBouton();
			
			//Si on veut rejouer
			if(o.equals("rejouer")) {
				this.vue.initialiser();
				this.count = 0;
				this.etat = Etat.CHOIX_TEMPS;
			}
			
		default:
			break;
		}
	}
	
	
}
