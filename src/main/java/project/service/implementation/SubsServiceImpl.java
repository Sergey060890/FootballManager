package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import project.models.Substitution;
import project.repository.SubstitutionRepository;
import project.service.interfaces.SubsService;

import java.util.List;

public class SubsServiceImpl implements SubsService {
    @Autowired
    private SubstitutionRepository substitutionRepository;

    @Override
    public List<Substitution> showAllSubstitutionInfo() {
        List<Substitution> substitutions = substitutionRepository.findAll();
        return substitutions;
    }
}
