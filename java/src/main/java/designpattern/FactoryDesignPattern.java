package designpattern;

public class FactoryDesignPattern {
    public static void main(String[] args) {
        Student computerStudent = StudentFactory.createStudent(StudentTypes.COMPUTER, "Imran");
        Student financeStudent = StudentFactory.createStudent(StudentTypes.FINANCE, "Shaikh");
        Student ArtStudent = StudentFactory.createStudent(StudentTypes.ART, "Mahesh");

        Student error = StudentFactory.createStudent(null, null);
    }
}


interface Student {
    String getName();
}

class FinanceStudent implements Student {
    private String name;

    FinanceStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return "Finance Student " + name;
    }
}

class ArtStudent implements Student {
    private String name;

    ArtStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return "Art Student " + name;
    }
}

class ComputerStudent implements Student {
    private String name;

    ComputerStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return "Computer Student " + name;
    }
}

enum StudentTypes {
    FINANCE, ART, COMPUTER;
}

class StudentFactory {
    public static Student createStudent(StudentTypes studentType,
                                        String name) {
        System.out.printf("Creating %s student object with name %s \n"
                , studentType, name);


        if (name == null) {
            throw new RuntimeException("name is null");
        }

        switch (studentType) {
            case FINANCE:
                return new FinanceStudent(name);
            case ART:
                return new ArtStudent(name);
            case COMPUTER:
                return new ComputerStudent(name);
            default:
                // returning null is not recommended
                return new ComputerStudent(name);
        }
    }
}

