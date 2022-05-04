package NewPost;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dolya
 */
import java.util.ArrayList;

public class User {
    
    private String name;
    private ArrayList<UserPost> userPostList;
    
    public User(){
        name = "unknown";
        userPostList.clear();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public UserPost getUserPost(int x){
        return userPostList.get(x);
    }
    
    public ArrayList<UserPost> getUserPostList(){
        return userPostList;
    }
    
    public void addUserPost(UserPost newUserPost){
        this.userPostList.add(newUserPost);
    }
}
