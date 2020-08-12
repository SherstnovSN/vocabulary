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

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
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
    public String showTranslationPage(Model model) {
        List<Vocabulary> vocabularies = vocabularyService.getAll();
        model.addAttribute("vocabularies", vocabularies);
        return "translate";
    }

    @RequestMapping(value = "/translation", method = RequestMethod.POST)
    public String showTranslationPage(@RequestParam(value = "source") String source,
                                      @RequestParam(value = "vocabulary") int vocabularyId,
                                      Model model) {
        if (vocabularyId == 0) model.addAttribute("position", positionService.getFromAllVocabularies(source));
        else model.addAttribute("position", positionService.getFromVocabulary(source, vocabularyService.getById(vocabularyId)));
        return "translation";
    }

    @RequestMapping(value = "/editPosition/{source}", method = RequestMethod.GET)
    public String showEditPositionPage(@PathVariable String source, Model model) {
        model.addAttribute("position", positionService.getFromAllVocabularies(source));
        return "editPosition";
    }

    @RequestMapping(value = "/addTranslation/{source}", method = RequestMethod.GET)
    public String showAddTranslationPage(@PathVariable String source, Model model) {
        model.addAttribute("position", positionService.getFromAllVocabularies(source));
        return "addTranslation";
    }

    @RequestMapping(value = "/addTranslation", method = RequestMethod.POST)
    public String addTranslation(@RequestParam(value = "source") String source,
                                 @RequestParam(value = "translations") String[] translations) {
        positionService.addTranslation(source, translations);
        return "redirect:/editPosition/" + source;
    }

    @RequestMapping(value = "/deletePosition", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePosition(@RequestParam(value = "source") String source) {
        positionService.deletePosition(source);
    }

    @RequestMapping(value = "/deleteTranslation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTranslation(@RequestParam(value = "id") String translationId) {
        positionService.deleteTranslation(Integer.parseInt(translationId));
    }

}
