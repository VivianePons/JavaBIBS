import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ExempleSwing {
	
	
	public static void main(String[] args) 
    { 
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Première fenêtre en java");
		fenetre.setBounds(0,0,300,100); 
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel helloWorld = new JLabel("Hello World !");
        JButton bouton = new JButton("Je suis un bouton");
        panel.add(helloWorld);
        panel.add(bouton);
        fenetre.add(panel);
        fenetre.setVisible(true);
		
    } 

}
