package metier;

import metier.AdherentDAO;
import models.Adherent;
import java.util.List;

public class TestAdherentDAO {

    public static void main(String[] args) {
        AdherentDAO adherentDAO = new AdherentDAO();

        

        // Test de la méthode supprimerAdherent
        String cniAdherentASupprimer = "DI7320"; // Remplacer par le CNI de l'adhérent à supprimer
        adherentDAO.supprimerAdherent(cniAdherentASupprimer);
        System.out.println("Adhérent avec le CNI " + cniAdherentASupprimer + " supprimé avec succès.");

    }
    }