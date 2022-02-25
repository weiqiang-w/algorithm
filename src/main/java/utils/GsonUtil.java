//package utils;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class GsonUtil {
//    private static Gson gson = new Gson();
//
//    public static <T> T from(String s, Class<T> clazz) {
//        return gson.fromJson(s, clazz);
//    }
//
//
//    public static Map<String, String> toMap(String s) {
//        Map<String, String> map = new HashMap<String, String>();
//        if (StringUtils.isBlank(s)) {
//            return map;
//        }
//        Type type = new TypeToken<Map<String, String>>() {
//        }.getType();
//
//        map = gson.fromJson(s, type);
//        return map;
//    }
//
//    public static <T> List<T> toList(String s, Class<T> clazz) {
//        Type type = new TypeToken<ArrayList<JsonElement>>() {
//        }.getType();
//        ArrayList<T> listOfT = new ArrayList<>();
//        if (StringUtils.isBlank(s)) {
//            return listOfT;
//        }
//        ArrayList<JsonElement> jsonObjs = new Gson().fromJson(s, type);
//        if (Objects.isNull(jsonObjs)) {
//            return listOfT;
//        }
//
//        for (JsonElement jsonObj : jsonObjs) {
//            listOfT.add(new Gson().fromJson(jsonObj, clazz));
//        }
//        return listOfT;
//
//    }
//
//    public static <T> HashSet<T> toSet(String s, Class<T> clazz) {
//        Type type = new TypeToken<HashSet<JsonElement>>() {
//        }.getType();
//        HashSet<T> hashSet = new HashSet<>();
//        if (StringUtils.isBlank(s)) {
//            return hashSet;
//        }
//        HashSet<JsonElement> jsonObjs = new Gson().fromJson(s, type);
//
//        for (JsonElement jsonObj : jsonObjs) {
//            hashSet.add(new Gson().fromJson(jsonObj, clazz));
//        }
//        return hashSet;
//
//    }
//
//
//    public static HashSet<Long> toSet(String s) {
//        Type type = new TypeToken<HashSet<Long>>() {
//        }.getType();
//        HashSet<Long> hashSet = new HashSet<Long>();
//        if (StringUtils.isBlank(s)) {
//            return hashSet;
//        }
//        HashSet<Long> jsonObjs = new Gson().fromJson(s, type);
//        if (CollectionUtils.isEmpty(jsonObjs)) {
//            return hashSet;
//        }
//
//        hashSet.addAll(jsonObjs);
//        return hashSet;
//
//    }
//
//    public static String toGson(Object object) {
//        return gson.toJson(object);
//    }
//
//
//    public static String toGsonSkipEmptyCollection(Object object) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonStr = mapper.writeValueAsString(object);
//        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonStr);
//        return new YAMLMapper().writeValueAsString(jsonNodeTree);
//    }
//}
