package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static int id = 1;

    public AccidentMem() {
        Accident accident1 = new Accident(id++, "Превышение скорости", "Превышение скорости на 20 км/ч",
                "ул. Волгоградский проспект");
        Accident accident2 = new Accident(id++, "Разметка", "Движение по выделенной полосе для общественного транспорта",
                "ул. Земляной Вал");
        Accident accident3 = new Accident(id++, "Ограничение дорожных знаков", "Остановка в запрещенном месте",
                "Луговой проезд");
       accidents.put(accident1.getId(), accident1);
       accidents.put(accident2.getId(), accident2);
       accidents.put(accident3.getId(), accident3);
    }

    @Override
    public List<Accident> findAll() {
        List<Accident> result = new ArrayList<>(accidents.values());
        return result;
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(id++);
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        Accident result = accidents.put(accident.getId(), accident);
        return result != null;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }
}
