package com.erp.erptool.users.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tool.erp.role.models.UserRolesModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class UsersModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    @NotBlank(message="User Name should not be Null")
    @Column(unique= true, nullable=false,updatable = false)
    private String userName;

    @Column(nullable=false, updatable=false)
    @NotBlank(message="Firat Name should not be null")
    @Size(max=20, min=5, message="Name should contain min 5 charecter and maxium of 20")
    private String firstName;

    private String middleName;
    private String lastName;

    @NotBlank(message= "Password should not be Null")
    private String password;

    @Email(message= "Email is not valid")
    private String email;

    private String phoneNo;

    @CreationTimestamp
    @Column(nullable= false, updatable= false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @UpdateTimestamp
    @Column(nullable= false, updatable= true)
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    private int modifiedBy;

    private int createdBy;
    private boolean status = true;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserRolesModel user;





}