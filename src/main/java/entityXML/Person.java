package entityXML;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private long id;
    private String name;
    private String address;
    private double cash;
    private String education;


}
