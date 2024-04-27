package ru.diploma.inflate_server.webEntities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.diploma.inflate_server.model.Place;
import ru.diploma.inflate_server.model.enums.ToolType;

import java.time.LocalDate;
@Data
@NoArgsConstructor

public class ToolWEB {

    private String code;
    private String name;
    private String description;
    private String additionalInfo;
    private String icon;
    private Place place;
    private ToolType type;
    private String controlDate;

}
