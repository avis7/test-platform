package com.bionic.university.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Olexandr on 8/9/2015.
 */
public class Test {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<Question>();
        questions.add(new Question(11, "1"));
        questions.add(new Question(12, "2"));
        questions.add(new Question(13, "1"));
        questions.add(new Question(14, "1"));
        questions.add(new Question(15, "2"));
        questions.add(new Question(16, "3"));
        questions.add(new Question(17, "4"));


        List<Integer> integers;
        List<Question> results;
        for (int i = 0; i < questions.size(); i++) {
            for (int j = i; j < questions.size(); j++) {

            }
        }

    }

    public static class Question{
        public Question(int number, String question) {
            this.number = number;
            this.question = question;
        }

        int number;
        String question;
    }
}
