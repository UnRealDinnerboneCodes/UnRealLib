//package com.unrealdinnerbone;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.awt.*;
//import java.lang.reflect.Field;
//import java.util.Calendar;
//
//@Slf4j
//public class ReflectionTest {
//
//    @org.junit.Test
//    public void testStuff() throws NoSuchFieldException, IllegalAccessException {
//        Test test = Test.TEST_ONoE;
//        TestTwo testTwo = new TestTwo(test);
//        log.info(getFieldsValueAsString(testTwo.getClass().getField("test"), testTwo));
//        log.info(testTwo.getClass().getField("test").get(testTwo).toString());
//
//        log.info(getFieldsValueAsString(testTwo.getClass().getField("some"), testTwo));
//        log.info(testTwo.getClass().getField("some").get(testTwo).toString());
//    }
//
//
//    public static String getFieldsValueAsString(Field field, Object o) {
//        try {
//            setFieldAccessible(field, o);
//            if (field.getType().isEnum()) {
//                return ((Enum) field.get(o)).name();
//            } else {
//                if (field.get(o) == null) {
//                    return "";
//                } else {
//                    return field.get(o).toString();
//                }
//            }
//        } catch (IllegalAccessException e) {
//            log.error("here was an error while parsing to from database", e);
//        }
//        return null;
//    }
//
//    public static void setFieldAccessible(Field field, Object object) {
//        field.setAccessible(true);
//    }
//
//    @AllArgsConstructor
//    public static class TestTwo {
//        public final Test test;
//        public final int some = 0;
//    }
//
//
//    public static enum Test {
//        TEST_ONoE,
//        TEST_TWO,
//    }
//
//}
