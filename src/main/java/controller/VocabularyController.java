package controller;

import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPositionToVocabularyPage(Model model) {
        List<Vocabulary> vocabularies = vocabularyService.getAll();
        model.addAttribute("vocabularies", vocabularies);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPositionToVocabulary(@RequestParam(value = "source") String source,
                                          @RequestParam(value = "translation") String translation,
                                          @RequestParam(value = "vocabulary") int vocabularyId) {
        Vocabulary vocabulary = vocabularyService.getById(vocabularyId);
        positionService.add(source, translation, vocabulary);
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
        return "translation";
    }

    @RequestMapping(value = "/edit/{source}", method = RequestMethod.GET)
    public String showEditPage(@PathVariable String source, Model model) {
        model.addAttribute("position", positionService.getFromAllVocabularies(source));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPosition(@RequestParam(value = "source") String source,
                               @RequestParam(value = "translation") String translation) {
        positionService.edit(source, translation);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deletePositionPage(@RequestParam(value = "source") String source) {
        positionService.delete(source);
    }

}
