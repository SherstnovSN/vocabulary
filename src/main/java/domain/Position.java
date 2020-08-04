package domain;

import javax.persistence.*;

@Entity
@Table(name = "eng_rus")
public class Position {

    @Id
    @Column(name = "source")
    private String source;

    @Column(name = "translation")
    private String translation;

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
}
