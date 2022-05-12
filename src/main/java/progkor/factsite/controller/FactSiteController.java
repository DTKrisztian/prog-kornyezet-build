package progkor.factsite.controller;

import java.awt.*;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progkor.factsite.model.FactText;
import progkor.factsite.model.exception.NotFoundException;
import progkor.factsite.service.FactSiteService;

@Controller
@RequestMapping("/fact")
public class FactSiteController {

    private final FactSiteService factSiteService;

    public FactSiteController(FactSiteService factSiteService) {
        this.factSiteService = factSiteService;
    }

    @GetMapping
    public String getAllFactText(final Model model) {
        final List<FactText> factTexts = factSiteService.getAllFactTexts();
        model.addAttribute("factTexts", factTexts);
        return "factsite/list";
    }

    @GetMapping("/{id}")
    public String getFactText(final Model model, @PathVariable final Long id) {
        final FactText factText = factSiteService.getFactText(id);
        model.addAttribute("factText", factText);
        return "factsite/edit";
    }

    @GetMapping("/create")
    public String createFactTextForm(final Model model) {
        return "factsite/create";
    }

    @PostMapping("/create")
    public String createFactText(final Model model, final FactText factText) {
        final FactText savedFactText = factSiteService.createFactText(factText);
        model.addAttribute("factText", savedFactText);
        return "factsite/create";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createFactText(final Model model,
                                 final @RequestParam(value = "id", required = false) Long id,
                                 final FactText factTextChanges) {
        final FactText factText = factSiteService.updateFactText(id, factTextChanges);
        model.addAttribute("factText", factText);
        return "factsite/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteFactText(final Model model, @PathVariable("id") final Long id) {
        try {
            factSiteService.deleteFactText(id);
        } catch (NotFoundException e) {
            //nothing
        }
        final List<FactText> factTexts = factSiteService.getAllFactTexts();
        model.addAttribute("factTexts", factTexts);
        return "factsite/list";
    }

}
