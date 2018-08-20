import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.ClassPool;
import javassist.CtMethod;
import javassist.LoaderClassPath;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

public class ClassLogger implements ClassFileTransformer {

    private static Logger LOGGER = Logger.getLogger( ClassLogger.class.getName() );


    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) {


        String finalClassName = s.replaceAll("/", ".");
        byte[] byteCode = bytes.clone();

        if(finalClassName.startsWith("br.com.algartelecom") && !finalClassName.contains("$")){
            try {
                CtClass cc = getClass(byteCode);

                for (CtMethod m : cc.getDeclaredMethods()) {
//                    if(m.isEmpty())
//                        continue;

                    insertMetricCode(m);

                }
                byteCode = cc.toBytecode();
                cc.detach();

            } catch (Exception e) {
                byteCode = bytes;
            }
        }

        return byteCode;
    }

    private CtClass getClass(byte[] byteCode) throws IOException {
        ClassPool cp = ClassPool.getDefault();
        cp.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
        return cp.makeClass(new ByteArrayInputStream(byteCode));
    }

    private void insertMetricCode(CtMethod m) throws CannotCompileException {
        LOGGER.info(ClassLogger.class.getName() + ": Method - " + m.getLongName());
        m.addLocalVariable("startTime", CtClass.longType);
        m.insertBefore("startTime = System.currentTimeMillis();");

        StringBuilder endBlock = new StringBuilder();
        m.addLocalVariable("opTime", CtClass.longType);
        m.addLocalVariable("endTime", CtClass.longType);
        endBlock.append("endTime = System.currentTimeMillis();");
        endBlock.append("opTime = (endTime - startTime);");

        endBlock.append(
                "LOGGER.info(\"" + m.getLongName() + " Completed in \" + opTime + \"ms\" );"
        );

        m.insertAfter(endBlock.toString());
    }

}
