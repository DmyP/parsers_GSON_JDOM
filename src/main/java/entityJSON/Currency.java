package entityJSON;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Currency {
    private String r030l;
    private String txt;
    private Double rate;
    private String cc;
    private String exchangedate;
}
