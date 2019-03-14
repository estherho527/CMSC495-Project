import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;

import java.time.LocalTime;

public class VetoTimes implements TimeVetoPolicy {

    @Override
    public boolean isTimeAllowed(LocalTime time) {

        // Only allow times from 9a to 5p, inclusive.

        return PickerUtilities.isLocalTimeInRange(

                time, LocalTime.of(9, 0), LocalTime.of(16, 30), true);

    }
}
