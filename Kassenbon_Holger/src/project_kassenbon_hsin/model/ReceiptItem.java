package project_kassenbon_hsin.model;

import project_kassenbon.model.sortiment.Produkt;

public class ReceiptItem {
	private Produkt produkt;
	private int anzahl;

	public ReceiptItem(Produkt produkt, int anzahl) {
		this.produkt = produkt;
		this.anzahl = anzahl;
	}

	public Produkt getArtikel() {
		return produkt;
	}

	public int getAnzahl() {
		return anzahl;
	}

	@Override
	public String toString() {
		return anzahl + " x " + produkt.getBezeichnung();
	}

	public String toStringPrint() {
		String returnString = String.format("%-18s", produkt.getBezeichnung())
				+ String.format("%8.2f%n", produkt.getPreis() * anzahl);
		if (anzahl > 1) {
			returnString += String.format("%4d x", anzahl) + String.format("%8.2f%n", produkt.getPreis());
		}
		return returnString;
	}

}
