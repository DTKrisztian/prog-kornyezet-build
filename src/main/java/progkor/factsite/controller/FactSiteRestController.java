package progkor.factsite.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progkor.factsite.model.FactText;
import progkor.factsite.service.FactSiteService;

@RestController
@RequestMapping("/api/version1/fact-site")
public class FactSiteRestController {

    private final FactSiteService factSiteService;

    public FactSiteRestController(final FactSiteService factSiteService) {
        this.factSiteService = factSiteService;
    }

    @GetMapping
    List<FactText> getAllFactTexts() {
        return factSiteService.getAllFactTexts();
    }

    @GetMapping("/{id}")
    FactText getFactText(@PathVariable("id") final Long id) {
        return factSiteService.getFactText(id);
    }

    @PostMapping
    FactText createFactText(@RequestBody final FactText factText) {
        return factSiteService.createFactText(factText);
    }

    @PutMapping("/{id}")
    FactText updateFactText(@PathVariable final Long id, @RequestBody final FactText factTextChange) {
        return factSiteService.updateFactText(id, factTextChange);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFactText(@PathVariable final Long id) {
        factSiteService.deleteFactText(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
