package ani.beautymarathon.repository;

import ani.beautymarathon.entity.User;
import ani.beautymarathon.entity.WkMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WkMeasurementRepository extends JpaRepository<WkMeasurement, Long> {
}
