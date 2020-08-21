package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "translation")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "word")
    private String word;

    @ManyToMany(mappedBy = "translations")
    private List<Position> positions;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
