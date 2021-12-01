package project_kassenbon_hsin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import project_kassenbon.model.Artikel;
import project_kassenbon.model.sortiment.Produkt;
import project_kassenbon.model.sortiment.Sortiment;
import project_kassenbon_hsin.controller.HsinIBonController;
import project_kassenbon_hsin.controller.SchirmerIBonController;
import project_kassenbon_hsin.controller.SchirmerIBonController_V2;
import project_kassenbon_hsin.model.Receipt;
import project_kassenbon_hsin.model.ReceiptItem;
import project_kassenbon_hsin.model.sortiment.DB_Storage;
import project_kassenbon_hsin.model.sortiment.FileStorage;

public class App {

	public static void main(String[] args) {

//		DB_Storage dbs = new DB_Storage();
//		Sortiment sortiment = new Sortiment(dbs);
//		sortiment.getLastId();
		// test
		
//		Produkt produkt = (new Produkt("Neue ID 11", 1.79, 11));
//		dbs.produktAktualisieren(produkt);
	
//		dbs.truncateSortiment();
//		dbs.produktHinzufuegen(new Produkt("15 Fischstäbchen", 1.79, 1));
//		dbs.produktHinzufuegen(new Produkt("Steaks", 3.99, dbs.getNextID()));
//		dbs.produktHinzufuegen(new Produkt("Naturell (1l)", 4.99, dbs.getNextID()));
//		dbs.produktHinzufuegen(new Produkt("Magnum Eiscreme", 2.99, dbs.getNextID()));
//		dbs.produktHinzufuegen(new Produkt("Banane", 1.99, dbs.getNextID()));
//		dbs.produktHinzufuegen(new Produkt("Kaffee", 4.99, dbs.getNextID()));
//		dbs.produktHinzufuegen(new Produkt("Milch", 0.69, dbs.getNextID()));
//		dbs.produktHinzufuegen(new Produkt("Cola 1l", 1.5, dbs.getNextID()));
//		for (int i = 1; i <= 10; i++) {
//			dbs.produktHinzufuegen(new Produkt("Random Artikel " + i, (double) Math.round((10.99 + i) * 1000) / 1000 , dbs.getNextID()));
//		}
//		System.out.println(Arrays.toString(dbs.getSortiment()));

		HsinIBonController hic = new HsinIBonController();
//		SchirmerIBonController sic = new SchirmerIBonController();
//		SchirmerIBonController_V2 sic2 = new SchirmerIBonController_V2();

		
	}

}
