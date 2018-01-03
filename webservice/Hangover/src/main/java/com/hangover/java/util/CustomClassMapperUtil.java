package com.hangover.java.util;

import com.hangover.java.exception.HangoverException;
import com.hangover.java.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/10/16
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomClassMapperUtil {

    private static Logger logger = LoggerFactory.getLogger(CustomClassMapperUtil.class);

    public static String MODEL_BASE_PACKAGE = "com.hangover.java.model";
    public static String DTO_BASE_PACKAGE = "com.hangover.java.dto";

    private Map<String, Class> classNameMap = new HashMap<String, Class>();
    private Map<String, Class> classNameDTOMap = new HashMap<String, Class>();

    private static Map<String, Class> classMap=null;

    private static CustomClassMapperUtil scanner = null;

    private CustomClassMapperUtil(){

    }

    public static CustomClassMapperUtil getInstance() throws ClassNotFoundException, IOException {
        if ( scanner == null){
            scanner = new CustomClassMapperUtil();
            scanner.doScan();
            scanner.prepareCustomClassMap();
        }
        return scanner;
    }

    public Map<String, Class> getClassNameMap() {
        return classNameMap;
    }


    public Map<String, Class> getClassNameDTOMap() {
        return classNameDTOMap;
    }

    private void doScan() throws IOException, ClassNotFoundException {
        classNameMap = getClassNameMap(MODEL_BASE_PACKAGE);
        classNameDTOMap = getClassNameMap(DTO_BASE_PACKAGE);

    }


    private void prepareCustomClassMap(){
        classMap = new HashMap<String, Class>();
        classMap.put("UserEntity", com.hangover.java.model.UserEntity.class);
        classMap.put("ItemEntity", com.hangover.java.model.ItemEntity.class);
        classMap.put("CategoryEntity", com.hangover.java.model.master.CategoryEntity.class);
    }



    public  Class getCustomMappedClass(String className) throws ClassNotFoundException {
        Class clazz = classMap.get(className);
        if(null==clazz){
            throw new ClassNotFoundException();
        }
        return clazz;
    }


    public static Class getMappedClass(String className) throws IOException, ClassNotFoundException {
        Class  clazz = CustomClassMapperUtil.getInstance().getClassByName(className);
        if(null==clazz){
            throw new ClassNotFoundException();
        }
        return clazz;
    }


    public static Class getMappedClassDTO(String className) throws IOException, ClassNotFoundException {
        Class  clazz = CustomClassMapperUtil.getInstance().getClassDTOByName(className);
        if(null==clazz){
            throw new ClassNotFoundException();
        }
        return clazz;
    }


    public Class getClassByName(String className) throws ClassNotFoundException {
         Class  clazz = getClassNameMap().get(className);
        if(null== clazz){
            throw new ClassNotFoundException();
        }
        return clazz;
    }


    public Class getClassDTOByName(String className) throws ClassNotFoundException {
        Class  clazz = getClassNameDTOMap().get(className);
        if(null== clazz){
            throw new ClassNotFoundException();
        }
        return clazz;
    }

    public static String getClassName(String name) {
        int length = name.length();
        int index = name.lastIndexOf(".");
        return name.substring(index+1, length );
    }

    public Map<String, Class>  getClassNameMap(String packageName)
            throws ClassNotFoundException, IOException {
        Map<String, Class> classNameMap = new HashMap<String, Class>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = (URL)resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        //Map<String, Class<? extends BaseEntity>> classNameMap = new HashMap<String, Class<? extends BaseEntity>>();
        for (File directory : dirs) {
            classNameMap.putAll(findClasses(directory, packageName));
        }
        return classNameMap;
    }

    private static Map<String, Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        Map<String, Class> classNameMap = new HashMap<String, Class>();
        if (!directory.exists()) {
            return classNameMap;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classNameMap.putAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                Class clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                classNameMap.put(getClassName(clazz.getName()),clazz);
            }
        }
        return classNameMap;
    }



    public static <T> Object invokeMethod(String methodName, T model) throws HangoverException{
        try {
           return model.getClass().getMethod(methodName).invoke(model);
        } catch (IllegalAccessException e) {
            throw new HangoverException(e);
        } catch (InvocationTargetException e) {
            throw new HangoverException(e);
        } catch (NoSuchMethodException e) {
            throw new HangoverException(e);
        }
    }
    
    public static <T> Object invokeMethod(String methodName, T model, Object obj){
        Method method = null;
        try {
            method = model.getClass().getMethod(methodName, new Class[] { obj.getClass() });
          return  invokeMethod(method, model, obj);
        } catch (SecurityException e) {
            //MbcLogUtil.getInstance().logException(e, true);
            throw new HangoverException(e);
        } catch (NoSuchMethodException e) {
            // MbcLogUtil.getInstance().logException(e, true);
            throw new HangoverException(e);
        }
    }

    public static <T> Object invokeMethod(Method method, T model, Object obj)
            throws HangoverException {
        if (null != method) {
            try {
              return  method.invoke(model, obj);
            } catch (IllegalArgumentException e) {
                //MbcLogUtil.getInstance().logException(e, true);
                throw new HangoverException(e);
            } catch (IllegalAccessException e) {
                //MbcLogUtil.getInstance().logException(e, true);
                throw new HangoverException(e);
            } catch (InvocationTargetException e) {
                //MbcLogUtil.getInstance().logException(e, true);
                throw new HangoverException(e);
            }
        }
        return null;
    }


    public static void transferQueryParamToPramMap(Class clazz, Map<String,String[]> queryParam, Map<String,Object> paramMap){
        for(String key : queryParam.keySet()){
            try {
                Object val = queryParam.get(key)[0];
                if(StringUtil.isNotNullOrEmpty(val)){
                    Field field = clazz.getDeclaredField(key);
                    if(null!=field){
                        paramMap.put(key, val);
                    }
                }
            } catch (NoSuchFieldException e) {
                logger.error("No Such filed "+key+" in class "+ clazz.getName());
            }
        }
    }

    public static boolean classHasField(Class clazz, String fieldName) {
        boolean isExist = false;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (null != field) {
                isExist = true;
            }
        } catch (NoSuchFieldException e) {
            logger.error("No Such filed " + fieldName + " in class " + clazz.getName());
        }
        return isExist;
    }
    
    public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getDeclaredField(fieldName);
        /*try {
             field =
        } catch (NoSuchFieldException e) {
            logger.error("No Such filed " + fieldName + " in class " + clazz.getName());
        }*/
    }
    
    
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        CustomClassMapperUtil customClassMapperUtil = CustomClassMapperUtil.getInstance();
        Map<String, Class> classNameMap = customClassMapperUtil.getClassNameMap(MODEL_BASE_PACKAGE);
        for(String key : classNameMap.keySet()){
            System.out.println("Key:- "+key +"  Value:- "+classNameMap.get(key));
        }
    }
    
}
