package project_kassenbon_hsin.model.sortiment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

//import project_kassenbon.model.sortiment.ISortimentSpeicher;
//import project_kassenbon.model.sortiment.Produkt;

public class Sortiment_V1 {

	private ISortimentSpeicher_V1 speicher;
	private ArrayList<Produkt_V1> sortiment;
	
	

	public Sortiment_V1(ISortimentSpeicher_V1 speicher) {
		super();
		this.speicher = speicher;
		this.sortiment = new ArrayList<Produkt_V1>(Arrays.asList(speicher.getSortiment()));
//		this.sortiment = (ArrayList<Produkt>) Arrays.asList(speicher.getSortiment());
	}

	public ISortimentSpeicher_V1 getSpeicher() {
		return speicher;
	}

	public ArrayList<Produkt_V1> getSortiment() {
		return sortiment;
	}
	
	public Produkt_V1 getProduktByID(int id) {
		for (Produkt_V1 produkt : sortiment) {
			if (produkt.getId() == id) {
				return produkt;
			}
		}
		return null;
	}
	
	public Produkt_V1 getProduktByName(String name) {
		for (Produkt_V1 produkt : sortiment) {
			if (produkt.getBezeichnung().equalsIgnoreCase(name)) {
				return produkt;
			}
		}
		return null;
	}
	
	
	public Produkt_V1 addProdukt(String name, double preis) {
		Produkt_V1 newProdukt = new Produkt_V1(name, preis, getNextID());
		speicher.produktHinzufuegen(newProdukt);
		this.sortiment = new ArrayList<Produkt_V1>(Arrays.asList(speicher.getSortiment()));
		return newProdukt;
	}
	
	private int getNextID() {
		int counter = 1;
		Produkt_V1 produkt;
		Iterator<Produkt_V1> produktIterator = sortiment.iterator();
		
		while (produktIterator.hasNext()) {
			produkt = (Produkt_V1) produktIterator.next();
			if (produkt.getId() == counter) {
				counter++;
				produktIterator = sortiment.iterator();
			}
		}
		return counter;
		
	}
	
	
	
	
}
