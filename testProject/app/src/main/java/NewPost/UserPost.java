package NewPost;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dolya
 */
public class UserPost {
    
    private String title;
    private String type;
    private String status;
    private int like;
    private String[] comment;
    
    public UserPost(){
        title = "unknown";
        type = "unknown";
        status = "unknown";
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String newType){
        this.type = newType;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String newStatus){
        this.status = newStatus;
    }
}
