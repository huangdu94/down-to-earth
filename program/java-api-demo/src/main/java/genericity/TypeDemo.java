package genericity;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class TypeDemo {

    public static void main(String[] args) {
        try {
            demoA();
            System.out.println();
            demoB();
            System.out.println();
            demoCD();
            System.out.println();
            demoE();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static void demoA() throws NoSuchFieldException {
        Field aField = Student.class.getDeclaredField("a");
        System.out.println("typeName: " + getTypeName(aField.getGenericType()));
        ParameterizedType aType = (ParameterizedType)aField.getGenericType();
        System.out.println("rawType: " + aType.getRawType());
        System.out.println("ownerType: " + aType.getOwnerType());
        System.out.println("actualTypeArguments: " + Arrays.toString(aType.getActualTypeArguments()));
    }

    public static void demoB() throws NoSuchFieldException {
        Field bField = Student.class.getDeclaredField("b");
        System.out.println("typeName: " + getTypeName(bField.getGenericType()));
        TypeVariable bType = (TypeVariable)bField.getGenericType();
        System.out.println("name: " + bType.getName());
        System.out.println("genericDeclaration: " + bType.getGenericDeclaration());
        System.out.println("bounds: " + Arrays.toString(bType.getBounds()));
    }

    public static void demoCD() throws NoSuchFieldException {
        Field cField = Student.class.getDeclaredField("c");
        System.out.println("typeName: " + getTypeName(cField.getGenericType()));
        GenericArrayType cType = (GenericArrayType)cField.getGenericType();
        System.out.println("genericComponentType: " + cType.getGenericComponentType());
        Field dField = Student.class.getDeclaredField("d");
        System.out.println("typeName: " + getTypeName(dField.getGenericType()));
        GenericArrayType dType = (GenericArrayType)dField.getGenericType();
        System.out.println("genericComponentType: " + dType.getGenericComponentType());
    }

    public static void demoE() throws NoSuchFieldException {
        Field eField = Student.class.getDeclaredField("e");
        System.out.println("typeName: " + getTypeName(eField.getGenericType()));
        ParameterizedType eType = (ParameterizedType)eField.getGenericType();
        WildcardType wildcardType = (WildcardType)eType.getActualTypeArguments()[0];
        System.out.println("lowerBounds: " + Arrays.toString(wildcardType.getLowerBounds()));
        System.out.println("upperBounds: " + Arrays.toString(wildcardType.getUpperBounds()));
    }

    public static String getTypeName(Type type) {
        if (type instanceof Class) {
            // just like "String"
            return "Class";
        } else if (type instanceof TypeVariable) {
            // just like "T"
            return "TypeVariable";
        } else if (type instanceof ParameterizedType) {
            // just like "List<String>";
            return "ParameterizedType";
        } else if (type instanceof GenericArrayType) {
            // just like "T[]";
            return "GenericArrayType";
        } else if (type instanceof WildcardType) {
            return "WildcardType";
        } else {
            // 理论上不该如此
            return "something wrong";
        }
    }

    static class Student<T extends Number> {
        public List<T> a = new ArrayList<>();
        public T b;
        public List<String>[] c;
        public T[] d;
        public List<? super Number> e;
    }
}
