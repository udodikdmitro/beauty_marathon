package ani.beautymarathon.view;

import ani.beautymarathon.entity.DeletedState;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GetUserView(
        Long id,
        String name,
        BigDecimal startWeight,
        BigDecimal targetWeight,
        LocalDate creationDate,
        DeletedState deletedState
) {}