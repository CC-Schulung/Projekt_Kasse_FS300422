package View;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;

public class JFrame extends javax.swing.JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String input1;
	private String input2;
	private String input3;
	private String input4;
	private JTextField txt_name;

	
	/**
	 * Create the frame.
	 */
	public JFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/View/trolly.jpg")));
		setResizable(false);
		setForeground(Color.WHITE);
		setTitle("Einkaufswagen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 491);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(43, 65, 311, 275);
		textArea.setEditable(false); // Textfeld nicht mit Maus/Tastatur veränderbar
		contentPane.add(textArea);
		textArea.setText(" \n");
		
		final JTextArea textArea2 = new JTextArea();
		textArea2.setBackground(new Color(255, 255, 255));
		textArea2.setBounds(43, 352, 311, 35);
		textArea2.setEditable(false); // Textfeld nicht mit Maus/Tastatur veränderbar
		contentPane.add(textArea2);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(SystemColor.inactiveCaption);
		btnAdd.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				input1 = JOptionPane.showInputDialog("Produkt eingeben");
				input2 = JOptionPane.showInputDialog("Menge eingeben");
				input3 = JOptionPane.showInputDialog("Preis eingeben");
				Ieinkaufen.liste.add(input1);
				Ieinkaufen.liste.add(input2);
				Ieinkaufen.liste.add(input3);
				Integer input2u=Integer.parseInt(input2);
				Integer input3u=Integer.parseInt(input3);
				Isumme.berechnen.add(input3u * input2u);
				textArea.append(input1 + " " + input2 +  " Stk \n für " + input3 + " Euro \n");
				
				System.out.println("Einkaufen Array" + Ieinkaufen.liste);
				System.out.println("Preis Array" + Isumme.berechnen);
				
				int sum=0;
				for(int i = 0; i < Isumme.berechnen.size(); i++)
				    sum += Isumme.berechnen.get(i);
				System.out.println("Summe des Warenkorbs" + sum);
				String sums = Integer.toString(sum);
				textArea2.setText("Summe des Warenkorbs: \n" + sums + " Euro");
				
					}
		});
		btnAdd.setBounds(375, 65, 150, 60);
		contentPane.add(btnAdd);
		
		/////___________
		
		JButton btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				System.out.println(Ieinkaufen.liste.contains("Tomate"));
				System.out.println(Ieinkaufen.liste.indexOf("Tomate"));
				input4 = JOptionPane.showInputDialog("Produkt eingeben");
				System.out.println(Ieinkaufen.liste.size());
				
				
				int index=Ieinkaufen.liste.indexOf(input4);
				
				Ieinkaufen.liste.remove(index);				
				Ieinkaufen.liste.remove(index);
				Ieinkaufen.liste.remove(index);
				Isumme.berechnen.remove(index/3); // Der Gesamtpreis von dem Artikel muss auch aus dem Array raus
												 // Geteilt durch 3 um auf den richtigen summe Array Index zu kommen
							
				System.out.println("Nach dem Löschen" + Ieinkaufen.liste);
				
				int sum=0;
				for(int i = 0; i < Isumme.berechnen.size(); i++) // hier wird jetzt einfach das Array durchgerechnet
				    sum += Isumme.berechnen.get(i);
				System.out.println("Summe des Warenkorbs" + sum);
				String sums = Integer.toString(sum); 
				textArea.setText(" ");
				try {
				
				for (int j = 0; j < Ieinkaufen.liste.size(); j+=3) {
//					textArea.append(einkaufen.liste.get(i));
					textArea.append(Ieinkaufen.liste.get(j) + " " + Ieinkaufen.liste.get(j + 1) +  " Stk \n für " + Ieinkaufen.liste.get(j + 2) + " Euro \n");
				}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				textArea2.setText("Summe des Warenkorbs: \n" + sums + " Euro");
			}
		});
				
		btnDel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnDel.setBounds(375, 166, 150, 60);
		contentPane.add(btnDel);
		
		txt_name = new JTextField();
		txt_name.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		txt_name.setText("Name des Kundenbetreuers eingeben");
		txt_name.setBounds(39, 10, 300, 29);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				int sum=0;
				for(int i = 0; i < Isumme.berechnen.size(); i++) // hier wird jetzt einfach das Array durchgerechnet
				    sum += Isumme.berechnen.get(i);
				System.out.println("Summe des Warenkorbs" + sum);
				String sums = Integer.toString(sum); 
				textArea.setText(" ");
				try {
				
				for (int j = 0; j < Ieinkaufen.liste.size(); j+=3) {
//					textArea.append(einkaufen.liste.get(i));
					textArea.append(Ieinkaufen.liste.get(j) + " " + Ieinkaufen.liste.get(j + 1) +  " Stk \n für " + Ieinkaufen.liste.get(j + 2) + " Euro \n");
				}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				textArea2.setText("Summe des Warenkorbs: \n" + sums + " Euro");
			
				
				String input_name=txt_name.getText();
				
				String produkte=" ";
//				String preis=" ";


				for (int i = 0; i < Ieinkaufen.liste.size(); i+=3) {
					produkte+= Ieinkaufen.liste.get(i) + "für je" + Ieinkaufen.liste.get(i+2) + "Euro  \n";
				}
				
				

				JOptionPane.showMessageDialog(null, produkte   + " \n \n Anzahl der Produkte: " + (Isumme.berechnen.size()) + "\n\n Summe: " + sums + "Euro" + " \n\n Es bediente Sie:" + input_name, "Ihr Kassenbon", JOptionPane.INFORMATION_MESSAGE);
				
//				System.out.println("Anzahl der Produkte: " + einkaufen.liste.size() +"\n Kundenbetreuer: " + input_name);;
			}
		});
		btnFertig.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnFertig.setBounds(438, 400, 115, 44);
		contentPane.add(btnFertig);
			
		
		
			}
		
	}

