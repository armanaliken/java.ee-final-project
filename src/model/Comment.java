package model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String comment;
    private LocalDateTime postDate;
    private User user;
    private Item item;

    public Comment(){}

    public Comment(Long id, String comment, LocalDateTime postDate, User user, Item item) {
        this.id = id;
        this.comment = comment;
        this.postDate = postDate;
        this.user = user;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
