package com.backend.MongoDB.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "character" )
public class League {
    private String id;
    private String name;
    private String hp;
    private String attack;
    private String defence;
}
