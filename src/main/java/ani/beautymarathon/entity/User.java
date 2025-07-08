package ani.beautymarathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_weight")
    private Double startWeight;

    @Column(name = "target_weight")
    private Double targetWeight;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    public User() {
    }

    public User(String name, Double startWeight, Double targetWeight, LocalDate creationDate) {
        this.name = name;
        this.startWeight = startWeight;
        this.targetWeight = targetWeight;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Участник " + id + ": " + name + ", стартовый вес "
                + startWeight + ", цель: " + targetWeight + "\n";
    }
}
