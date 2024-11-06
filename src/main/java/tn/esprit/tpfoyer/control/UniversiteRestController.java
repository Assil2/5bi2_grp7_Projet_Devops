package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

    private static final Logger logger = LogManager.getLogger(UniversiteRestController.class);

    IUniversiteService universiteService;

    // http://localhost:8089/tpfoyer/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        logger.debug("Attempting to retrieve all universities");
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        logger.info("Retrieved {} universities", listUniversites.size());
        return listUniversites;
    }

    // http://localhost:8089/tpfoyer/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        logger.debug("Attempting to retrieve universite with ID: {}", uId);
        Universite universite = universiteService.retrieveUniversite(uId);
        if (universite != null) {
            logger.info("Universite found: {}", universite);
        } else {
            logger.warn("Universite with ID: {} not found", uId);
        }
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/add-universite
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        logger.debug("Attempting to add a new universite: {}", u);
        Universite universite = universiteService.addUniversite(u);
        logger.info("Universite added with ID: {}", universite.getIdUniversite());
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/remove-universite/{universite-id}
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        logger.debug("Attempting to remove universite with ID: {}", uId);
        try {
            universiteService.removeUniversite(uId);
            logger.info("Universite with ID: {} removed successfully", uId);
        } catch (Exception e) {
            logger.error("Error occurred while removing universite with ID: {}", uId, e);
        }
    }

    // http://localhost:8089/tpfoyer/universite/modify-universite
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        logger.debug("Attempting to modify universite: {}", u);
        Universite universite = universiteService.modifyUniversite(u);
        logger.info("Universite modified: {}", universite);
        return universite;
    }
}
