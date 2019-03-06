package educationPlanManager;

import java.time.LocalDate;

public class EveryDayTimeStrategy implements TimeFrame {
    @Override
    public boolean checkForThisDay(LocalDate currentDate) {
        return true;
    }
}
