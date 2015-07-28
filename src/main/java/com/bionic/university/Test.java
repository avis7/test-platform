package com.bionic.university;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author bogdan.ponomarchuk
 * @since 02-Jul-15
 */
public class Test {
    public static void main(String[] args) {
        String email ="grifan91@mail.ru";
        String test ="username";
        System.out.println(test.matches("^[\\w]{4,15}$"));
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        System.out.println(result);
    }
    }

