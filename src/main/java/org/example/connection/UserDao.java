package org.example.connection;

import java.util.List;

public interface UserDao {

	void saveUser(Student user);


	Student getUserById(long id);

	List<Student> getAllUsers();

	void updateUser(Student user);

	void deleteUserById(long id);

}