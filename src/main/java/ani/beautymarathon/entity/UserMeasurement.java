package ani.beautymarathon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_measurement")
public class UserMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "weight_point")
    private Integer weightPoint;

    @Column(name = "sleep_points")
    private Integer sleepPoint;

    @Column(name = "water_points")
    private Integer waterPoint;

    @Column(name = "step_points")
    private Integer stepPoint;

    @Column(name = "diary_points")
    private Integer diaryPoint;

    @Column(name = "alcohol_free_points")
    private Integer alcoholFreePoints;

    @Column(name = "commentary")
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "wk_measurement_id")
    private WkMeasurement wkMeasurement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserMeasurement(Long id, Double weight, Integer weightPoint, Integer sleepPoint,
                           Integer waterPoint, Integer stepPoint, Integer diaryPoint,
                           Integer alcoholFreePoints, String commentary, WkMeasurement wkMeasurement, User user) {
        this.id = id;
        this.weight = weight;
        this.weightPoint = weightPoint;
        this.sleepPoint = sleepPoint;
        this.waterPoint = waterPoint;
        this.stepPoint = stepPoint;
        this.diaryPoint = diaryPoint;
        this.alcoholFreePoints = alcoholFreePoints;
        this.commentary = commentary;
        this.wkMeasurement = wkMeasurement;
        this.user = user;
    }

    public UserMeasurement() {

    }

    @Override
    public String toString() {
        return "Замер за неделю: " + wkMeasurement +
                "\nУчастник " + user +
                ":\nвес: " + weight +
                "\nбаллы за вес: " + weightPoint +
                "\nбаллы за сон: " + sleepPoint +
                "\nбаллы за воду: " + waterPoint +
                "\nбаллы за шаги: " + stepPoint +
                "\nбаллы за дневник: " + diaryPoint +
                "\nбаллы за трезвость: " + alcoholFreePoints +
                "\n\nКомментарий: " + commentary
                ;
    }
}
