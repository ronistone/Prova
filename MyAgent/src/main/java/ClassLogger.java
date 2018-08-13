import javassist.CtClass;
import javassist.ClassPool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.logging.Logger;

public class ClassLogger implements ClassFileTransformer {

    private static Logger LOGGER = Logger.getLogger( ClassLogger.class.getName() );


    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {


        String finalClassName = s.replaceAll("/", ".");
        byte[] byteCode = bytes;

        //if(finalClassName.endsWith("PessoaController")) {
        if(finalClassName.startsWith("br.com.algartelecom")){
            LOGGER.info("Transforming 1 [" + finalClassName + "]" + " -- " + bytes.length);
            try {
                LOGGER.info("OLOKO MEU");
                ClassPool cp = ClassPool.getDefault();
                LOGGER.info("cp is Null?" + (cp == null));
                System.out.println("cp is Null? fake");
//                LOGGER.info(ClassLogger.class.getName() + "Methods Size Before: " + cp.getCtClass(finalClassName).getDeclaredMethods().length);
                CtClass cc = cp.getCtClass(finalClassName);//cp.get(finalClassName);

                LOGGER.info("cc is Null?" + (cc == null));
                LOGGER.info("cc is Null? fake");
                LOGGER.info("Methods Size: " + cc.getDeclaredMethod("get") .getLongName());
//                for (CtMethod m : cc.getDeclaredMethods()) {
//                    LOGGER.info(ClassLogger.class.getName() + ": Method - " + m.getLongName());
//
//                    if(!m.getName().equals("get"))
//                        continue;
//                    m.addLocalVariable("startTime", CtClass.longType);
//                    m.insertBefore("startTime = System.currentTimeMillis();");
//
//                    StringBuilder endBlock = new StringBuilder();
////                    m.addLocalVariable("opTime", CtClass.longType);
////                    m.addLocalVariable("endTime", CtClass.longType);
////                    endBlock.append("endTime = System.currentTimeMillis();");
////                    endBlock.append("opTime = (endTime - startTime)/1000;");
//
////                endBlock.append(
////                        "LOGGER.info(\"" + m.getLongName() + "\" Completed in \" + opTime + \" );"
////                );
//
//                    m.insertAfter(endBlock.toString());
//
//
//                    byteCode = cc.toBytecode();
//                    cc.detach();
//                }

//            } catch (CannotCompileException e) {
//                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
//            } catch (NotFoundException e) {
//                e.printStackTrace();
            }
            LOGGER.info("END");
        }

        return byteCode;
    }

}
