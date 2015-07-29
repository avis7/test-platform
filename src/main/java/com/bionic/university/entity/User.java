package com.bionic.university.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;



@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "result", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "test_id"))
    private Collection<Test> tests;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Result> results;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Date birthday, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Test> getTests() {
        return tests;
    }

    public void setTests(Collection<Test> tests) {
        this.tests = tests;
    }

    public Collection<Result> getResults() {
        return results;
    }

    public void setResults(Collection<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", results=" + results +
                '}';
    }
}