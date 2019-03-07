package educationPlanManager;

import java.time.LocalDate;

public class IntervalTimeStrategy implements TimeFrame {

    private LocalDate startDate;
    private LocalDate endDate;

    public IntervalTimeStrategy(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean checkForThisDay(LocalDate currentDate) {
        return currentDate.isAfter(startDate) && currentDate.isBefore(endDate)
                || currentDate.isEqual(startDate) || currentDate.isEqual(endDate);
    }
}
