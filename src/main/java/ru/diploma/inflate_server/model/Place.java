package ru.diploma.inflate_server.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Place {
    private String shelf;
    private String column;
    private String row;
}
