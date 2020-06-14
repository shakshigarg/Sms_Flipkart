package com.flipcard.service;

import com.flipcard.exception.UserAlreadyExist;
import com.flipcard.model.Admin;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;

public interface AdminServiceInterface {


	void createStudent(Student s,String password);

	void createProfessor(Professor p, String password);
	
	void createAdmin(Admin a, String password);

	void checkUsername(String userName) throws UserAlreadyExist;

}
