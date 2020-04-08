package alibaba;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String item = iterator.next();
//            if ("1".equals(item)) {
//                iterator.remove();
//            }
//        }

//        for (String item : list) {
//            if ("2".equals(item)) {
//                list.remove(item);
//            }
//        }

        for (int i = 0; i < list.size(); i++) {
            if ("1".equals(list.get(i))) {
                iterator.remove();
            }
        }
        for (String item : list) {
            System.out.println(item);
        }
    }

}
