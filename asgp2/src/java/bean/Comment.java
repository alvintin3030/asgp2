/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;

public class Comment {
    private int commentID;
    private int toyID;
    private int subComment; 
    private String username;
    private String content;
    private Date datetime;
    private int isMainComment;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getToyID() {
        return toyID;
    }

    public void setToyID(int toyID) {
        this.toyID = toyID;
    }

    public int getSubComment() {
        return subComment;
    }

    public void setSubComment(int subComment) {
        this.subComment = subComment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getDatetime(){
        return datetime;
    }
    
    public void setDatetime(Date datetime){
        this.datetime = datetime;
    }

    public int getIsMainComment() {
        return isMainComment;
    }

    public void setIsMainComment(int isMainComment) {
        this.isMainComment = isMainComment;
    }
    
}
