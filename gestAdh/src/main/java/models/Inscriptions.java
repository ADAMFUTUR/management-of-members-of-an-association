package models;

public class Inscriptions {
	private int inscription_id;
	private String adherent_id;
	private int event_id;
	
	public String getAdherent_id() {
		return adherent_id;
	}
	public void setAdherent_id(String adherent_id) {
		this.adherent_id = adherent_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getInscription_id() {
		return inscription_id;
	}
	public void setInscription_id(int inscription_id) {
		this.inscription_id = inscription_id;
	}
}
