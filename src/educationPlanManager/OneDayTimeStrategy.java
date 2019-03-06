package educationPlanManager;

import java.time.LocalDate;

public class OneDayTimeStrategy implements TimeFrame {

    private LocalDate targetDate;

    public OneDayTimeStrategy(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public boolean checkForThisDay(LocalDate currentDate) {
        return currentDate.equals(targetDate);
    }
}
