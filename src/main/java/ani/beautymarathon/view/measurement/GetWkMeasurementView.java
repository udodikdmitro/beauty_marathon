package ani.beautymarathon.view.measurement;

import ani.beautymarathon.entity.ClosedState;

import java.time.LocalDate;

public record GetWkMeasurementView(
        Long id,
        LocalDate measurementDate,
        ClosedState closedState,
        String commentary,
        GetMoMeasurementView moMeasurement
) {}