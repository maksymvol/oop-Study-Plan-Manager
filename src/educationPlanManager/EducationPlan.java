package educationPlanManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class EducationPlan {
    private ArrayList<Activity> activities;
    private LocalDate expirationDate;

    public EducationPlan(ArrayList<Activity> activities, LocalDate expirationDate) {
        this.activities = activities;
        this.expirationDate = expirationDate;
    }

    public Knowledge apply(Student student, LocalDate currentDate) {
        while (currentDate.isBefore(expirationDate) || currentDate.equals(expirationDate)) {
            for (Activity activity : activities) {
                activity.teach(student);
            }
            currentDate = currentDate.plusDays(1);
        }
        return student.getKnowledge();
    }
}
