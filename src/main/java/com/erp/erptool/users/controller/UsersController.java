package com.erp.erptool.users.controller;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.erptool.setup.APIReturnModel;
import com.erp.erptool.users.model.UsersModel;
import com.erp.erptool.users.services.UserService;

    @RestController
    @RequestMapping("/user")
    public class UsersController {

        @Autowired
        private UserService userService;

        private APIReturnModel apiReturnModel;
        private Vector<UsersModel> userVec;

        @PostMapping("/")
        public ResponseEntity<?> createUser(@RequestBody UsersModel userModel){
            apiReturnModel = new APIReturnModel();
            userVec = new Vector<>();

            try {
                UsersModel user = this.userService.createUser(userModel);
                userVec.add(user);
                apiReturnModel.setData(userVec);
                apiReturnModel.setStatus("Success");
                apiReturnModel.setMessage("User Created Successfully !");
                apiReturnModel.setCount(userVec.size());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                apiReturnModel.setStatus("Fail");
                apiReturnModel.setMessage(" Send the correct  User Data");
                apiReturnModel.setCount(0);
            }

            return ResponseEntity.ok(apiReturnModel);
        }

        @PutMapping("/")
        public ResponseEntity<?> updateUser(@RequestBody UsersModel usersModel) {
            apiReturnModel = new APIReturnModel();
            userVec = new Vector<>();
            //String msg = "";

            try {
                UsersModel user = this.userService.updateUser(usersModel);
                userVec.add(user);
                apiReturnModel.setData(userVec);
                apiReturnModel.setStatus("Success");
                apiReturnModel.setMessage("User updated successfully");
                apiReturnModel.setCount(userVec.size());


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                apiReturnModel.setStatus("fail");
                apiReturnModel.setMessage("Something went Wrong !!");
                apiReturnModel.setCount(0);

            }


            return ResponseEntity.ok(apiReturnModel);

        }
        @GetMapping("/")
        public ResponseEntity<?> getAllUsers(){
            apiReturnModel = new APIReturnModel();

            try {
                List<UsersModel> allUsers = this.userService.getAllUsers();
                userVec = new Vector<>(allUsers);
                apiReturnModel.setStatus("Success");
                apiReturnModel.setMessage("All users");
                apiReturnModel.setCount(userVec.size());
                apiReturnModel.setData(userVec);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                apiReturnModel.setStatus("fail");
                apiReturnModel.setMessage("Something went Wrong !!");
                apiReturnModel.setCount(0);

            }

            return ResponseEntity.ok(apiReturnModel);
        }

//	@GetMapping("/{userId}")
//	public ResponseEntity<?> getUserById(@RequestParam("userId") int userId){
//		apiReturnModel = new APIReturnModel();
//		userVec = new Vector<>();
//		System.out.println("userId "+userId);
//
//		try {
//			UsersModel user = this.userService.getUserById(userId);
//			userVec.add(user);
//			apiReturnModel.setData(userVec);
//			apiReturnModel.setStatus("Success");
//			apiReturnModel.setMessage("User Data ");
//			apiReturnModel.setCount(userVec.size());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			apiReturnModel.setStatus("Fail");
//			apiReturnModel.setMessage(" Something Went Wrong !");
//			apiReturnModel.setCount(0);
//		}
//		return ResponseEntity.ok(apiReturnModel);
//
//	}

        @GetMapping("/{userId}")
        public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
            apiReturnModel = new APIReturnModel();

            try {
                UsersModel user = this.userService.getUserById(userId);
                userVec = new Vector<>();
                userVec.add(user);
                apiReturnModel.setStatus("Success");
                apiReturnModel.setMessage("User with user Id");
                apiReturnModel.setCount(userVec.size());
                apiReturnModel.setData(userVec);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                apiReturnModel.setStatus("fail");
                apiReturnModel.setMessage("Something went Wrong !!");
                apiReturnModel.setCount(0);
            }

            return ResponseEntity.ok(apiReturnModel);
        }

//	@GetMapping("/{userName}")
//	public ResponseEntity<?> getUserByName(@PathVariable("userName") String userName){
//		apiReturnModel = new APIReturnModel();
//
//		try {
//			UsersModel user = this.userService.getUserByName(userName);
//			userVec = new Vector<>();
//			userVec.add(user);
//			apiReturnModel.setStatus("Success");
//			apiReturnModel.setMessage("User with user Id");
//			apiReturnModel.setCount(userVec.size());
//			apiReturnModel.setData(userVec);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			apiReturnModel.setStatus("fail");
//			apiReturnModel.setMessage("Something went Wrong !!");
//			apiReturnModel.setCount(0);
//		}
//
//		return ResponseEntity.ok(apiReturnModel);
//	}

    }

