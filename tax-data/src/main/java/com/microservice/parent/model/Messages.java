package com.microservice.parent.model;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(value = "messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @Id
    private String id;
    private String name;
    private String email;
    private String subject;
    private String message;
}
