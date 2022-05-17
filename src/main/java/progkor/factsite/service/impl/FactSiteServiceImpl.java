package progkor.factsite.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progkor.factsite.model.FactText;
import progkor.factsite.model.Theme;
import progkor.factsite.model.exception.NotFoundException;
import progkor.factsite.service.FactSiteService;

@Service
public class FactSiteServiceImpl implements FactSiteService {

    private final List<FactText> dataBase = new ArrayList<>();

    @Autowired
    public FactSiteServiceImpl() {
        dataBase.add(new FactText(1L, "Egy ember az élete során annyit sétál, hogy 5-ször megkerülhetné a Földet.", Theme.GENERAL));
        dataBase.add(new FactText(2L, "A csillagorrú vakond a leggyorsabb evő az egész világon.", Theme.ANIMALS));
    }

    public FactSiteServiceImpl(final List<FactText> factTexts) {
        dataBase.addAll(factTexts);
    }

    @Override
    public List<FactText> getAllFactTexts() {
        return Collections.unmodifiableList(dataBase);
    }

    @Override
    public FactText getFactText(final Long id) {
        return dataBase.stream()
                .filter(factText -> factText.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public FactText createFactText(final FactText factText) {
        factText.setId(getNextId());
        dataBase.add(factText);
        return factText;
    }

    @Override
    public FactText updateFactText(final Long id, final FactText factTextChange) {
        final FactText factText = getFactText(id);
        factText.setContext(factTextChange.getContext());
        factText.setTheme(factTextChange.getTheme());
        return factText;
    }

    @Override
    public void deleteFactText(final Long id) {
        final FactText factText = getFactText(id);
        dataBase.remove(factText);
    }

    private long getNextId() {
        return getLastId() + 1L;
    }

    private long getLastId() {
        return dataBase.stream()
                .mapToLong(FactText::getId)
                .max()
                .orElse(0);
    }
}
