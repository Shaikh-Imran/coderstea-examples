package core;

public class InterfaceOfManyFaces {
    public static void main(String[] args) {
        System.out.println("The best website is :" + StaticFunction.getAwesomeWebsite());
        FunctionalInterfaceDemo anonymous = new FunctionalInterfaceDemo() {
            @Override
            public void iAmTheOnlyOne() {
                System.out.println("inside lambda");
            }
        };
        FunctionalInterfaceDemo useLambda = () -> System.out.println("inside lambda");
        useLambda.iAmTheOnlyOne();
        anonymous.iAmTheOnlyOne();
    }
}

//usual interface
interface NormalInterface {
    int overrideThisFunction();
}

class ImplementNormalInterface implements NormalInterface {

    @Override
    public int overrideThisFunction() {
        //Do something
        return 0;
    }
}

//interface with default functions
interface WithDefaultFunction {
    int overrideThisFunction();
    default int noNeedToOverride(){
        return 1 + 1;
    }
}

class ImplementWithDefaultFunction implements WithDefaultFunction{
    @Override
    public int overrideThisFunction() {return 0;}
}
class ImplementWithDefaultFunction2 implements WithDefaultFunction{
    @Override
    public int overrideThisFunction() {return 0;}

    //There is no need to override
    //Just in case you want to change the default behaviour
    @Override
    public int noNeedToOverride() {
        //just changed default behaviour
        return 2+2;
    }
}

interface StaticFunction{
    static String getAwesomeWebsite(){
        return "CodersTea.com";
    }
    void overrideThis();
}

class ImplementStaticFunc implements StaticFunction{
    //there is no option of overriding static function
    //this is not the overridden function
    static String getAwesomeWebsite(){
        return "https://www.CodersTea.com";
    }

    @Override
    public void overrideThis() { /*do something*/ }

}


@FunctionalInterface
interface FunctionalInterfaceDemo{
    void iAmTheOnlyOne();
    default void iAmAlsoHereButNotAnAbstractMethod(){}
}

class ImplementFunctionalInterface implements FunctionalInterfaceDemo{
    @Override
    public void iAmTheOnlyOne() {
        //normal overriding
    }

}



