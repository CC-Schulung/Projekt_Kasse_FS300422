package project_kassenbon_hsin.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import project_kassenbon.model.Artikel;
import project_kassenbon.model.IBon;
import project_kassenbon.model.Kassenbon;
import project_kassenbon.model.KassenbonEintrag;
import project_kassenbon.model.sortiment.Produkt;
import project_kassenbon.model.sortiment.Sortiment;
import project_kassenbon_hsin.model.sortiment.DefaultProducts;
import project_kassenbon_hsin.view.MainFrame;

public class SchirmerIBonController_V2 {

	private MainFrame mainFrame;
	private JComboBox<Object> addItemComboBox;
	private JComboBox<Object> deleteItemComboBox;
	private IBon<KassenbonEintrag, String, String[]> iBon;
	private Sortiment sortiment;
	
	private final Action addNewItemAction = new AddNewItemAction();

	public SchirmerIBonController_V2() {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
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
		
		DefaultProducts dp = new DefaultProducts();
		sortiment = new Sortiment(dp);
		
		
		iBon = new Kassenbon();
		
		iBon.addEintrag(new KassenbonEintrag(sortiment.getProduktByID(9), 15));
		iBon.addEintrag(new KassenbonEintrag(sortiment.getProduktByID(1), 3));
		iBon.addEintrag(new KassenbonEintrag(sortiment.getProduktByID(2), 5));
		iBon.addEintrag(new KassenbonEintrag(sortiment.getProduktByID(3), 6));
		iBon.addEintrag(new KassenbonEintrag(sortiment.getProduktByID(4), 2));

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
				mainFrame.getTextField_Artikelname().setText(((Produkt) addItemComboBox.getSelectedItem()).getBezeichnung());
				mainFrame.getFormattedTextField_Preis().setValue((((Produkt) addItemComboBox.getSelectedItem()).getPreis()));
				//updateGUI();
				
			}
		});
		

	}
	
	
	
	private void updateGUI() {
		addItemComboBox.setModel(new DefaultComboBoxModel(sortiment.getSortiment().toArray()));
		deleteItemComboBox.setModel(new DefaultComboBoxModel(iBon.getEintraege().toArray()));
		mainFrame.getReceiptTextPane().setText(iBon.toString());
		mainFrame.getFormattedTextField_itemCount().setText("1");
	}
	
	
	private Produkt getProduktByName(String name) {
		for (Produkt produkt : sortiment.getSortiment()) {
			if (produkt.getBezeichnung().equalsIgnoreCase(name)) {
				return produkt;
			}
		}
		return null;
		
	} 

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
			Produkt produktToAdd = getProduktByName(mainFrame.getTextField_Artikelname().getText());
			if (produktToAdd == null) {
				NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
				Number preisNumber = 0;
				try {
					preisNumber = numberFormat.parse(mainFrame.getFormattedTextField_Preis().getText());
				} catch (ParseException e1) {
					System.out.println("Exception catched, unable to parse number, no product added");
					e1.printStackTrace();
					return;
				}
				double preisDouble = preisNumber.doubleValue();
				
				produktToAdd = new Produkt(mainFrame.getTextField_Artikelname().getText(), preisDouble, sortiment.getLastId());
				sortiment.hinzufuegen(produktToAdd);
				
			}
			KassenbonEintrag toAddReceiptItem = new KassenbonEintrag(produktToAdd,
					Integer.parseInt(mainFrame.getFormattedTextField_itemCount().getText()));
			iBon.addEintrag(toAddReceiptItem);
			updateGUI();
		}
	}

	
}
