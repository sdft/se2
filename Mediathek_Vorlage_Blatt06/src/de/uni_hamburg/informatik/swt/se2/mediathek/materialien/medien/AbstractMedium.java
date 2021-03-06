package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;

/**
 * Ein {@link AbstractMedium} bietet eine Standardimplementation für ein
 * {@link Medium} an.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
abstract class AbstractMedium implements Medium
{
    // 4 Leerzeichen für formatierte Strings
    protected static final String SPACE = "    ";

    /**
     * Gebühr für einen Tag
     */
    private final int _tagesmietgebuehr;

    /**
     * Ein Kommentar zum Medium
     */
    private String _kommentar;

    /**
     * Der Titel des Mediums
     * 
     */
    private String _titel;

    /**
     * Vormerkliste
     */
    private List _vormerker;

    /**
     * Initialisiert ein neues Exemplar.
     * 
     * @param titel
     *            Der Titel des Mediums
     * @param kommentar
     *            Ein Kommentar zum Medium
     * 
     * @require titel != null
     * @require kommentar != null
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     */
    public AbstractMedium(String titel, String kommentar)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
        _titel = titel;
        _kommentar = kommentar;
        _tagesmietgebuehr = 300;
        _vormerker = new ArrayList<Kunde>();
    }

    @Override
    public Geldbetrag berechneMietgebuehr(Datum von, Datum bis)
    {
        assert von != null : "Vorbedingung verletzt: von != null";
        assert bis != null : "Vorbedingung verletzt: bis != null";
        assert von.compareTo(bis) <= 0 : "Vorbedingung verletzt: von <= bis";

        return new Geldbetrag(_tagesmietgebuehr * (bis.tageSeit(von) + 1));
    }

    @Override
    public String getFormatiertenString()
    {
        return getMedienBezeichnung() + ":\n" + SPACE + "Titel: " + _titel
                + "\n" + SPACE + "Kommentar: " + _kommentar + "\n";
    }

    @Override
    public String getKommentar()
    {
        return _kommentar;
    }

    /**
     * Ändert den Kommentar
     * 
     * @param kommentar
     *            Ein Kommentar zum Medium
     * 
     * @require kommentar != null
     * @ensure getKommentar() == kommentar
     */
    public void setKommentar(String kommentar)
    {
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
        _kommentar = kommentar;
    }

    @Override
    public String getTitel()
    {
        return _titel;
    }

    /**
     * Ändert den Titel
     * 
     * @param titel
     *            Der Titel des Mediums
     * 
     * @require titel != null
     * @ensure getTitel() == titel
     */
    public void setTitel(String titel)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        _titel = titel;
    }

    /*
     * ################# Teil für das Vormerken ################
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setVormerker(Kunde kunde)
    {
        _vormerker.add(kunde);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Kunde> getVormerker()
    {
        return _vormerker;
    }

    @Override
    public Kunde getErsterVormerker()
    {
        if (_vormerker.size() > 0)
        {
            return (Kunde) _vormerker.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean istVormerkenMoeglich(Kunde kunde)
    {
        // Länge >= 3
        if (_vormerker.size() >= 3)
        {
            return false;
        }

        // _vormerker.contains(kunde)
        if (kunde == null || _vormerker.contains(kunde))
        {
            return false;
        }

        return true;
    }

    @Override
    public void vormerkerLoeschen(Kunde kunde)
    {
        _vormerker.remove(kunde);
    }

}
