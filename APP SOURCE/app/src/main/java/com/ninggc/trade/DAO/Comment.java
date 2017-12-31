package com.ninggc.trade.DAO;

import com.ninggc.trade.util.constants.Constant;
import com.ninggc.trade.util.tool.IGson;

/**
 * Created by Ning on 12/27/2017 0027.
 */

public class Comment implements IBean {
    private int id;
    private String releaseUsername;
    private String image;
    private String content;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReleaseUsername() {
        return releaseUsername;
    }

    public void setReleaseUsername(String releaseUsername) {
        this.releaseUsername = releaseUsername;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (releaseUsername != null ? !releaseUsername.equals(comment.releaseUsername) : comment.releaseUsername != null)
            return false;
        if (image != null ? !image.equals(comment.image) : comment.image != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null)
            return false;
        return time != null ? time.equals(comment.time) : comment.time == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (releaseUsername != null ? releaseUsername.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private static int count = 0;
    public static Comment getTestInstance() {
        Comment comment = new Comment();
        comment.setContent(String.valueOf(count++));
        comment.setImage(Constant.image1);
        comment.setId(count);
        comment.setReleaseUsername("name");
        return comment;
    }
}
