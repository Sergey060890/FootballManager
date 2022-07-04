package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Substitution;
import project.repository.SubstitutionRepository;
import project.service.interfaces.SubsService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class SubsServiceImpl implements SubsService {
    @Autowired
    private SubstitutionRepository substitutionRepository;

    @Override
    public List<Substitution> showAllSubstitutionInfo() {
        List<Substitution> substitutions = substitutionRepository.findAll();
        return substitutions;
    }
}
