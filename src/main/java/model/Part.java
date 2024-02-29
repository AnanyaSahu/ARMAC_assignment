package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Part")
public class Part {

    @Id
    @Column(name = "code")
    private Integer code;

    @Column(name = "description")
    private String description;

    @Column(name = "purchaseTimePlanned")
    private Integer purchaseTimePlanned;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "demandForecast")
    private Double demandForecast;

    @Column(name = "demandPLCDO")
    private String demandPLCDO;

    @Column(name = "min")
    private Integer min;
}
