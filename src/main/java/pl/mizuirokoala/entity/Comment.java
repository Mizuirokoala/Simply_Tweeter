package pl.mizuirokoala.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotBlank
    @Size(max = 400)
    @Column(columnDefinition = "TEXT")
    private String commentText;
    @Column(updatable = false)
    @CreationTimestamp
    // private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm")
    private Date created;
    @ManyToOne
    private User user;
    @ManyToOne
    private Tweet tweet;

    public Comment(Comment comment) {

    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {
        return String.format("Comment [id=%s, commentText=%s, created=%s, user=%s, tweet=%s]", id, commentText, created,
                user, tweet);
    }

}
