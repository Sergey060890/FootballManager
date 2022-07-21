package project.service.interfaces;

import project.models.Substitution;

import java.util.List;

public interface SubsService {
    /**
     * Show all substitution in player
     */
    List<Substitution> showAllSubsInPlayer(Integer id);

    /**
     * Show all substitution out player
     */
    List<Substitution> showAllSubsOutPlayer(Integer id);

    /**
     * Delete substitution
     */
    void deleteSubs(Integer id);

}
