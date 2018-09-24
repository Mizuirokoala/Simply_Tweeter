package pl.mizuirokoala.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotBlank
    @Size(max = 160)
    @Column(columnDefinition = "TEXT")
    private String messageText;
    @Column(updatable = false)
    @CreationTimestamp
    // private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat
    private Date created;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private boolean ifRead;

    public Message() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");
        String dateString = sdf.format(created);
        return dateString;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public boolean isIfRead() {
        return ifRead;
    }

    public void setIfRead(boolean ifRead) {
        this.ifRead = ifRead;
    }

    public Message(long id, String messageText, Date created, User sender, User receiver, boolean ifRead) {
        super();
        this.id = id;
        this.messageText = messageText;
        this.created = created;
        this.sender = sender;
        this.receiver = receiver;
        this.ifRead = ifRead;
    }

    @Override
    public String toString() {
        return String.format("Message [id=%s, messageText=%s, created=%s, sender=%s, receiver=%s, ifRead=%s]", id,
                messageText, created, sender, receiver, ifRead);
    }

}
