package ani.beautymarathon.repository;

import ani.beautymarathon.entity.User;
import ani.beautymarathon.entity.UserMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMeasurementRepository extends JpaRepository<UserMeasurement, Long> {
}
