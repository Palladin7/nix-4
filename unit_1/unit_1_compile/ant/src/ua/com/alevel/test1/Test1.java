package ua.com.alevel.test1;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import ua.com.alevel.test2.Test2;

public class Test1 {

    public void run() {
        System.out.println("Hello world 1");
        BidiMap bidiMap = new DualHashBidiMap();
        bidiMap.put("One", 1);

        Test2 test2 = new Test2();
        test2.run();
    }
}
