package project_kassenbon_hsin.model.sortiment;

public interface ISortimentSpeicher_V1 {
	public Produkt_V1[] getSortiment();
	public void sortimentSpeichern(Produkt_V1[] sortiment);
	public void produktHinzufuegen(Produkt_V1 produkt);
	public void produktAktualisieren(Produkt_V1 produkt);
	public void produktEntfernen(Produkt_V1 produkt);
	
}
