package com.karan.rawal.services;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import com.karan.rawal.models.User;

public class UserService implements IUserService {
	public static final String USER_FILE_PATH = "/home/karan/TodoData/user.dat";
	private static UserService instance;
	
	private UserService() throws IOException {
		createFileIfNotExists();
	}
	
	public static UserService getInstance() throws IOException {
		if(null == instance) {
			instance = new UserService();
		}
		return instance;
	}
	
	@Override
	public void createUser(User user) throws Exception {
		HashSet<User> users;
		try {
			users = readUsers();
			if(users.contains(user)) {
				throw new Exception(User.USER_EXISTS);
			} else {
				putUser(user);
			}
		} catch (IOException e) {
			e.printStackTrace();	
			throw new Exception(User.USER_CREATION_FAILED);
		}
	}
	
	private void putUser(User user) throws IOException {
		Path filePath = Paths.get(USER_FILE_PATH);
		OutputStream fos = Files.newOutputStream(filePath, StandardOpenOption.APPEND);
		try(ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(user);
		}	
		fos.close();
	}

	private HashSet<User> readUsers() throws IOException {
		Path filePath = Paths.get(USER_FILE_PATH);
		
		HashSet<User> users = new HashSet<>();
		InputStream fis = Files.newInputStream(filePath);
		try(ObjectInputStream ois = new ObjectInputStream(fis)) {
			User user = null;
			while((user = (User) ois.readObject()) != null) {
				users.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
		} catch (EOFException e) {
			e.printStackTrace();
		}
		fis.close();
		
		return users;
	}
	
	private void createFileIfNotExists() throws IOException {
		Path filePath = Paths.get(USER_FILE_PATH);
		if(!Files.exists(filePath)) {
			Files.createDirectories(filePath.getParent());
			Files.createFile(filePath);
		}
	}
}
