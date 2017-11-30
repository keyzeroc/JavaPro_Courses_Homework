package hometask.bank;

import java.util.HashMap;
import java.util.Map;

public class CourseContainer {
    private static final Map<String,Course> coursesMap = new HashMap<String,Course>(){{
        put("UAH-USD",new Course("UAH","USD",0.037387));
        put("UAH-EUR",new Course("UAH","EUR", 0.0312749387));
        put("USD-UAH",new Course("USD","UAH", 26.7472651));
        put("USD-EUR",new Course("USD","EUR", 0.836519077));
        put("EUR-UAH",new Course("EUR","UAH", 31.9744831));
        put("EUR-USD",new Course("EUR","USD", 1.19543));
    }};

    public static Course searchCourse(String from, String to){
        if(from.equals(to)) return new Course(from,to,1.);
        return coursesMap.get(from.trim()+"-"+to.trim());
    }

    public static Map<String, Course> getCoursesMap(){
        return coursesMap;
    }
}
