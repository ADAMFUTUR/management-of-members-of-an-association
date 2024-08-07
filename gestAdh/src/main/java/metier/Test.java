package metier;

import models.Adherent;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServiceRegister sv = new ServiceRegister();
		
		Adherent adh = new Adherent();
		adh.setNom("ddd");
		adh.setAge(19);
		adh.setCni("ffff");
		adh.setEmail("dddddd");
		adh.setPrenom("ddff");
		adh.setPassword("ddddd");
		adh.setProfession("dddd");
		adh.setTel(89);
		
		sv.insertFormData(adh);
				

	}

}
