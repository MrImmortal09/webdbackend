package com.erp.erptool.users.services;

import java.util.List;



import com.erp.erptool.users.models.UsersModel;
public interface UserService {

    UsersModel createUser(UsersModel userModel);

    UsersModel getUserById(int userId);

    List<UsersModel> getAllUsers();

    UsersModel updateUser(UsersModel usersModel);

    UsersModel getUserByName(String userName);

}