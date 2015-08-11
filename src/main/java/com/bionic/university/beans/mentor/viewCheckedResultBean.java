package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.UserAnswer;
import com.bionic.university.services.AnswerService;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.UserAnswerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olexandr on 8/9/2015.
 */
@ViewScoped
@ManagedBean
public class viewCheckedResultBean {
    private List<RadioQuestion> radioQuestions;
    private List<MultichoiseQuestion> multichoiseQuestions;
    private List<OpenQuestion> openQuestions;

    @Inject
    private AnswerService answerService;
    @Inject
    private UserAnswerService userAnswerService;
    @Inject
    private QuestionService questionService;


    private String resultId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("resultId");

    @PostConstruct
    public void init() {
        radioQuestions = new ArrayList<RadioQuestion>();
        multichoiseQuestions = new ArrayList<MultichoiseQuestion>();
        openQuestions = new ArrayList<OpenQuestion>();

        List<UserAnswer> userAnswers = userAnswerService.getUserAnswersByResultId(resultId);
        for (UserAnswer userAnswer : userAnswers){
            Question question = questionService.getQuestionByAnswerId(userAnswer.getAnswerId());
            Answer answer = answerService.getAnswerById(userAnswer.getAnswerId());
            if (!question.getIsMultichoise() && !question.getIsOpen()){
                RadioQuestion radioQuestion = new RadioQuestion();
                radioQuestion.setQuestion(question);
                radioQuestion.setAnswer(answer.getAnswerText());
                radioQuestion.setMark(userAnswer.getMark());
                radioQuestions.add(radioQuestion);
            }
            if (question.getIsMultichoise()){
                MultichoiseQuestion multichoiseQuestion = new MultichoiseQuestion();
                multichoiseQuestion.setQuestion(question);
                if (multichoiseQuestions.contains(multichoiseQuestion)){
                    multichoiseQuestion = multichoiseQuestions.get(multichoiseQuestions.indexOf(multichoiseQuestion));
                }else {
                    multichoiseQuestions.add(multichoiseQuestion);
                }
                multichoiseQuestion.addAnswer(answer.getAnswerText());
                multichoiseQuestion.setMark(userAnswer.getMark());

            }
            if (question.getIsOpen()){
                OpenQuestion openQuestion = new OpenQuestion();
                openQuestion.setQuestion(question);
                openQuestion.setAnswer(userAnswer.getOwnAnswer());
                openQuestion.setMark(userAnswer.getMark());
                openQuestions.add(openQuestion);
            }
        }

    }


    public List<RadioQuestion> getRadioQuestions() {
        return radioQuestions;
    }

    public void setRadioQuestions(List<RadioQuestion> radioQuestions) {
        this.radioQuestions = radioQuestions;
    }

    public List<MultichoiseQuestion> getMultichoiseQuestions() {
        return multichoiseQuestions;
    }

    public void setMultichoiseQuestions(List<MultichoiseQuestion> multichoiseQuestions) {
        this.multichoiseQuestions = multichoiseQuestions;
    }

    public List<OpenQuestion> getOpenQuestions() {
        return openQuestions;
    }

    public void setOpenQuestions(List<OpenQuestion> openQuestions) {
        this.openQuestions = openQuestions;
    }

    public class RadioQuestion{
        private Question question;
        private String answer;
        private int mark;

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }
    }
    public class MultichoiseQuestion{
        private Question question;
        private List<String> answers = new ArrayList<String>();
        private int mark;


        public void addAnswer(String answer){
            answers.add(answer);
        }


        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            MultichoiseQuestion multichoiseQuestion = (MultichoiseQuestion) obj;
            return this.question.equals(multichoiseQuestion.getQuestion());
        }
    }
    public class OpenQuestion{
        private Question question;
        private String answer;
        private int mark;


        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }
    }
}
