package entityXML;

import lombok.*;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notebook {
    private List<Person> persons;
}
