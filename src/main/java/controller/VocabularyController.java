package controller;

import domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.VocabularyService;
import validator.Validator;

import java.util.List;

@Controller
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showSelectPage() {
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPositionToVocabularyPage() {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPositionToVocabulary(@RequestParam(value = "source") String source,
                                          @RequestParam(value = "translation") String translation) {
        vocabularyService.add(source, translation);
        return "redirect:/";
    }

    @RequestMapping(value = "/vocabulary", method = RequestMethod.GET)
    public String showVocabulary(Model model) {
        List<Position> vocabulary = vocabularyService.getAll();
        model.addAttribute("vocabulary", vocabulary);
        return "vocabulary";
    }

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String showTranslationPage() {
        return "translate";
    }

    @RequestMapping(value = "/translation", method = RequestMethod.POST)
    public String showTranslationPage(@RequestParam(value = "source") String source, Model model) {
        model.addAttribute("position", vocabularyService.get(source));
        return "translation";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePositionPage() {
        return "delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePosition(@RequestParam(value = "source") String source) {
        vocabularyService.delete(source);
        return "redirect:/";
    }

}
