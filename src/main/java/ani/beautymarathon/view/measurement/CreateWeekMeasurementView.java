package ani.beautymarathon.view.measurement;

import java.time.LocalDate;

public record CreateWeekMeasurementView(
        LocalDate measurementDate,
        String commentary
){}