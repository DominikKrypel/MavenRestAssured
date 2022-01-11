package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)// - dzięki temu, jak nie podamy w metodzie PUT jakiejś wartości to ona nie będzie miała null
public class Post {

    private Integer id;
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Dzięki poniższym dwóm zapisom "@Override" możemy porównać dwa obiekty w teście "addPostObjectAndCompareTwoObjects"
    //te zapisy generujemy poprzez naciśnięcie przycisków "alt + insert" i wybór opcji "equals() and hashCode()"
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) && Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
