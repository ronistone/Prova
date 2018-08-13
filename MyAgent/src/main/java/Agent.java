import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class Agent {

    private static Logger LOGGER = Logger.getLogger( Agent.class.getName() );


    public static void premain(String agentArgs, Instrumentation instrumentation){

        LOGGER.info("Initialize Agent");

        transform(instrumentation);

        //transformClass(instrumentation);
    }

    private static void transformClass(Instrumentation instrumentation) {

        for(Class c: instrumentation.getAllLoadedClasses()){
           // transform(c, c.getClassLoader(), instrumentation);
        }

    }

    private static void transform(Instrumentation instrumentation) {

        instrumentation.addTransformer(new ClassLogger());

    }


}
