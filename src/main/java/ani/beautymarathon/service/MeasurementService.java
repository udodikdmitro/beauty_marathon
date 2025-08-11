package ani.beautymarathon.service;

import ani.beautymarathon.entity.MoMeasurement;
import ani.beautymarathon.entity.UserMeasurement;
import ani.beautymarathon.entity.WkMeasurement;
import ani.beautymarathon.repository.MoMeasurementRepository;
import ani.beautymarathon.repository.UserMeasurementRepository;
import ani.beautymarathon.repository.WkMeasurementRepository;
import org.springframework.stereotype.Service;

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

    public MoMeasurement createMoMeasurement(MoMeasurement moMeasurement) {
        return moMeasurementRepository.save(moMeasurement);
    }

    public WkMeasurement createWkMeasurement(WkMeasurement wkMeasurement) {
        return wkMeasurementRepository.save(wkMeasurement);
    }

    public UserMeasurement createUserMeasurement(UserMeasurement userMeasurement) {
        return userMeasurementRepository.save(userMeasurement);
    }
}
