package project_kassenbon_hsin.model.sortiment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import project_kassenbon.model.sortiment.ISortimentSpeicher;
import project_kassenbon.model.sortiment.Produkt;

//import project_kassenbon.model.Artikel;
//import project_kassenbon_hsin.model.sortiment.ISortimentSpeicher;
//import project_kassenbon_hsin.model.sortiment.Produkt;

public class DefaultProducts implements ISortimentSpeicher {
	
	private ArrayList<Produkt> produktListe = new ArrayList<Produkt>();
//	private HashSet<Produkt> produktSet = new HashSet<Produkt>();
	
	

	/**
	 * 
	 */
	public DefaultProducts() {
//		produktSet.add(new Produkt("15 Fischstäbchen", 1.79, getNextID()));
//		produktSet.add(new Produkt("Steaks", 3.99, getNextID()));
//		produktSet.add(new Produkt("Naturell (1l)", 4.99, getNextID()));
//		produktSet.add(new Produkt("Magnum Eiscreme", 2.99, getNextID()));
//		produktSet.add(new Produkt("Banane", 1.99, 123));
//		produktSet.add(new Produkt("Kaffee", 4.99, 456));
//		for (int i = 1; i <= 10; i++) {
//			produktSet.add(new Produkt("Random Artikel " + i, 10.99 + i, getNextID()));
//		}
		produktListe.add(new Produkt("15 Fischstäbchen", 1.79, getNextID()));
		produktListe.add(new Produkt("Steaks", 3.99, getNextID()));
		produktListe.add(new Produkt("Naturell (1l)", 4.99, getNextID()));
		produktListe.add(new Produkt("Magnum Eiscreme", 2.99, getNextID()));
		produktListe.add(new Produkt("Banane", 1.99, 123));
		produktListe.add(new Produkt("Kaffee", 4.99, 456));
		for (int i = 1; i <= 10; i++) {
			produktListe.add(new Produkt("Random Artikel " + i, 10.99 + i, getNextID()));
		}
	}

	public int getNextID() {
		int counter = 1;
		Produkt produkt;
		Iterator<Produkt> produktIterator = produktListe.iterator();
		
		while (produktIterator.hasNext()) {
			produkt = (Produkt) produktIterator.next();
			if (produkt.getId() == counter) {
				counter++;
				produktIterator = produktListe.iterator();
			}
		}
		return counter;
		
	}
	
	
	// Interface Methoden
	
	@Override
	public Produkt[] getSortiment() {
		return produktListe.toArray(new Produkt[0]);
	}

	@Override
	public void sortimentSpeichern(Produkt[] sortiment) {
		produktListe = (ArrayList<Produkt>) Arrays.asList(sortiment);

	}

	@Override
	public void produktHinzufuegen(Produkt produkt) {
		produktListe.add(produkt);

	}

	@Override
	public void produktAktualisieren(Produkt produktParam) {
		int index = -1;
		for (Produkt produkt : produktListe) {
			if (produkt.getId() == produktParam.getId()) {
				produktListe.remove(produkt);
				produktListe.add(produktParam);
			}
		}

	}

	@Override
	public void produktEntfernen(Produkt produkt) {
		produktListe.remove(produkt);

	}

	@Override
	public int getLastId() {
		int lastId = 0;
		for (Produkt produkt : produktListe) {
			if(lastId < produkt.getId()) {
				lastId = produkt.getId();
			}
		}
		return lastId + 1;
	}

}
