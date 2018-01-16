package chaos.utils.object;

import chaos.utils.json.JsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * ©
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-06-02
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    private static Logger log = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 有一个内容为空，即返回true
     * <pre>
     *     null = true
     *     "null" = true
     *     [,,a,b] = true
     *     [a,,b,c] = true
     * </pre>
     *
     * @param obj
     * @return
     */
//    public static boolean isEmpty(Object... objects) {
//        if (objects == null) return true;
//        if (objects.length == 0) return true;
//        for (int i = 0; i < objects.length; i++) {
//            Object object;
//            try {
//                object = objects[i];
//                if (object instanceof CharSequence) if (object.equals("null")) return true;
//            } catch (Exception ignored) {
//                return true;
//            }
//            if (ObjectUtils.isEmpty(object)) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        try {
            if (obj instanceof CharSequence) {
                CharSequence cs = (CharSequence) obj;
                return StringUtils.isEmpty(cs.toString().trim()) || obj.equals("null");
            }
            if (obj instanceof Collection) {
                return CollectionUtils.isEmpty((Collection) obj);
            }
            if (obj instanceof Map) {
                return MapUtils.isEmpty((Map) obj);
            }
            if (obj.getClass().isArray()) {
                return ArrayUtils.getLength(obj) <= 0;
            }
        } catch (Exception e) {
            log.error("非空判断异常！");
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        if (org.apache.commons.lang3.StringUtils.isNumeric(str)) return true;
        return false;
    }

    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }


    /**
     * @param obj
     * @param def
     * @return
     */
    public static int toInt(Object obj, int def) {


        if (obj == null) return def;
        try {
            if (obj instanceof Integer) {
                def = ((Integer) obj).intValue();
            } else if (obj instanceof Long) {
                def = ((Long) obj).intValue();
            } else if (obj instanceof Short) {
                def = ((Short) obj).intValue();
            } else {
                def = NumberUtils.toInt(obj.toString(), def);
            }
        } catch (Exception e) {
            return def;
        }

        return def;
    }

    public static Long toLong(Object obj) {
        return toLong(obj, 0L);
    }

    /**
     * @param obj
     * @param def
     * @return
     */
    public static long toLong(Object obj, long def) {
        if (obj == null) return def;
        try {
            if (obj instanceof Integer) {
                def = ((Integer) obj).longValue();
            } else if (obj instanceof Long) {
                def = ((Long) obj).longValue();
            } else if (obj instanceof Short) {
                def = ((Short) obj).longValue();
            } else {
                def = NumberUtils.toLong(obj.toString(), def);
                NumberUtils.toLong("");
            }
        } catch (Exception e) {
            return def;
        }

        return def;
    }

    public static String toString(Object object, String def) {
        if (ObjectUtils.isEmpty(object)) return def;
        def = String.valueOf(object);
        if (def.equals("null")) def = "";
        return def;
    }

    public static String toString(Object object) {
        return toString(object, "");
    }

    /**
     * 任意符号分割字符串
     * 　<pre>
     *     a b c = [a,b,c]
     *     a !@#$%^&*()_+bc = [a,bc]
     *     23 k abc !@#$%^&*()_ ss = [23,k,abc,ss]
     * </pre>
     *
     * @param str
     * @return
     */
    public static String[] split(String str) {
        String[] def = {};
        StringBuilder sb = new StringBuilder();
        if (ObjectUtils.isEmpty(str)) return def;

        for (char c : str.toCharArray()) {
            if (isValidChar(c)) {
                sb.append(c);
            } else {
                if (sb.length() <= 0) continue;

                if (sb.codePointAt(sb.length() - 1) != "-".codePointAt(0)) {
                    sb.append("-");
                }
            }
        }
        def = sb.toString().split("-");
        return def;
    }

    /**
     * 有效字符串，非符号
     *
     * @param c
     * @return
     */
    public static boolean isValidChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        // 中文汉字
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
//                标点符号
//                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
//                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                ) {
            return true;
        }
        //字母和数字[0-9 a-Z]
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        return false;
    }


//    public static String[] split(String str, String regex) {
//        String[] def = {};
//
//
//        return def;
//    }

    public static void main(String[] args) {
//        for (char c : "123ssv1　S　S　SS345678!@#$$%%^%^^&*(*()_)_+9ha拉开距离那快递费就按了空间，。路口24346！@#￥%……&*（）——+".toCharArray()) {
//            System.out.println(c + " = " + isValidChar(c));
//        }


//        String[] temp = split("!!lakf hahah%%ah 3.2.3 理,论,考,试 卡空,间来看");
//        for (String s : temp) {
////            System.out.println(s);
//        }

//        System.out.println(ObjectUtils.isEmpty("adfasf"));
//        System.out.println(ObjectUtils.isEmpty(""));
//        System.out.println(ObjectUtils.isEmpty("null"));
//        System.out.println(ObjectUtils.isEmpty(new ArrayList<>()));
//        String[] strings = new String[1];
//        System.out.println(ObjectUtils.isEmpty((Object) strings));
//        System.out.println(ObjectUtils.isEmpty((Object) new String[]{"ad"}));

//
//        RegionModel regionModel = new RegionModel();
//        regionModel.setName("regionModel");
//        List<RegionModel> childs = Lists.newArrayList();
//        for (int i = 0; i < 4; i++) {
//            RegionModel m = new RegionModel();
//            m.setName("m" + i);
//            childs.add(m);
//        }
//        regionModel.setChild(childs);
//
//        Map<String, Set<String>> map = Maps.newHashMap();
//        Set<String> fs = Sets.newHashSet();
//        fs.add("id");
//        fs.add("child");
//        map.put(RegionModel.class.getName(), fs);
//        Map<String, Object> toMap = objectToMap(regionModel, map, true);
//
//        System.out.println(JsonUtils.tojSON(toMap));

    }

    /**
     * 所有字段
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        return objectToMap(obj, null, null, null, null);
    }

    /**
     * @param obj
     * @param fields
     * @param isTrue 保留还是排除
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj, Set<String> fields, boolean isTrue) {
        if (!isTrue) {
            return objectToMap(obj, null, fields, null, null);
        } else {
            return objectToMap(obj, fields, null, null, null);
        }
    }

    /**
     * @param obj
     * @param fields
     * @param isTrue 保留还是排除
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj, String[] fields, boolean isTrue) {
        if (!isTrue) {
            return objectToMap(obj, null, Arrays.stream(fields).collect(Collectors.toSet()), null, null);
        } else {
            return objectToMap(obj, Arrays.stream(fields).collect(Collectors.toSet()), null, null, null);
        }
    }

    /**
     * @param obj
     * @param fields
     * @param isTrue 保留还是排除
     * @return
     */
    private static Map<String, Object> objectToMap(Object obj, Map<String, Set<String>> fields, boolean isTrue) {

        if (isTrue) {
            return objectToMap(obj, null, null, fields, true);
        } else {
            return objectToMap(obj, null, null, fields, false);
        }
    }

    /**
     * @param obj
     * @param fields
     * @param isTrue 保留还是排除
     * @return
     */
    public static Map<String, Object> toMapFilter(Object obj, Map<String, String[]> fields, boolean isTrue) {
        Map<String, Set<String>> temp = Maps.newHashMap();
        for (Map.Entry<String, String[]> entry : fields.entrySet()) {
            temp.put(entry.getKey(), Arrays.stream(entry.getValue()).collect(Collectors.toSet()));
        }
        return objectToMap(obj, temp, isTrue);
    }

    /**
     * @param obj
     * @param retain 保留字段
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj, String[] retain) {
        return objectToMap(obj, Arrays.stream(retain).collect(Collectors.toSet()), null, null, null);
    }

    /**
     * @param obj
     * @param retain 保留字段
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj, Set<String> retain) {
        return objectToMap(obj, retain, null, null, null);
    }

    /**
     * @param obj
     * @param retain
     * @param retainNot
     * @return
     */
    private static Map<String, Object> objectToMap(Object obj, Set<String> retain, Set<String> retainNot, Map<String, Set<String>> fieldsMap, Boolean isTrue) {

        HashMap<String, Object> def = new HashMap<>();

        if (obj == null) return def;


        if (isTrue != null) {
            return (Map<String, Object>) _objectToMap(obj, fieldsMap, isTrue);
        }


        HashMap map = new HashMap();
        List<Field> fields = new ArrayList<>();

//        if (isTrue != null) {
//            getFields(obj.getClass(), fields, fieldsMap, isTrue);
//        } else {
//            getFields(obj.getClass(), fields, null, null);
//        }
        getFields(obj.getClass(), fields);

        for (Field field : fields) {
            if (field.getName().equals("serialVersionUID")) continue;
            if (retain != null) {
                if (!retain.contains(field.getName())) continue;
            }
            if (retainNot != null) {
                if (retainNot.contains(field.getName())) continue;
            }
            field.setAccessible(true);
            try {
                Object value;
                try {
                    Method readMethod = PropertyUtils.getPropertyDescriptor(obj, field.getName()).getReadMethod();
                    value = readMethod.invoke(obj);
                } catch (Exception e) {
                    log.warn(field.getName() + "没有get方法");
                    value = field.get(obj);
                }
                map.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                log.warn("转map异常", e);
                return def;
            }
        }
        return map;
    }


    private static Object _objectToMap(Object obj, Map<String, Set<String>> mapFields, boolean isTrue) {
        Map<String, Object> map = Maps.newHashMap();
        List<Field> fields = new ArrayList<>();
        getFields(obj.getClass(), fields);

//        if (mapFields.containsKey(obj.getClass().getName())) {
        Set<String> fieldRetain = mapFields.get(obj.getClass().getName());
        for (Field field : fields) {
            if (isTrue && fieldRetain != null) {
                if (!fieldRetain.contains(getFieldName(field))) continue;
            } else if (fieldRetain != null) {
                if (fieldRetain.contains(getFieldName(field))) continue;
            }
            field.setAccessible(true);
            try {
                Object child;
                boolean isList = Iterable.class.isAssignableFrom(field.getType());

                try {
                    Method readMethod = PropertyUtils.getPropertyDescriptor(obj, field.getName()).getReadMethod();
                    child = readMethod.invoke(obj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.warn(field.getName() + "没有get方法");
                    child = field.get(obj);
                }
                if (isList) {
                    Iterable childs = (Iterable) field.get(obj);
                    List<Object> temp = Lists.newArrayList();
                    Iterator iterator = childs.iterator();
                    while (iterator.hasNext()) {
                        Object o1 = _objectToMap(iterator.next(), mapFields, isTrue);
                        temp.add(o1);
                    }
                    child = temp;
                } else {
                    if (child != null && mapFields.containsKey(field.getType().getName())) child = _objectToMap(child, mapFields, isTrue);
                }

                map.put(field.getName(), child);
            } catch (Exception e) {
                log.error("递归转map异常", e);
                return new Object();
            }
        }
        return map;
//        } else {
//            return objectToMap(obj);
//        }
    }

    private static String getFieldName(Field field) {
        return field.getName().substring(field.getName().lastIndexOf(".") + 1);
    }


    //对属性进行递归调用。
    private static void getFields(Class<?> t, List<Field> fields) {
        Field[] declaredFields = t.getDeclaredFields();
        for (Field field : declaredFields) {
            if (Modifier.isFinal(field.getModifiers())) continue;
            if (Modifier.isStatic(field.getModifiers())) continue;
            fields.add(field);
        }
//        Field[] declaredFields = t.getDeclaredFields();
//        fields.addAll(Arrays.asList(declaredFields));
        Class<?> superclass = t.getSuperclass();
        if (superclass == null) return;
        getFields(superclass, fields);
    }


    /**
     * 合并bean
     *
     * @param t   结果bean
     * @param e   源bean
     * @param <T>
     * @return
     */
    public static <T> T mergeBean(Class<T> t, Object... e) {
        T o = null;
        try {
            o = t.newInstance();
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        for (Object temp : e) {
            map.putAll(objectToMap(temp));
        }
        o = mapToObject(map, t);
        return o;
    }


    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {

        T obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            if (map == null) return obj;
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) continue;

                field.setAccessible(true);
                Object value = map.get(field.getName());
                if (value == null) continue;
                if (map instanceof MultiValueMap) value = ((LinkedList) value).get(0);
                field.set(obj, conversionValue(field, value));
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            log.warn("通过json中转一下");
            obj = JsonUtils.toObjDef(JsonUtils.tojSON(map), beanClass);
            return obj;
        }
        return obj;
    }

    private static Object conversionValue(Field f, Object value) {
        if (f.getType() == value.getClass()) return value;

        if (f.getType() == String.class) {
            return value.toString();
        }
        if (f.getType() == BigDecimal.class) {
            return BigDecimal.valueOf(Double.parseDouble(value.toString()));
        }
        return value;
    }

    public static boolean toBool(Object aTrue) {
        if (aTrue == null) return false;
//        org.apache.commons.lang3.ObjectUtils
//        BooleanUtils.toBoolean(aTrue)
        return false;

    }
}
