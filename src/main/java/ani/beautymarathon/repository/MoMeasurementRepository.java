package ani.beautymarathon.repository;

import ani.beautymarathon.entity.MoMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoMeasurementRepository extends JpaRepository<MoMeasurement, Long> {

    Optional<MoMeasurement> findByYearAndMonthNumber(final int year, final int month);
}
