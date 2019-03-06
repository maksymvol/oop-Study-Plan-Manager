package educationPlanManager;

public class Knowledge {
    private double theoretical;
    private double practical;

    public Knowledge(double theoretical, double practical) {
        this.theoretical = theoretical;
        this.practical = practical;
    }

    public void addTheoretical(double theoretical) {
        this.theoretical += theoretical;
    }

    public void addPractical(double practical) {
        this.practical += practical;
    }

    public double getTheoretical() {
        return theoretical;
    }

    public double getPractical() {
        return practical;
    }
}
