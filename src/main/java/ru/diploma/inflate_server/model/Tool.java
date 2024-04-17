package ru.diploma.inflate_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.diploma.inflate_server.model.enums.ToolType;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@Entity
@Table(name = "toolTable")
public class Tool {
    @Id
    private String code;
    private String name;
    private String description;
    private String additionalInfo;
    private String icon;
    @Embedded
    private Place place;
    @Enumerated
    private ToolType type;
    private LocalDate controlDate;

}
