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
            boolean isInThisDay;
            for (Activity activity : activities) {
                isInThisDay = true;

                for (TimeFrame frame : activity.getTimeFrames()) {
                    if (!frame.checkForThisDay(currentDate)) {
                        isInThisDay = false;
                    }
                }

                if (isInThisDay) {
                    activity.teach(student);
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        return student.getKnowledge();
    }
}
