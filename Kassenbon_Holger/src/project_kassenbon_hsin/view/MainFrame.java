package project_kassenbon_hsin.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

//import project_kassenbon.model.Artikel;
//import project_kassenbon_hsin.model.ReceiptItem;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtField_Verkaeufer;
	private JComboBox<Object> comboBox_addItem;
	private JFormattedTextField formattedTextField_itemCount;
//	private final Action action = new SwingAction();
	private JComboBox<Object> comboBox_deleteItem;
	private JButton btnNewItem;
	private JTextPane receiptTextPane;
	private JButton btnSaveBon;
	private JButton btnNewBon;
	private JTextField textField_Artikelname;
	private JButton btnDeleteItem;
	private JFormattedTextField formattedTextField_Preis;
	private JButton btnDeleteProdukt;
	private JFormattedTextField formattedTextField_id;



	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Kassenbon Projekt - Holgers GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);

		JPanel panel_left = new JPanel();
		splitPane.setLeftComponent(panel_left);
		panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));

		JPanel panel_receipt = new JPanel();
		panel_receipt.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_left.add(panel_receipt);
		panel_receipt.setLayout(new BoxLayout(panel_receipt, BoxLayout.Y_AXIS));

		btnNewBon = new JButton("Neuer Kassenbon");
		btnNewBon.setMaximumSize(new Dimension(150, 23));
		btnNewBon.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_receipt.add(btnNewBon);

		btnSaveBon = new JButton("Verk\u00E4ufer speichern");
		btnSaveBon.setMaximumSize(new Dimension(150, 23));
		btnSaveBon.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_receipt.add(btnSaveBon);

		JPanel panel__verkaeufer = new JPanel();
		panel__verkaeufer.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_receipt.add(panel__verkaeufer);
		panel__verkaeufer.setLayout(new BoxLayout(panel__verkaeufer, BoxLayout.Y_AXIS));

		JLabel lblVerkaeufer = new JLabel("Verk\u00E4ufer");
		panel__verkaeufer.add(lblVerkaeufer);

		JPanel panel_verkauferTextField = new JPanel();
		panel__verkaeufer.add(panel_verkauferTextField);

		txtField_Verkaeufer = new JTextField();
		txtField_Verkaeufer.setText("Herr Max Mustermann");
		txtField_Verkaeufer.setColumns(15);
		panel_verkauferTextField.add(txtField_Verkaeufer);

		JPanel panel_receiptItem = new JPanel();
		panel_receiptItem.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_left.add(panel_receiptItem);
		panel_receiptItem.setLayout(new BoxLayout(panel_receiptItem, BoxLayout.Y_AXIS));

		JPanel panel_newItem = new JPanel();
		panel_newItem
				.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kassenbon/Sortiment Eintrag hinzuf\u00FCgen/entfernen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panel_receiptItem.add(panel_newItem);
		panel_newItem.setLayout(new BoxLayout(panel_newItem, BoxLayout.Y_AXIS));
		
		btnDeleteProdukt = new JButton("Produkt l\u00F6schen");
		btnDeleteProdukt.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_newItem.add(btnDeleteProdukt);

		JPanel panel_artikelComboBox = new JPanel();
		panel_newItem.add(panel_artikelComboBox);

		comboBox_addItem = new JComboBox<Object>();
		comboBox_addItem.setFont(new Font("Courier New", Font.PLAIN, 11));
		comboBox_addItem
				.setModel(new DefaultComboBoxModel<Object>(new String[] { "Test", "test2", "dies ist ein sehr langer Text" }));
		comboBox_addItem.setPreferredSize(new Dimension(250, 22));
		comboBox_addItem.setMinimumSize(new Dimension(100, 22));
		panel_artikelComboBox.add(comboBox_addItem);

		JPanel panel_artikelTextFields = new JPanel();
		panel_newItem.add(panel_artikelTextFields);
		
// eigener Number Formatter 		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter integerFormatter = new MyNumberFormatter(format);
		integerFormatter.setValueClass(Integer.class);
		integerFormatter.setMinimum(1);
		integerFormatter.setMaximum(10000);
		integerFormatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		integerFormatter.setCommitsOnValidEdit(true);
// Ende eigener Formatter	   
		formattedTextField_id = new JFormattedTextField(integerFormatter);
		formattedTextField_id.setColumns(3);
		panel_artikelTextFields.add(formattedTextField_id);

		textField_Artikelname = new JTextField();
		textField_Artikelname.setToolTipText("Artikelbezeichnung");
		panel_artikelTextFields.add(textField_Artikelname);
		textField_Artikelname.setColumns(20);
		textField_Artikelname.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textField_Artikelname.selectAll();
			}
		});
		
		

// eigener Number Formatter double		
		format = DecimalFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		NumberFormatter doubleFormatter = new MyNumberFormatter(format);
//	    doubleFormatter.setValueClass(Double.class);
		doubleFormatter.setMinimum(0.0);
		doubleFormatter.setMaximum(10000.0);
		doubleFormatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
//	    doubleFormatter.setCommitsOnValidEdit(true);
// Ende eigener Formatter	    

		formattedTextField_Preis = new JFormattedTextField(doubleFormatter);
		formattedTextField_Preis.setColumns(5);
		formattedTextField_Preis.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	formattedTextField_Preis.selectAll();
		            }
				});
			}
		});
		panel_artikelTextFields.add(formattedTextField_Preis);

		JPanel panel_itemCount = new JPanel();
		panel_newItem.add(panel_itemCount);
		panel_itemCount.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblArtikelZahl = new JLabel("Anzahl Artikel");
		panel_itemCount.add(lblArtikelZahl);

// eigener Number Formatter 		
		format = NumberFormat.getInstance();
		integerFormatter = new MyNumberFormatter(format);
		integerFormatter.setValueClass(Integer.class);
		integerFormatter.setMinimum(1);
		integerFormatter.setMaximum(1000);
		integerFormatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		integerFormatter.setCommitsOnValidEdit(true);
// Ende eigener Formatter	    

		formattedTextField_itemCount = new JFormattedTextField(integerFormatter);
		formattedTextField_itemCount.setColumns(5);
		formattedTextField_itemCount.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	formattedTextField_itemCount.selectAll();
		            }
				});
			}
		});
		panel_itemCount.add(formattedTextField_itemCount);
		
				btnNewItem = new JButton("Eintrag hinzuf\u00FCgen");
				btnNewItem.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel_newItem.add(btnNewItem);

		JPanel panel_deleteItem = new JPanel();
		panel_deleteItem.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
				new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						"Eintrag l\u00F6schen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panel_receiptItem.add(panel_deleteItem);
		panel_deleteItem.setLayout(new BoxLayout(panel_deleteItem, BoxLayout.Y_AXIS));

		JPanel panel_ComboBoxDeleteItem = new JPanel();
		panel_deleteItem.add(panel_ComboBoxDeleteItem);
		panel_ComboBoxDeleteItem.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		comboBox_deleteItem = new JComboBox<Object>();
		comboBox_deleteItem.setPreferredSize(new Dimension(250, 22));
		comboBox_deleteItem.setMinimumSize(new Dimension(100, 22));
		comboBox_deleteItem.setFont(new Font("Courier New", Font.PLAIN, 11));
		panel_ComboBoxDeleteItem.add(comboBox_deleteItem);

		btnDeleteItem = new JButton("Eintrag l\u00F6schen");
		btnDeleteItem.setAlignmentX(0.5f);
		panel_deleteItem.add(btnDeleteItem);

		receiptTextPane = new JTextPane();
		receiptTextPane.setEditable(false);

		JPanel panel_right = new JPanel();
		panel_right.add(receiptTextPane);

		JScrollPane scrollPane = new JScrollPane(panel_right);

		receiptTextPane.setFont(new Font("Courier New", Font.PLAIN, 11));

		splitPane.setRightComponent(scrollPane);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTextField_verkaeufer() {
		return txtField_Verkaeufer;
	}

	public JTextPane getReceiptTextPane() {
		return receiptTextPane;
	}

	public JComboBox<Object> getComboBox_addItem() {
		return comboBox_addItem;
	}

	public JFormattedTextField getFormattedTextField_itemCount() {
		return formattedTextField_itemCount;
	}

	public JComboBox<Object> getComboBox_deleteItem() {
		return comboBox_deleteItem;
	}

	public JButton getBtnDeleteItem() {
		return btnDeleteItem;
	}

	public JButton getBtnNewItem() {
		return btnNewItem;
	}

	public JTextField getTxtField_Verkaeufer() {
		return txtField_Verkaeufer;
	}

	public JButton getBtnSaveBon() {
		return btnSaveBon;
	}

	public JButton getBtnNewBon() {
		return btnNewBon;
	}

	public JTextField getTextField_Artikelname() {
		return textField_Artikelname;
	}

	public JFormattedTextField getFormattedTextField_Preis() {
		return formattedTextField_Preis;
	}
	
	

	public JButton getBtnDeleteProdukt() {
		return btnDeleteProdukt;
	}



	public JFormattedTextField getFormattedTextField_id() {
		return formattedTextField_id;
	}



	// aktuell nicht genutzt
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Artikel entfernen");
			putValue(SHORT_DESCRIPTION, "Liste mit Artikeln auf Kassenbon anzeigen, gewählten Artikel löschen");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("SwingAction ausgelöst");
		}
	}
}
