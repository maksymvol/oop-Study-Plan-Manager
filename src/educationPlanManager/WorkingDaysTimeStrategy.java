package educationPlanManager;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkingDaysTimeStrategy implements TimeFrame {

    @Override
    public boolean checkForThisDay(LocalDate currentDate) {
        return currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY
                && currentDate.getMonth().getValue() < 6 || currentDate.getMonth().getValue() >= 9;
    }
}
