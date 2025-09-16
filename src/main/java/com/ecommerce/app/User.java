package com.ecommerce.app;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@JsonPropertyOrder({ "id", "firstName", "lastName" })

public class User {

    private Long id;

    private String firstName;
    private String lastName;

}
