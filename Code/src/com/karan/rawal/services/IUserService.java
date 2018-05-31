package com.karan.rawal.services;

import java.io.IOException;

import com.karan.rawal.models.User;

public interface IUserService {
	void createUser(User user) throws IOException, Exception;
}
