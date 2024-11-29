package API;

import java.util.Collection;

public class ProjectDTO {
	private String title;
    private String date;
    private String place;
    private String imageUrl;

    public ProjectDTO(String title, String date, String place, String imageUrl) {
        this.title = title;
        this.date = date;
        this.place = place;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "MusicalDTO{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

 // JSON 변환 메서드
    public org.json.JSONObject toJson() {
        org.json.JSONObject json = new org.json.JSONObject();
        json.put("title", title);
        json.put("date", date);
        json.put("place", place);
        json.put("imageUrl", imageUrl);
        return json;
    }
}

