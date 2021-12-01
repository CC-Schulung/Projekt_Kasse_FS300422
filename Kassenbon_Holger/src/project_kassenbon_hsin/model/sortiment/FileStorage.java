package project_kassenbon_hsin.model.sortiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import project_kassenbon.model.sortiment.ISortimentSpeicher;
import project_kassenbon.model.sortiment.Produkt;


public class FileStorage implements ISortimentSpeicher {

//	private ArrayList<Produkt> produktListe = new ArrayList<Produkt>();
//	private HashSet<Produkt> produktSet = new HashSet<Produkt>();
	private File file;

	/**
	 * 
	 */
	public FileStorage(String fileLocation) {
		file = new File(fileLocation);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Exception catched while creating file in Constructor:");
			e.printStackTrace();
		}
	}
	
	public FileStorage() {
		this(".\\FileSaverStandardFile.csv");
	}

	public int getNextID() {
		int counter = 1;
		Produkt produkt;
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
		Iterator<Produkt> produktIterator = tmpProduktList.iterator();
		
		
		while (produktIterator.hasNext()) {
			produkt = (Produkt) produktIterator.next();
			if (produkt.getId() == counter) {
				counter++;
				produktIterator = tmpProduktList.iterator();
			}
		}
		return counter;

	}

	// Interface Methoden

	@Override
	public Produkt[] getSortiment() {
//		return produktListe.toArray(new Produkt[0]);
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>();
		String[] produktAsStringArray;
		try {
			Scanner fileScanner = new Scanner(file);

			while (fileScanner.hasNext()) {
				produktAsStringArray = fileScanner.nextLine().split("[,;]");
				tmpProduktList.add(new Produkt(produktAsStringArray[1], Double.parseDouble(produktAsStringArray[2]), Integer.parseInt(produktAsStringArray[0])));
			}

			fileScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("Exception catched while reading file in getSortiment:");
			e.printStackTrace();
		}
		return tmpProduktList.toArray(new Produkt[0]);
	}

	@Override
	public void sortimentSpeichern(Produkt[] produktArray) {
//		produktListe = (ArrayList<Produkt>) Arrays.asList(sortiment);
//		System.out.println(file);
		try {
			FileWriter fw = new FileWriter(file, false);
			for (Produkt produkt : produktArray) {
				fw.write(produkt.getId() + ";" + produkt.getBezeichnung() + ";" + produkt.getPreis()
						+ System.lineSeparator());
//				System.out.println(produkt);
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("catched exception while writing file:");
			e.printStackTrace();
		}
	}

	@Override
	public void produktHinzufuegen(Produkt produkt) {
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
		tmpProduktList.add(produkt);
		sortimentSpeichern(tmpProduktList.toArray(new Produkt[0]));

	}

	@Override
	public void produktAktualisieren(Produkt produktParam) {
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
		int index = -1;
		for (Produkt produkt : tmpProduktList) {
			if (produkt.getId() == produktParam.getId()) {
				index = tmpProduktList.indexOf(produkt);
				tmpProduktList.remove(produkt);
				tmpProduktList.add(index, produktParam);
			}
		}
		sortimentSpeichern(tmpProduktList.toArray(new Produkt[0]));

	}

	@Override
	public void produktEntfernen(Produkt produktParam) {
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
		int index = -1;
		for (Produkt produkt : tmpProduktList) {
			if (produkt.getId() == produktParam.getId()) {
				index = tmpProduktList.indexOf(produkt);
			}
		}
		System.out.println(tmpProduktList.remove(index) != null ? "Produkt aus Sortiment enfernt: " + produktParam : "Fehler: Produkt nicht entfernt");
		sortimentSpeichern(tmpProduktList.toArray(new Produkt[0]));
	}

	@Override
	public int getLastId() {
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
		int lastId = 0;
		for (Produkt produkt : tmpProduktList) {
			if (lastId < produkt.getId()) {
				lastId = produkt.getId();
			}
		}
		return lastId + 1;
	}

}
