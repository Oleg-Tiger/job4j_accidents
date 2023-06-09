package ru.job4j.accidents.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentStore;
import ru.job4j.accidents.service.mem.AccidentTypeMemService;
import ru.job4j.accidents.service.mem.RuleMemService;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemRepository implements AccidentStore {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger(1);
    private final List<AccidentType> types = new AccidentTypeMemService().getAllTypes();
    private final List<Rule> rules = new RuleMemService().getAllRules();

    public AccidentMemRepository() {
        Accident accident1 = new Accident(id.incrementAndGet(), "Превышение скорости", "Превышение скорости на 20 км/ч",
                "ул. Волгоградский проспект", types.get(3), Set.of(rules.get(0)));
        Accident accident2 = new Accident(id.incrementAndGet(), "Разметка", "Движение по выделенной полосе для общественного транспорта",
                "ул. Земляной Вал", types.get(3), Set.of(rules.get(0), rules.get(1)));
        Accident accident3 = new Accident(id.incrementAndGet(), "Ограничение дорожных знаков", "Остановка в запрещенном месте",
                "Луговой проезд", types.get(3), Set.of(rules.get(1), rules.get(2)));
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
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (x, y) -> accident) != null;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }
}
