package com.flipcard.DAO;

import java.util.List;

import com.flipcard.model.Admin;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;

public interface AdminDaoInterface {
	void createStudent(Student s,String password);

	void createProfessor(Professor p, String password);

	void createAdmin(Admin a, String password);

	boolean checkUsername(String userName);

	List<String> getUsersWithRole(String role);

	boolean deleteUser(String username, String role);

	void deleteSelfAccount(String username);

}
