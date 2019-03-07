package educationPlanManager;

import java.time.LocalDate;

public class OneDayPerMonthTimeStrategy implements TimeFrame {

    private LocalDate targetDate;

    public OneDayPerMonthTimeStrategy(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public boolean checkForThisDay(LocalDate currentDate) {
        return currentDate.getDayOfMonth() == targetDate.getDayOfMonth();
    }
}
