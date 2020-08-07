package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "source_regex")
    private String sourceRegex;

    @Column(name = "translation_regex")
    private String translationRegex;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vocabulary")
    private Set<Position> positions = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceRegex() {
        return sourceRegex;
    }

    public void setSourceRegex(String sourceRegex) {
        this.sourceRegex = sourceRegex;
    }

    public String getTranslationRegex() {
        return translationRegex;
    }

    public void setTranslationRegex(String translationRegex) {
        this.translationRegex = translationRegex;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

}
