package tools;

import lombok.*;

import java.util.List;

public class LombokCode {
    public static void main(String[] args) {

    }
}



@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
class SetterAndGetter{
    private final int id;
    private final String name;
    private ExtraClass extraClass;
}


//without final fields
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ExtraClass{
    private int a;
}

//with final fields

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class LotsOfAnnotations {
    private final int a;
    private String ab;
}
@Data
class OnlyData{
    private final int a;
    private String ab;
}


@ToString
class WithBuilderPojo{
    private int id;
    @NonNull
    private String name;
    private ExtraClass extraClass;
    private List<Float> listOfMarks;

public static void main(String[] args) {
}
}