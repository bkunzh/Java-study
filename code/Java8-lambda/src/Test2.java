import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by zhbk on 2019/2/23.
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("bb");
        list.add("ab");
        list.add("cd");
        list.add("ca");
        list.add("11");

        //1
//        Collections.sort(list);

        //2
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareTo(s2);
//            }
//        });

        //3 Java8
        Collections.sort(list, (String s1, String s2) -> {
            return s1.compareTo(s2);
        });


        for (String s : list) {
            System.out.println(s);
        }
    }
}
