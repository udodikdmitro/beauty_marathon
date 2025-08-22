package ani.beautymarathon.view.measurement;
import ani.beautymarathon.entity.ClosedState;

public record UpdateWkMeasurementView(
        ClosedState closedState,
        String commentary
        ) {}
