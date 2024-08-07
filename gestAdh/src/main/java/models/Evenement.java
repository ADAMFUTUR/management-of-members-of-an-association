package models;

import java.util.Date;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Evenement {

    private int id;
    private String localisationE;
    private Date dateE;
    private String type;
    private int heure;

    // Date format for parsing and formatting
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalisationE() {
        return localisationE;
    }

    public void setLocalisationE(String localisationE) {
        this.localisationE = localisationE;
    }

    public Date getDateE() {
        return dateE;
    }

    public void setDateE(Date dateE) {
        this.dateE = dateE;
    }

    public void setDateE(String dateE) throws ParseException {
        this.dateE = dateFormat.parse(dateE);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Method to get the date as a formatted string
    public String getDateEAsString() {
        return dateFormat.format(this.dateE);
    }

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}
}
