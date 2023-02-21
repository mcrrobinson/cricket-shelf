/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Squash
 */
@Named(value = "login")
@RequestScoped
public class login {
    
    private String name;

    /**
     * Creates a new instance of login
     */
    public login() {
        this.name = "Hi";
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void getUsername(){
        Users user = new Users(0);
        try {
            System.out.println(user.getEmail());
        } catch(Exception e) {
            System.out.println("Doesn't exist...");
        }
        
    }
    
}
