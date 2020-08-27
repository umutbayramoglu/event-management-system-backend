package yte.internship.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.math.BigDecimal;


@AllArgsConstructor
@Getter
@Setter
public class Address {

    private String address;
    private BigDecimal lng;
    private BigDecimal lat;
}
