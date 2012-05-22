package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

/**
 * Ein {@link Medium} definiert Eigenschaften, die alle Medien unserer Mediathek
 * gemeinsam haben. Der Titel eines {@link Medium}s dient als eindeutige
 * Identifikation. Ein {@link Medium} kann ausgeliehen und zurückgegeben werden.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public interface Medium
{
    /**
     * Berechnet die Mietgebühr in Euro, für den Fall, dass das Medium am Datum
     * "von" ausgeliehen wird und am Datum "bis" zurückgegeben wird. Für ein
     * Medium, das an einem Tag ausgeliehen wird und am nächsten Tag
     * zurückgegeben wird, werden 2 Miettage berechnet.
     * 
     * @param von
     *            Das Ausleihdatum
     * @param bis
     *            Das Rückgabedatum
     * @return Die Mietgebühr in Euro als {@link Geldbetrag}
     * 
     * @require von != null
     * @require bis != null
     * @require von.compareTo(bis) <= 0
     * 
     * @ensure result != null
     */
    Geldbetrag berechneMietgebuehr(Datum von, Datum bis);

    /**
     * Gibt einen formatierten Text mit allen Eigenschaften des Mediums zurück.
     * Jedes Attribute steht in einer eigenen Zeile mit der Form "Attributename:
     * Attributwert". Hinweis: Ein Zeilenumbruch wird durch den Character '\n'
     * dargestellt.
     * 
     * @return Eine Textrepräsentation des Mediums.
     * 
     * @ensure result != null
     */
    String getFormatiertenString();

    /**
     * Gibt den Kommentar zu diesem Medium zurück.
     * 
     * @return Den Kommentar zu diesem Medium.
     * 
     * @ensure result != null
     */
    String getKommentar();

    /**
     * Ändert den Kommentar des Mediums
     * 
     * @param kommentar
     *            Ein Kommentar zu diesem Medium
     * 
     * @require kommentar != null
     * @ensure getKommentar() == kommentar
     */
    public void setKommentar(String kommentar);

    /**
     * Gibt die Bezeichnung für die Medienart zurück.
     * 
     * @return Die Bezeichnung für die Medienart.
     * 
     * @ensure result != null
     */
    String getMedienBezeichnung();

    /**
     * Gibt den Titel des Mediums zurück.
     * 
     * @return Den Titel des Mediums
     * 
     * @ensure result != null
     */
    String getTitel();

    /**
     * Ändert den Titel des Mediums.
     * 
     * @param titel
     *            Der Titel des Mediums
     * 
     * @require titel != null
     * @ensure getTitel() == titel
     */
    public void setTitel(String titel);

    /*
     * ################# Teil für das Vormerken ################
     */

    /**
     * Setzt den Kunden als neuen Vormerker an das Ende der Liste
     * 
     * @param kunde
     *            zu setzender Kunde
     * @param medium
     *            vorzumerkendes Medium
     * 
     * @require kundeImBestand(kunde)
     * @require istVormerkenMoeglich(kunde)
     * @ensure getVormerker.contains(kunde)
     * @ensure getVormerker.sitze <= 3
     */
    void setVormerker(Kunde kunde);

    /**
     * Liefert eine Liste der Vormerker für das Medium
     * 
     * @param medium
     *            Medium
     * 
     * @return List<Kunde> Vollständige Liste der vormerkenden Kunden
     */
    List<Kunde> getVormerker();

    /**
     * Gibt den Kunden an erster Stelle der Vormerkliste aus. Kann auch null
     * sein.
     * 
     * @param medium
     * 
     * @return Kunde an erster Stelle der Liste
     */
    Kunde getErsterVormerker();

    /**
     * prüft, ob der Kunde das Medium vormerken kann. Eine Prüfung Vormerker !=
     * Entleiher macht VerleihService. Daher darf keine andere Klasse diese
     * Methode benutzen.
     * 
     * @param kunde
     *            Kunde
     * @param medium
     *            Medium
     * @return Vormerken möglich
     * 
     */
    boolean istVormerkenMoeglich(Kunde kunde);

    /**
     * Entfernt den Kunden aus der Vormerkliste des Mediums
     * 
     * @param kunde
     *            Kunde
     * @param medium
     *            Medium
     * 
     * @require kundeImBestand(kunde)
     * @require getVormerker.contains(kunde)
     * @ensure !getVormerker.contains(kunde)
     */
    void vormerkerLoeschen(Kunde kunde);

}
