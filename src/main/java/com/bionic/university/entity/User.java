package com.bionic.university.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "getUserByEmail",
                query = "SELECT * FROM user u WHERE u.email = :login",
                resultClass = User.class),
        @NamedNativeQuery(name = "getUserRole",
                query = "SELECT * FROM user u WHERE u.role_id = 3",
                resultClass = User.class)
})
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
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "result", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Test> tests;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Result> results;

    public User() {
    }

    public User(String firstName, String lastName, String email, Date birthday, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
