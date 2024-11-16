package com.identity.identity_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Admin
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank(message ="Name is mandatory")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^\\d{10}$",message = "Phone number must be 10 digits")
    private String phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    public @Email(message = "Invalid email format") @NotBlank(message = "Email is mandatory") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email format") @NotBlank(message = "Email is mandatory") String email) {
        this.email = email;
    }

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits") String phone) {
        this.phone = phone;
    }


}
