package ru.job4j.accidents.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.hibernate.AccidentTypeHibernateRepository;
import ru.job4j.accidents.service.AccidentTypeService;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccidentTypeHibernateService implements AccidentTypeService {

    private final AccidentTypeHibernateRepository repository;

    @Override
    public List<AccidentType> getAllTypes() {
        return repository.getAllTypes();
    }

    @Override
    public Optional<AccidentType> findById(Accident accident) {
        int id = accident.getType().getId();
        Optional<AccidentType> result = repository.findById(id);
        accident.setType(result.get());
        return result;
    }
}
