package progkor.factsite.service;

import java.util.List;

import progkor.factsite.model.FactText;

public interface FactSiteService {

    List<FactText> getAllFactTexts();

    FactText getFactText(Long id);

    FactText createFactText(FactText factText);

    FactText updateFactText(Long id, FactText factTextChange);

    void deleteFactText(Long id);
}
