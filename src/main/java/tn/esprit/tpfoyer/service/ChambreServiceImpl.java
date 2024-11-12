package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private static final Logger log = LoggerFactory.getLogger(ChambreServiceImpl.class);

    private final ChambreRepository chambreRepository;

    public List<Chambre> retrieveAllChambres() {
        log.info("In method retrieveAllChambres:");
        List<Chambre> listC = chambreRepository.findAll();
        log.info("Out of retrieveAllChambres:");
        return listC;
    }

    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).orElse(null);
    }

    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    public Chambre modifyChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc) {
        return chambreRepository.findAllByTypeC(tc);
    }

    public Chambre trouverchambreSelonEtudiant(long cin) {
        return chambreRepository.trouverChselonEt(cin);
    }
}
