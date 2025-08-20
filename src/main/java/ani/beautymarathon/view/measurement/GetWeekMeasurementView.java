package ani.beautymarathon.view.measurement;

import ani.beautymarathon.entity.ClosedState;
import ani.beautymarathon.entity.MoMeasurement;

import java.time.LocalDate;

public record GetWeekMeasurementView(
        Long id,
        LocalDate measurementDate,
        ClosedState closedState,
        String commentary,
        MoMeasurement moMeasurement
) {}