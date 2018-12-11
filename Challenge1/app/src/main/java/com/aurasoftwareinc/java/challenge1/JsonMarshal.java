package com.aurasoftwareinc.java.challenge1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class JsonMarshal{

    /**
     * This method marshals the given object to a json object
     * @param object what we get
     * @return a json object
     */
    public static JSONObject marshalJSON(Object object) {

        // Todo: replace contents of this method with Your code.
        JSONObject json = null;
        try{
        json = new JSONObject();
        Class inputClass = object.getClass();
        Field classFields[] = inputClass.getDeclaredFields();
        Class classType;
        Object result;

            for (Field classField : classFields) {
                String fieldName = classField.getName();
                classType = classField.getType();

                if (classType.equals(byte.class)
                        || classType.equals(Byte.class)
                        || classType.equals(float.class)
                        || classType.equals(Float.class)
                        || classType.equals(int.class)
                        || classType.equals(Integer.class)
                        || classType.equals(short.class)
                        || classType.equals(Short.class)
                        || classType.equals(long.class)
                        || classType.equals(Long.class)
                        || classType.equals(double.class)
                        || classType.equals(Double.class)
                        || classType.equals(boolean.class)
                        || classType.equals(Boolean.class)
                        || classType.equals(long.class)
                        || classType.equals(Long.class)
                        || classType.equals(String.class)
                        || classType.isArray()
                        || classType.equals(JSONObject.class)
                        || classType.equals(JSONArray.class)){

                    result = extractPrimitiveValue(fieldName, object);
                    json.put(fieldName,  result!=null ? result : "");
                }
                else if (classType.equals(PrimitiveTypes.class)
                        || classType.equals(ObjectTypes.class)
                        || classType.equals(JSONTypes.class)){
                    json.put(fieldName, extractSubClass(fieldName, object));
                }
                else {
                    System.out.println("marshaling is not implemented for "+ classType);
                }
            }
            System.out.println("No of Class fields "+classFields.length);
            System.out.println("No of constructed json fields "+json.length());
            if (classFields.length != json.length())
                throw new Exception("Sizes are different");
        }
        catch (JSONException jsonException){
            jsonException.printStackTrace();
            //Not handling
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println(json);
        return json;
    }



    /**
     * //TODO fill it out
     * This method will do...
     * @param fieldName
     * @param object
     * @return
     */
    private static Object extractPrimitiveValue(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            if (!field.isAccessible())
                field.setAccessible(true);
            return field.get(object);
        }catch (NoSuchFieldException | IllegalAccessException ex){
            ex.printStackTrace();
        }
          return null;
    }

    private static Object extractSubClass(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            if (!field.isAccessible())
                field.setAccessible(true);
            return marshalJSON(field.get(object));
        }catch (NoSuchFieldException | IllegalAccessException ex){
            ex.printStackTrace();
        }
        return null;
    }


    public static boolean unmarshalJSON(Object object, JSONObject json)
    {
        //
        // Todo: replace contents of this method with Your code.
        //

        //json.get
        return true;
    }
}
