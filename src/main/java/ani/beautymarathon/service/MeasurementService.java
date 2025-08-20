package ani.beautymarathon.service;

import ani.beautymarathon.entity.ClosedState;
import ani.beautymarathon.entity.MoMeasurement;
import ani.beautymarathon.entity.WkMeasurement;
import ani.beautymarathon.exception.MoClosedException;
import ani.beautymarathon.repository.MoMeasurementRepository;
import ani.beautymarathon.repository.UserMeasurementRepository;
import ani.beautymarathon.repository.WkMeasurementRepository;
import ani.beautymarathon.view.measurement.CreateWeekMeasurementView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MeasurementService {
    private final MoMeasurementRepository moMeasurementRepository;
    private final WkMeasurementRepository wkMeasurementRepository;
    private final UserMeasurementRepository userMeasurementRepository;

    public MeasurementService(MoMeasurementRepository moMeasurementRepository,
                              WkMeasurementRepository wkMeasurementRepository,
                              UserMeasurementRepository userMeasurementRepository) {
        this.moMeasurementRepository = moMeasurementRepository;
        this.wkMeasurementRepository = wkMeasurementRepository;
        this.userMeasurementRepository = userMeasurementRepository;
    }

    public Object createWkMeasurement(CreateWeekMeasurementView createWeekMeasurementView){
        final LocalDate measurementDate = createWeekMeasurementView.measurementDate();
        final int year = measurementDate.getYear();
        final int month = measurementDate.getMonthValue();
        final String commentary = createWeekMeasurementView.commentary();
        final WkMeasurement newWkMeasurement = new WkMeasurement();
        Optional<MoMeasurement> found = moMeasurementRepository.findByYearAndMonthNumber(year, month);
        found.ifPresentOrElse(
                (moMeasurement) -> {
                    ClosedState closedState = moMeasurement.getClosedState();
                    if (ClosedState.CLOSED == closedState){
                        String errorText = "Found month " + month + "-" + year + " is closed";
                        throw new MoClosedException(errorText);
                    }
                    newWkMeasurement.setMoMeasurement(moMeasurement);
                },
                () -> {
                    MoMeasurement moMeasurement = new MoMeasurement();
                    moMeasurement.setClosedState(ClosedState.OPEN);
                    moMeasurement.setMoDate(measurementDate);
                    MoMeasurement savedMonth = moMeasurementRepository.save(moMeasurement);
                    newWkMeasurement.setMoMeasurement(savedMonth);
                }

        );
        newWkMeasurement.setCommentary(commentary);
        newWkMeasurement.setClosedState(ClosedState.OPEN);
        WkMeasurement saved = wkMeasurementRepository.save(newWkMeasurement);

        return null;
    }
}
