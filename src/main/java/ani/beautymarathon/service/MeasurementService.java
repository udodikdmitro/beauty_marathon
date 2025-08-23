package ani.beautymarathon.service;

import ani.beautymarathon.entity.ClosedState;
import ani.beautymarathon.entity.MoMeasurement;
import ani.beautymarathon.entity.WkMeasurement;
import ani.beautymarathon.exception.MoClosedException;
import ani.beautymarathon.repository.MoMeasurementRepository;
import ani.beautymarathon.repository.UserMeasurementRepository;
import ani.beautymarathon.repository.WkMeasurementRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
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

    @Transactional
    public WkMeasurement createWkMeasurement(WkMeasurement wkMeasurement){
        final LocalDate measurementDate = wkMeasurement.getMeasurementDate();
        final int year = measurementDate.getYear();
        final int month = measurementDate.getMonthValue();
        Optional<WkMeasurement> byMeasurementDate = wkMeasurementRepository.findByMeasurementDate(measurementDate);
        if(byMeasurementDate.isPresent()){
            throw new IllegalArgumentException("WkMeasurement with date " + measurementDate + " already exists");
        }
        Optional<MoMeasurement> foundMonth = moMeasurementRepository.findByYearAndMonthNumber(year, month);
        foundMonth.ifPresentOrElse(
                (moMeasurement) -> {
                    ClosedState closedState = moMeasurement.getClosedState();
                    if (ClosedState.CLOSED == closedState){
                        String errorText = "Found month " + month + "-" + year + " is closed";
                        throw new MoClosedException(errorText);
                    }
                    wkMeasurement.setMoMeasurement(moMeasurement);
                },
                () -> {
                    MoMeasurement moMeasurement = new MoMeasurement();
                    moMeasurement.setMoDate(measurementDate);
                    MoMeasurement savedMonth = moMeasurementRepository.save(moMeasurement);
                    log.info("Saved month: {}", savedMonth);
                    wkMeasurement.setMoMeasurement(savedMonth);
                }
        );

        WkMeasurement saved = wkMeasurementRepository.save(wkMeasurement);
        log.info("Saved week: {}", saved);
        return saved;
    }


}
