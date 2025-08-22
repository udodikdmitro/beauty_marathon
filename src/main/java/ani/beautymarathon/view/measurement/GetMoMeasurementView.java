package ani.beautymarathon.view.measurement;

import ani.beautymarathon.entity.ClosedState;

public record GetMoMeasurementView(
        Long id,
        ClosedState closedState,
        Integer year,
        Integer monthNumber
) {}