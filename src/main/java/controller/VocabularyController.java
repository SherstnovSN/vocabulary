package controller;

import domain.Position;
import domain.VocabularyHolder;
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
    private VocabularyHolder vocabularyHolder;

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("vocabularies", vocabularyService.getAll());
        return "home";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menuPage() {
        return "menu";
    }

    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    public String setVocabularyMenuPage(@PathVariable(value = "id") int id) {
        vocabularyHolder.setVocabulary(vocabularyService.getById(id));
        return "menu";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPositionToVocabularyPage() {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPositionToVocabulary(@RequestParam(value = "source") String source,
                                          @RequestParam(value = "translation") String translation) {
        positionService.add(source, translation);
        return "redirect:/menu";
    }

    @RequestMapping(value = "/vocabulary", method = RequestMethod.GET)
    public String showVocabulary(Model model) {
        List<Position> vocabulary = positionService.getAll();
        model.addAttribute("vocabulary", vocabulary);
        return "vocabulary";
    }

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String showTranslationPage() {
        return "translate";
    }

    @RequestMapping(value = "/translation", method = RequestMethod.POST)
    public String showTranslationPage(@RequestParam(value = "source") String source, Model model) {
        Position position = positionService.get(source);
        if (position == null) {
            position = new Position();
            position.setSource("отсутствует");
            position.setTranslation("отсутствует");
        }
        model.addAttribute("position", position);
        return "translation";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePositionPage() {
        return "delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePosition(@RequestParam(value = "source") String source) {
        positionService.delete(source);
        return "redirect:/menu";
    }

}
