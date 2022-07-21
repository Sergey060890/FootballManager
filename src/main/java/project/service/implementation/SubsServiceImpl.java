package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.RedCard;
import project.models.Substitution;
import project.repository.SubstitutionRepository;
import project.service.interfaces.SubsService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class SubsServiceImpl implements SubsService {

    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    private SubstitutionRepository substitutionRepository;

    /**
     * Show all in substitution player
     */
    @Override
    public List<Substitution> showAllSubsInPlayer(Integer id) {//id игрока
        List<Substitution> substitutions = new ArrayList<>();
        for (Substitution subs : substitutionRepository.findAll()
        ) {
            if (Objects.equals(subs.getPlayerIn().getPlayer_id(), id)) {
                substitutions.add(subs);
            }
        }
        return substitutions;
    }

    /**
     * Show all out substitution player
     */
    @Override
    public List<Substitution> showAllSubsOutPlayer(Integer id) {//id игрока
        List<Substitution> substitutions = new ArrayList<>();
        for (Substitution subs : substitutionRepository.findAll()
        ) {
            if (Objects.equals(subs.getPlayerOut().getPlayer_id(), id)) {
                substitutions.add(subs);
            }
        }
        return substitutions;
    }

    /**
     * Delete substitution
     */
    @Override
    public void deleteSubs(Integer id) {
        Substitution substitution =
                substitutionRepository.findById(id).orElseThrow();
        substitutionRepository.delete(substitution);
    }
}
