import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class VetoDates implements DateVetoPolicy {

    @Override
    public boolean isDateAllowed(LocalDate localDate) {
        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                localDate.getDayOfWeek() == DayOfWeek.SUNDAY ||
                localDate.isBefore(LocalDate.now()))
            return false;
        return true;
    }
}
