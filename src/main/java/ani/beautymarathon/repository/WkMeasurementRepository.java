package ani.beautymarathon.repository;

import ani.beautymarathon.entity.WkMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WkMeasurementRepository extends JpaRepository<WkMeasurement, Long> {
    Optional<WkMeasurement> findByMeasurementDate(LocalDate measurementDate);

}
