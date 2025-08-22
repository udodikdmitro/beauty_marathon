package ani.beautymarathon.controller;

import ani.beautymarathon.entity.ClosedState;
import ani.beautymarathon.entity.MoMeasurement;
import ani.beautymarathon.entity.WkMeasurement;
import ani.beautymarathon.service.MeasurementService;
import ani.beautymarathon.view.measurement.CreateWkMeasurementView;
import ani.beautymarathon.view.measurement.GetMoMeasurementView;
import ani.beautymarathon.view.measurement.GetWkMeasurementView;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    private final MeasurementService measurementService;

    @PostMapping("/create")
    public GetWkMeasurementView createWkMeasurement(@Valid @RequestBody CreateWkMeasurementView newWkMeasurementView) {
        final WkMeasurement newWkMeasurement = new WkMeasurement();

        newWkMeasurement.setMeasurementDate(newWkMeasurementView.measurementDate());
        newWkMeasurement.setCommentary(newWkMeasurementView.commentary());
        newWkMeasurement.setClosedState(ClosedState.OPEN);

        WkMeasurement createdWkMeasurement = measurementService.createWkMeasurement(newWkMeasurement);
        return constructWeekMeasurementView(createdWkMeasurement);
    }

    private GetWkMeasurementView constructWeekMeasurementView(WkMeasurement wkMeasurement) {
        MoMeasurement moMeasurement = wkMeasurement.getMoMeasurement();
        GetMoMeasurementView moMeasurementView = new GetMoMeasurementView(
                moMeasurement.getId(),
                moMeasurement.getClosedState(),
                moMeasurement.getYear(),
                moMeasurement.getMonthNumber()
        );
        return new GetWkMeasurementView(
                wkMeasurement.getId(),
                wkMeasurement.getMeasurementDate(),
                wkMeasurement.getClosedState(),
                wkMeasurement.getCommentary(),
                moMeasurementView
        );
    }
}
