package educationPlanManager;

public class Student {

    private double learningCoefficient;
    private Knowledge knowledge;

    private boolean hasLaptop;

    public Student(double learningCoefficient, Knowledge knowledge) {
        this.learningCoefficient = learningCoefficient;
        this.knowledge = knowledge;
    }

    void addKnowledge(double practical, double theoretical) {
        knowledge.addPractical(practical * learningCoefficient);
        knowledge.addTheoretical(theoretical * learningCoefficient);
    }

    Knowledge getKnowledge() {
        return knowledge;
    }

    public void setHavingLaptop(boolean havingLaptop) {
        hasLaptop = havingLaptop;
    }

    public boolean hasLaptop() {
        return hasLaptop;
    }
}
