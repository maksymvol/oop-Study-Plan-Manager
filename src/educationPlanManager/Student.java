package educationPlanManager;

public class Student {

    private double learningCoefficient;
    private Knowledge knowledge;

    private boolean hasLaptop;

    public Student(double learningCoefficient, Knowledge knowledge) {
        this.learningCoefficient = learningCoefficient;
        this.knowledge = knowledge;
    }

    public void setLearningCoefficient(double learningCoefficient) {
        this.learningCoefficient = learningCoefficient;
    }

    public void setHavingLaptop(boolean havingLaptop) {
        hasLaptop = havingLaptop;
    }

    void addKnowledge(double practical, double theoretical) {
        knowledge.addPractical(practical * learningCoefficient);
        knowledge.addTheoretical(theoretical * learningCoefficient);
    }

    Knowledge getKnowledge() {
        return knowledge;
    }

    boolean hasLaptop() {
        return hasLaptop;
    }
}
