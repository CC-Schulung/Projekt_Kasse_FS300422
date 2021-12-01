package project_kassenbon_hsin.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.ComboBoxUI;

import project_kassenbon.model.Artikel;
import project_kassenbon.model.Artikelliste;
import project_kassenbon.model.IBon;
import project_kassenbon.model.Kassenbon;
import project_kassenbon.model.KassenbonEintrag;
import project_kassenbon_hsin.model.sortiment.DefaultProducts;
import project_kassenbon_hsin.view.MainFrame;

public class SchirmerIBonController {

	private MainFrame mainFrame;
	private JComboBox addItemComboBox;
	private JComboBox deleteItemComboBox;
	private IBon<KassenbonEintrag, String, String[]> iBon;
	
	private final Action addNewItemAction = new AddNewItemAction();
	
	
	protected Artikelliste artikelliste = new Artikelliste();
	

	public SchirmerIBonController() {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mainFrame = new MainFrame();
					init();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void init() {
		
		
		iBon = new Kassenbon();
		
		DefaultProducts defaultProducts = new DefaultProducts();
		
		generateAssortment();

//		System.out.println(receipt);

		addItemComboBox = mainFrame.getComboBox_addItem();

		deleteItemComboBox = mainFrame.getComboBox_deleteItem();

		updateGUI();
		
		mainFrame.getBtnNewItem().setAction(addNewItemAction);
		
		mainFrame.getFormattedTextField_itemCount().addKeyListener(new KeyAdapter() {
			public void keyPressed (KeyEvent e) {
				if ((e.getKeyCode() == 10)) {
					addNewItemAction.actionPerformed(null);
				}
			}
		});

		mainFrame.getTextField_Artikelname().addKeyListener(new KeyAdapter() {
			public void keyPressed (KeyEvent e) {
				if ((e.getKeyCode() == 10)) {
					addNewItemAction.actionPerformed(null);
				}
			}
		});
		
		mainFrame.getFormattedTextField_Preis().addKeyListener(new KeyAdapter() {
			public void keyPressed (KeyEvent e) {
				if ((e.getKeyCode() == 10)) {
					addNewItemAction.actionPerformed(null);
				}
			}
		});
		
		

		mainFrame.getBtnDeleteItem().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KassenbonEintrag toDeleteReceiptItem = (KassenbonEintrag) deleteItemComboBox.getSelectedItem();
//				System.out.println(toDeleteReceiptItem);
				iBon.removeEintrag(toDeleteReceiptItem);
				updateGUI();
			}
		});

	

		mainFrame.getBtnSaveBon().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newVerkaeuferName = mainFrame.getTxtField_Verkaeufer().getText();
				iBon.setVerkaeufer(newVerkaeuferName);
				updateGUI();
			}
		});
		
		mainFrame.getBtnNewBon().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iBon.resetEintraege();
				updateGUI();
			}
		});
		
		addItemComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getTextField_Artikelname().setText(((Artikel) addItemComboBox.getSelectedItem()).getBezeichnung());
				mainFrame.getFormattedTextField_Preis().setValue((((Artikel) addItemComboBox.getSelectedItem()).getPreis()));
				//updateGUI();
				
			}
		});
		

	}
	
	// eigene Methoden
	
	private void generateAssortment() {
		artikelliste.addArtikel(1, "15 Fischstäbchen", 1.79);
		artikelliste.addArtikel(2,"Steaks", 3.99);
		artikelliste.addArtikel(3, "Naturell (1l)", 4.99);
		artikelliste.addArtikel(4,"Magnum Eiscreme", 2.99);
		artikelliste.addArtikel(new Artikel(123, "Banane", 1.99));
		artikelliste.addArtikel(new Artikel(456, "Kaffee", 4.99));
	}
	
	
	
	private void updateGUI() {
		addItemComboBox.setModel(new DefaultComboBoxModel(artikelliste.getListe().toArray()));
		deleteItemComboBox.setModel(new DefaultComboBoxModel(iBon.getEintraege().toArray()));
		mainFrame.getReceiptTextPane().setText(iBon.toString());
		mainFrame.getFormattedTextField_itemCount().setText("1");
	}

	private Artikel getProductByName(String name) {
		for (Artikel artikel : artikelliste.getListe()) {
			if (artikel.getBezeichnung().equalsIgnoreCase(name)) {
				return artikel;
			}
		}
		return null;
		
	} 
	
	// Action Klassen
	
	public class AddNewItemAction extends AbstractAction{
		public AddNewItemAction() {
			putValue(NAME, "Eintrag hinzufügen");
			putValue(SHORT_DESCRIPTION, "Fügt einen Eintrag dem Kassenbon hinzu, ggf. auch einem Artikel dem Sortiment");
		}

		public void actionPerformed(ActionEvent e) {
			if (mainFrame.getTextField_Artikelname().getText().equals("")) {
				System.out.println("Artikelbezeichnung darf nicht leer sein");
				return;
			}
			
			Artikel toAddArtikel = getProductByName(mainFrame.getTextField_Artikelname().getText());
			if (toAddArtikel == null) {
				NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
				Number preisNumber = 0;
				try {
					preisNumber = numberFormat.parse(mainFrame.getFormattedTextField_Preis().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					System.out.println("Exception catched, unable to parse number, no product added");
					e1.printStackTrace();
					return;
				}
				double preisDouble = preisNumber.doubleValue();
				int counter = 1;
				Artikel artikel;
				Iterator<Artikel> artikelIterator = artikelliste.getListe().iterator();
				
				while (artikelIterator.hasNext()) {
					artikel = (Artikel) artikelIterator.next();
					if (artikel.getNummer() == counter) {
						counter++;
						artikelIterator = artikelliste.getListe().iterator();
					}
				}
				
				
				
				artikelliste.addArtikel(counter, mainFrame.getTextField_Artikelname().getText(), preisDouble);
				toAddArtikel = getProductByName(mainFrame.getTextField_Artikelname().getText());
			}
			
			KassenbonEintrag toAddKassenbonEintrag = new KassenbonEintrag(toAddArtikel,
					Integer.parseInt(mainFrame.getFormattedTextField_itemCount().getText()));
			iBon.addEintrag(toAddKassenbonEintrag);
			updateGUI();
			
		}
	}

	
}
