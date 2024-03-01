package model;

import java.time.LocalDateTime;

public class Item {
    private Long id;
    private LocalDateTime postDate;
    private ItemCategory itemCategory;
    private String title;
    private String content;

    public Item(){}
    public Item(Long id, LocalDateTime postDate, ItemCategory itemCategory, String title, String content) {
        this.id = id;
        this.postDate = postDate;
        this.itemCategory = itemCategory;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

