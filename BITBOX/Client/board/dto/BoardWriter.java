public class BoardWriter{
    private String foodType;
    private String title;
    private String author;
    private String location;
    private String content;
    // 필요에 따라 다른 속성들도 추가할 수 있습니다.

    // 생성자, 게터(getter), 세터(setter) 등을 추가할 수 있습니다.
    
    public PostDTO(String foodType, String title, String author, String location, String content) {
        this.foodType = foodType;
        this.title = title;
        this.author = author;
        this.location = location;
        this.content = content;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
