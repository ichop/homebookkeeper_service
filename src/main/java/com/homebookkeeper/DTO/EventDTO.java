package com.homebookkeeper.DTO;



import com.homebookkeeper.model.EventType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EventDTO extends BaseDTO {

    private EventType type;

    private BigDecimal amount;

    private Long categoryId;

    private LocalDate date;

    private String description;

    private Long userId;
}
