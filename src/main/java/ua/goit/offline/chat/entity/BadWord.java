package ua.goit.offline.chat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
@Entity
@Table(name = "bad_words", schema = "chat")
public class BadWord {
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
