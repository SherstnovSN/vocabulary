package domain;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @Column(name = "source")
    private String source;

    @Column(name = "translation")
    private String translation;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id", referencedColumnName = "id")
    private Vocabulary vocabulary;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
}
