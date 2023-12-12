package com.backend.MongoDB.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "statistik")
public class Statistik {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String userName;
    private String points;
    private String time;
}


