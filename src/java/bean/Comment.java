/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
    private int commentID;
    private int toyID;
    private int subComment; 
    private String username;
    private String content;
    private String datetime;
    private int isSubComment;

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
    
    public String getDatetime(){
        return datetime;
    }
    
    public void setDatetime(String datetime){
        this.datetime = datetime;
    }

    public int getIsSubComment() {
        return isSubComment;
    }

    public void setIsSubComment(int isSubComment) {
        this.isSubComment = isSubComment;
    }
    
}