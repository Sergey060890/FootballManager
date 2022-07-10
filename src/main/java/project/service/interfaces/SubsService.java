package project.service.interfaces;



import project.models.Substitution;

import java.util.List;

public interface SubsService {
    List<Substitution> showAllSubsInPlayer(Integer id);

    List<Substitution> showAllSubsOutPlayer(Integer id);

    void deleteSubs(Integer id);

}
