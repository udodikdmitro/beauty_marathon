package ani.beautymarathon.repository;

import ani.beautymarathon.entity.MoMeasurement;
import ani.beautymarathon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoMeasurementRepository extends JpaRepository<MoMeasurement, Long> {
}
