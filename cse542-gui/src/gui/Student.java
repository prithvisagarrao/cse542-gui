/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author psrao
 */

public class Student {

	public String userName;
	public String passWord;
	public String email;
	public boolean paused;
	
        public Student(String userName, String passWord, String email) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.paused=false;
		
		
	}
	@Override
	public String toString() {
		return "Student [userName=" + userName + ", passWord=" + passWord + ", email=" + email + ", paused=" + paused
				+ "]";
	}
	
	
} 