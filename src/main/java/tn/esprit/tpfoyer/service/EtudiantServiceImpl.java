package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private static final Logger logger = LogManager.getLogger(EtudiantServiceImpl.class);

    EtudiantRepository etudiantRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        logger.info("Début de la récupération de tous les étudiants");
        List<Etudiant> etudiants = etudiantRepository.findAll();
        logger.info("Nombre d'étudiants récupérés : {}", etudiants.size());
        return etudiants;
    }

    public Etudiant retrieveEtudiant(Long etudiantId) {
        logger.info("Récupération de l'étudiant avec ID : {}", etudiantId);
        return etudiantRepository.findById(etudiantId).orElse(null);
    }

    public Etudiant addEtudiant(Etudiant c) {
        logger.info("Ajout d'un nouvel étudiant : {}", c);
        Etudiant etudiant = etudiantRepository.save(c);
        logger.info("Étudiant ajouté avec ID : {}", etudiant.getIdEtudiant());
        return etudiant;
    }

    public Etudiant modifyEtudiant(Etudiant c) {
        logger.info("Modification de l'étudiant avec ID : {}", c.getIdEtudiant());
        Etudiant etudiant = etudiantRepository.save(c);
        logger.info("Étudiant modifié avec succès : {}", etudiant);
        return etudiant;
    }

    public void removeEtudiant(Long etudiantId) {
        logger.info("Suppression de l'étudiant avec ID : {}", etudiantId);
        try {
            etudiantRepository.deleteById(etudiantId);
            logger.info("Étudiant supprimé avec succès");
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'étudiant avec ID : {}", etudiantId, e);
        }
    }

    public Etudiant recupererEtudiantParCin(long cin) {
        logger.info("Récupération de l'étudiant avec CIN : {}", cin);
        Etudiant etudiant = etudiantRepository.findEtudiantByCinEtudiant(cin);
        if (etudiant != null) {
            logger.info("Étudiant trouvé : {}", etudiant);
        } else {
            logger.warn("Aucun étudiant trouvé avec le CIN : {}", cin);
        }
        return etudiant;
    }
}
