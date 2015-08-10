package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.dao.QuestionDAO;
import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.entity.Test;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.inject.Inject;
import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestParserService {
    private static final String TAG_QUESTION = "question";
    private static final String TAG_QUESTIONTEXT = "questiontext";
    private static final String TAG_ANSWER = "answer";

    private static final String QUESTION_NAME = "name";
    private static final String QUESTION_DEADLINE = "deadline";
    private static final String QUESTION_DURATION = "duration";
    private static final String QEUSTION_CATEGORY = "category";

    private static final String ATTR_QUESTION_TYPE = "type";
    private static final String ATTR_ANSWER_MARK = "fraction";

    private String xmlTestFile;
    private String parsedTestName;
    private List<Question> questions;

    @Inject
    QuestionDAO questionDAO;

    @Inject
    AnswerDAO answerDAO;

    @Inject
    TestDAO testDAO;

    public void initialize(String xmlTestFile) {
        this.xmlTestFile = xmlTestFile;
        questions = new ArrayList<Question>();
    }

    public void parseTestFile() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(xmlTestFile));

            Document doc = dBuilder.parse(source);
            doc.getDocumentElement().normalize();

            NodeList questionNodes = doc.getElementsByTagName(TAG_QUESTION);
            String testName = doc.getDocumentElement().getAttribute(QUESTION_NAME);
            String testDeadline = doc.getDocumentElement().getAttribute(QUESTION_DEADLINE);
            String testDuration = doc.getDocumentElement().getAttribute(QUESTION_DURATION);
            String testCategory = doc.getDocumentElement().getAttribute(QEUSTION_CATEGORY);

            parsedTestName = testName;

            SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

            Test test = new Test(testName, Integer.parseInt(testDuration), sdf.parse(testDeadline), testCategory);
            testDAO.save(test);

            for (int index = 0; index < questionNodes.getLength(); index++) {
                Node node = questionNodes.item(index);

                Element element = (Element) node;

                int correctAnswersCounter = 0;
                String questionName = element.getElementsByTagName(TAG_QUESTIONTEXT)
                        .item(0)
                        .getTextContent();
                boolean questionIsOpen = (element.getAttribute(ATTR_QUESTION_TYPE).equals("shortAnswer"));
                int questionMark = 0;

                questions.add(new Question(questionName, questionMark, questionIsOpen, false));
                questions.get(index).setTest(test);
                questionDAO.save(questions.get(index));

                List<Answer> answersToCurrentQuestion = new ArrayList<Answer>();

                for (int answerIndex = 0; answerIndex < element.getElementsByTagName("answer").getLength(); answerIndex++) {

                    String answerText = element.getElementsByTagName(TAG_ANSWER).item(answerIndex).getTextContent();
                    boolean isCorrect =
                            Integer.parseInt(element.getElementsByTagName(TAG_ANSWER)
                                    .item(answerIndex)
                                    .getAttributes().getNamedItem(ATTR_ANSWER_MARK)
                                    .getNodeValue()) > 0;

                    Answer answer = new Answer(answerText, isCorrect);
                    answer.setQuestion(questions.get(index));

                    if (isCorrect) {
                        questionMark = Integer.parseInt(element.getElementsByTagName(TAG_ANSWER)
                            .item(answerIndex)
                            .getAttributes().getNamedItem(ATTR_ANSWER_MARK)
                            .getNodeValue());
                        correctAnswersCounter++;
                    }

                    answersToCurrentQuestion.add(answer);
                    answerDAO.save(answersToCurrentQuestion.get(answerIndex));
                }

                boolean isMultiChoice = correctAnswersCounter > 1;

                questions.get(index).setAnswers(answersToCurrentQuestion);
                questions.get(index).setMark(questionMark);
                questions.get(index).setIsMultichoise(isMultiChoice);

                questionDAO.update(questions.get(index));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public boolean containsNecessaryTagsAndAttributes() {
        return xmlTestFile.contains(TAG_QUESTION)
                && xmlTestFile.contains(TAG_QUESTIONTEXT)
                && xmlTestFile.contains(TAG_ANSWER)
                && xmlTestFile.contains(ATTR_ANSWER_MARK)
                && xmlTestFile.contains(ATTR_QUESTION_TYPE)
                && xmlTestFile.contains(QEUSTION_CATEGORY)
                && xmlTestFile.contains(QUESTION_DEADLINE)
                && xmlTestFile.contains(QUESTION_DURATION)
                && xmlTestFile.contains(QUESTION_NAME);
    }
}