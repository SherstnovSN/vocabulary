package controller;

import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PositionService;
import service.VocabularyService;

import java.util.List;

@Controller
public class VocabularyController {

    private VocabularyService vocabularyService;

    private PositionService positionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<Vocabulary> vocabularies = vocabularyService.getAll();
        model.addAttribute("vocabularies", vocabularies);
        return "home";
    }

    @RequestMapping(value = "/addPosition", method = RequestMethod.GET)
    public String addPositionToVocabularyPage(Model model) {
        List<Vocabulary> vocabularies = vocabularyService.getAll();
        model.addAttribute("vocabularies", vocabularies);
        return "addPosition";
    }

    @RequestMapping(value = "/addPosition", method = RequestMethod.POST)
    public String addPositionToVocabulary(@RequestParam(value = "source") String source,
                                          @RequestParam(value = "translations") String[] translations,
                                          @RequestParam(value = "vocabulary") int vocabularyId) {
        Vocabulary vocabulary = vocabularyService.getById(vocabularyId);
        positionService.addPosition(source, translations, vocabulary);
        return "redirect:/";
    }

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String showTranslationSearchPage(Model model) {
        List<Vocabulary> vocabularies = vocabularyService.getAll();
        model.addAttribute("vocabularies", vocabularies);
        return "translate";
    }

    @RequestMapping(value = "/translation", method = RequestMethod.POST)
    public String showTranslationPage(@RequestParam(value = "search") String search,
                                      @RequestParam(value = "source") String source,
                                      @RequestParam(value = "vocabulary") int vocabularyId,
                                      Model model) {
        if (vocabularyId == 0) model.addAttribute("positions", positionService.getFromAllVocabularies(search, source));
        else model.addAttribute("positions", positionService.getFromVocabulary(search, source, vocabularyService.getById(vocabularyId)));
        return "translation";
    }

    @RequestMapping(value = "/editPosition/{positionId}", method = RequestMethod.GET)
    public String showEditPositionPage(@PathVariable String positionId, Model model) {
        model.addAttribute("position", positionService.getPositionById(Integer.parseInt(positionId)));
        return "editPosition";
    }

    @RequestMapping(value = "/addTranslation/{positionId}", method = RequestMethod.GET)
    public String showAddTranslationPage(@PathVariable String positionId, Model model) {
        model.addAttribute("position", positionService.getPositionById(Integer.parseInt(positionId)));
        return "addTranslation";
    }

    @RequestMapping(value = "/addTranslation", method = RequestMethod.POST)
    public String addTranslation(@RequestParam(value = "positionId") int positionId,
                                 @RequestParam(value = "translations") String[] translations) {
        positionService.addTranslation(positionId, translations);
        return "redirect:/editPosition/" + positionId;
    }

    @RequestMapping(value = "/deletePosition", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePosition(@RequestParam(value = "positionId") String positionId) {
        positionService.deletePosition(Integer.parseInt(positionId));
    }

    @RequestMapping(value = "/deleteTranslation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTranslation(@RequestParam(value = "id") String translationId) {
        positionService.deleteTranslation(Integer.parseInt(translationId));
    }

    @Autowired
    public void setVocabularyService(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

}