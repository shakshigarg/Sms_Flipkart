package com.flipcard.DAO;

import com.flipcard.model.Admin;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;

public interface AdminDaoInterface {
	void createStudent(Student s,String password);

	void createProfessor(Professor p, String password);

	void createAdmin(Admin a, String password);

	boolean checkUsername(String userName);

}
