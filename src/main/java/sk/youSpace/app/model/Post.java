package sk.youSpace.app.model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.format.DateTimeFormatter;

@Document(collection = "posts")
public class Post {
    @Id
    private ObjectId id;
    @Field
    private String title;
    @Field
    private String text;
    @Field
    private String added;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getAdded() {
        return added;
    }

    public void setAdded() {
        var now = java.time.LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var formatDateTime = now.format(formatter);
        this.added = formatDateTime;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}