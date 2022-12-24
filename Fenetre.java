package calculatrice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	private JPanel container = new JPanel();
	private JLabel affiche = new JLabel("0");
	private PanCalc[] tab_but = new PanCalc[12];
	private JButton[] tab_op = new JButton[5];
	private double result =0;
	private double temp =0;
	private String text = "";
	private String op = "";
	private String optemp = "";
	private boolean change = false;
	private boolean priority = false;
	private boolean press = false;
	private boolean egal = false;
	private boolean onpriorit = false;
	public Fenetre () {
		this.setTitle("Calculatrice");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setResizable(false);
		container.setLayout(new BorderLayout());
		createBut();
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	public void createBut() {
		JPanel chiffres = new JPanel();
		GridLayout grille = new GridLayout(4,3);
		grille.setHgap(8);
		grille.setVgap(8);
		chiffres.setLayout(grille);
		
		JPanel operator = new JPanel();
		GridLayout g2 = new GridLayout(5,1);
		g2.setHgap(8);
		g2.setVgap(8);
		operator.setLayout(g2);
		JPanel ecran = new JPanel();
		ecran.setPreferredSize(new Dimension(450, 60));
		Font police = new Font("Arial", Font.BOLD, 20);
		affiche.setFont(police);
		affiche.setHorizontalAlignment(JLabel.RIGHT);
		affiche.setPreferredSize(new Dimension(450, 20));
		for (int i=0; i<17; i++) {
			switch (i) {
			case 9:
				tab_but[i] = new PanCalc(".");
				tab_but[i].setFont(police);
				tab_but[i].addActionListener(new ChiffreListener());
				chiffres.add(tab_but[i]);
				break;
			case 10:
				tab_but[i] = new PanCalc("0");
				tab_but[i].setFont(police);
				tab_but[i].addActionListener(new ChiffreListener());
				chiffres.add(tab_but[i]);
				break;
			case 11:
				tab_but[i] = new PanCalc("=");
				tab_but[i].setFont(police);
				tab_but[i].addActionListener(new EgalListener());
				chiffres.add(tab_but[i]);
				break;
			case 12:
				tab_op[i-12] = new JButton("C");
				tab_op[i-12].setForeground (Color.red);
				tab_op[i-12].addActionListener(new ClearListener());
				operator.add(tab_op[i-12]);
				break;
			case 13:
				tab_op[i-12] = new JButton("+");
				tab_op[i-12].addActionListener(new PlusListener());
				operator.add(tab_op[i-12]);
				break;
			case 14:
				tab_op[i-12] = new JButton("-");
				tab_op[i-12].addActionListener(new MoinsListener());
				operator.add(tab_op[i-12]);
				break;
			case 15:
				tab_op[i-12] = new JButton("x");
				tab_op[i-12].addActionListener(new FoisListener());
				operator.add(tab_op[i-12]);
				break;
			case 16:
				tab_op[i-12] = new JButton("/");
				tab_op[i-12].addActionListener(new DivListener());
				operator.add(tab_op[i-12]);
				break;
			default:
				tab_but[i] = new PanCalc(String.valueOf(i+1));
				tab_but[i].setFont(police);
				tab_but[i].addActionListener(new ChiffreListener());
				chiffres.add(tab_but[i]);
				break;
			}
		}
		ecran.add(affiche);
		ecran.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(ecran, BorderLayout.NORTH);
		container.add(chiffres, BorderLayout.CENTER);
		container.add(operator, BorderLayout.EAST);
		
	}
	public void operate() {
		if (!press) {
			press = true;
			if (priority && op != "x" && op != "/") {
				temp = result;
				optemp = op;
				result = Double.valueOf(affiche.getText());
				priority = false;
				
			} else if (op == "+") {
				result += Double.valueOf(affiche.getText());

//				System.out.println("In plus "+ result);
			}
			else if (op =="-") {
				result -= Double.valueOf(affiche.getText());
				
			}
			else if (op == "x") {
				result *= Double.valueOf(affiche.getText());
				
			}
			else if (op == "/") {
				result /= Double.valueOf(affiche.getText());
				
			} else {
				result += Double.valueOf(affiche.getText());

//				System.out.println("In else "+ result);
			}
			if (onpriorit) {
				if (optemp == "+") {
					result += temp;
				} else if (optemp == "-") {
					result =temp - result;
				}
				optemp ="";
				temp= 0;
				onpriorit= false;
				affiche.setText(String.valueOf(result));
			} else {
				if (optemp.isEmpty()) {
//					System.out.println("in empty "+ result);
					affiche.setText(String.valueOf(result));
				}
			}
		}
		
	}
	class ChiffreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			press = false;
			if (egal) {
				result = 0;
				op= "";
				egal =false;
			}
			text = ((JButton) e.getSource()).getText();
			if (!affiche.getText().equals("0") || (text == "." ))
			{
				if (change) {
					change = false;							
					if (text == ".")
						text="0.";
				}else {
					if (text == "." && affiche.getText().indexOf('.') != -1)
					{
						text = "";
					}
					text = affiche.getText()+ text;
				}
				
				
			}
			affiche.setText(text);
		}
		
	}
	class EgalListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			change = true ;
			press = false;
			onpriorit = true;
			egal = true;
			operate();
		}
		
	}
	class ClearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			affiche.setText("0");
			result = 0;
			press = false;
			
		}
		
	}
	class PlusListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			change = true ;
			onpriorit = true;
			egal =false;
			operate();
			op = "+";
			
				
		}
		
	}
	class MoinsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			change = true ;
			onpriorit = true;
			egal =false;
			operate();
			op = "-";
				
		}
		
	}
	class FoisListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			change = true ;
			priority = true;
			egal =false;
			operate();
			op = "x";
				
		}
		
	}
	class DivListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			change = true ;
			priority = true;
			egal =false;
			operate();
			op = "/";
				
		}
		
	}
}
