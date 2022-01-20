package Inlämningsuppgift1;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){
        return (int) questions
                .stream()
                .count();
    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){
        return (int) questions
                .stream()
                .filter(x -> x.getCategory() == category)
                .count();
    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){
        return questions
                .stream()
                .map(Question::getQuestionString)
                .toList();
    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        return questions
                .stream()
                .filter(x -> x.getCategory() == category)
                .map(Question::getQuestionString)
                .toList();
    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){
        return questions
                .stream()
                .map(Question::getAllAnswers)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){
        return questions
                .stream()
                .map(Question::getAllAnswers)
                .flatMap(List::stream)
                .anyMatch(x -> x.equals(answerCandidate));
    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){
        return (int) questions
                .stream()
                .map(Question::getAllAnswers)
                .flatMap(List::stream)
                .filter(x -> x.equals(answerCandidate))
                .count();
    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){
        return questions
                .stream()
                .map(Question::getCategory)
                .distinct()
                .collect(Collectors.toMap(
                        x -> x,
                        this::getAllQuestionStringsBelongingACategory));
    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c) {
        List<String> questionsForCategory = questions
                .stream()
                .filter(x -> x.getCategory() == c)
                .map(Question::getAllAnswers)
                .flatMap(List::stream)
                .distinct()
                .toList();

        return questionsForCategory
                .stream()
                .reduce((questionOne, questionTwo) -> questionOne.length() >= questionTwo.length() ? questionOne : questionTwo)
                .map(Objects::toString)
                .orElse(null);
    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();

    }

}