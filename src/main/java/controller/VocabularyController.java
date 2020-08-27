package controller;

import domain.Language;
import domain.Position;
import ecxeption.InvalidWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.LanguageService;
import service.PositionService;

import java.util.List;

@Controller
public class VocabularyController {

    private LanguageService languageService;

    private PositionService positionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showTranslationSearchPage(Model model) {
        List<Language> languages = languageService.getAll();
        model.addAttribute("languages", languages);
        return "translate";
    }

    @RequestMapping(value = "/translation", method = RequestMethod.POST)
    public String showTranslationPage(@RequestParam(value = "source") String source,
                                      @RequestParam(value = "sourceLanguageId") int sourceLanguageId,
                                      @RequestParam(value = "translationLanguageId") int translationLanguageId,
                                      Model model) {
        model.addAttribute("source", source);
        model.addAttribute("translations", positionService.getTranslations(source, languageService.getById(sourceLanguageId), languageService.getById(translationLanguageId)));
        return "translation";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        List<Language> languages = languageService.getAll();
        model.addAttribute("languages", languages);
        return "admin";
    }

    @RequestMapping(value = "/addPosition", method = RequestMethod.GET)
    public String addPositionToVocabularyPage(Model model) {
        List<Language> languages = languageService.getAll();
        model.addAttribute("languages", languages);
        return "addPosition";
    }

    @RequestMapping(value = "/addPosition", method = RequestMethod.POST)
    public String addPositionToVocabulary(@RequestParam(value = "source") String source,
                                          @RequestParam(value = "translations") String[] translations,
                                          @RequestParam(value = "sourceLanguageId") int sourceLanguageId,
                                          @RequestParam(value = "translationLanguageId") int translationLanguageId,
                                          Model model) {
        Language sourceLanguage = languageService.getById(sourceLanguageId);
        Language translationLanguage = languageService.getById(translationLanguageId);
        Position position = positionService.getPositionBySourceAndLanguage(source, sourceLanguage);
        if (position != null) return "redirect:/positionExists/" + position.getId();
        try {
            positionService.addPosition(source, translations, sourceLanguage, translationLanguage);
        } catch (InvalidWordException iwe) {
            List<Language> languages = languageService.getAll();
            model.addAttribute("languages", languages);
            model.addAttribute("invalidWordMessage", iwe.getMessage());
            return "/addPosition";
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/positionExists/{positionId}", method = RequestMethod.GET)
    public String showPositionExistsPage(@PathVariable int positionId, Model model) {
        model.addAttribute("positionId", positionId);
        return "positionExists";
    }

    @RequestMapping(value = "/editPosition/{positionId}", method = RequestMethod.GET)
    public String showEditPositionPage(@PathVariable int positionId, Model model) {
        model.addAttribute("position", positionService.getPositionById(positionId));
        return "editPosition";
    }

    @RequestMapping(value = "/addTranslation/{positionId}", method = RequestMethod.GET)
    public String showAddTranslationPage(@PathVariable int positionId, Model model) {
        model.addAttribute("position", positionService.getPositionById(positionId));
        model.addAttribute("languages", languageService.getAll());
        return "addTranslation";
    }

    @RequestMapping(value = "/addTranslation", method = RequestMethod.POST)
    public String addTranslation(@RequestParam(value = "positionId") int positionId,
                                 @RequestParam(value = "translations") String[] translations,
                                 @RequestParam(value = "translationLanguageId") int translationLanguageId,
                                 Model model) {
        Language translationLanguage = languageService.getById(translationLanguageId);
        try {
            positionService.addTranslation(positionId, translations, translationLanguage);
        } catch (InvalidWordException iwe) {
            model.addAttribute("position", positionService.getPositionById(positionId));
            model.addAttribute("languages", languageService.getAll());
            model.addAttribute("invalidWordMessage", iwe.getMessage());
            return "addTranslation";
        }
        return "redirect:/editPosition/" + positionId;
    }

    @RequestMapping(value = "/deletePosition", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePosition(@RequestParam(value = "positionId") String positionId) {
        positionService.deletePosition(Integer.parseInt(positionId));
    }

    @RequestMapping(value = "/deleteTranslation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTranslation(@RequestParam(value = "positionId") String positionId,
                                  @RequestParam(value = "translationId") String translationId) {
        positionService.deleteTranslation(Integer.parseInt(positionId), Integer.parseInt(translationId));
    }

    @Autowired
    public void setLanguageService(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

}