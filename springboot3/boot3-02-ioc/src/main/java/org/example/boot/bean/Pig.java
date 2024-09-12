package org.example.boot.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "pig")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pig {

    protected int id;
    protected String name;

}
