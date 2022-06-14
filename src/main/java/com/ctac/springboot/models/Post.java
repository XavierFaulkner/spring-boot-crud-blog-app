package com.ctac.springboot.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.GenerationType;


@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author; //= new User("","","","",  null);

    @Column(nullable = false)
    private Date date = new Date();

    public Post(){}

    public Post(long id, String title, String content, User author){
        this.id  = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post(String title, String content, User author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return User return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
     public String toString() {
         return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author=" + author +
            ", date=" + date +
            '}';
    }

}
