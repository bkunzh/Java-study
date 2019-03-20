import java.util.concurrent.CountDownLatch;

/**
 * Created by zhbk on 2019/3/20.
 * 见http://www.jasongj.com/java/threadlocal/
 * 分析的结果：
 Thread name:thread-1 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy1, Value:0
 Thread name:thread-1 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy1, Value:01
 Thread name:thread-1 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy1, Value:012
 Thread name:thread-1 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy1, Value:0123

 Set, Thread name:thread-1 , ThreadLocal hashcode:0xxx1,  Instance hashcode:0xyy2, Value:hello world

 Thread name:thread-2 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy11, Value:0
 Thread name:thread-2 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy11, Value:01
 Thread name:thread-2 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy11, Value:012
 Thread name:thread-2 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy11, Value:0123

 Set, Thread name:thread-2 , ThreadLocal hashcode:0xxx1,  Instance hashcode:0xyy22, Value:hello world

 Thread name:thread-3 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy111, Value:0
 Thread name:thread-3 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy111, Value:01
 Thread name:thread-3 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy111, Value:012
 Thread name:thread-3 , ThreadLocal hashcode:0xxx1, Instance hashcode:0xyy111, Value:0123

 Set, Thread name:thread-3 , ThreadLocal hashcode:0xxx1,  Instance hashcode:0xyy222, Value:hello world


 * 实际结果
 * 多线程输出不确定：
 Thread name:thread - 3 , ThreadLocal hashcode:2056753021, Instance hashcode:1951432287, Value:0
 Thread name:thread - 1 , ThreadLocal hashcode:2056753021, Instance hashcode:457127325, Value:0
 Thread name:thread - 2 , ThreadLocal hashcode:2056753021, Instance hashcode:89739500, Value:0
 Thread name:thread - 1 , ThreadLocal hashcode:2056753021, Instance hashcode:457127325, Value:01
 Thread name:thread - 3 , ThreadLocal hashcode:2056753021, Instance hashcode:1951432287, Value:01
 Thread name:thread - 1 , ThreadLocal hashcode:2056753021, Instance hashcode:457127325, Value:012
 Thread name:thread - 2 , ThreadLocal hashcode:2056753021, Instance hashcode:89739500, Value:01
 Thread name:thread - 1 , ThreadLocal hashcode:2056753021, Instance hashcode:457127325, Value:0123
 Thread name:thread - 3 , ThreadLocal hashcode:2056753021, Instance hashcode:1951432287, Value:012
 Set, Thread name:thread - 1 , ThreadLocal hashcode:2056753021,  Instance hashcode:368629898, Value:hello world
 Thread name:thread - 2 , ThreadLocal hashcode:2056753021, Instance hashcode:89739500, Value:012
 Thread name:thread - 3 , ThreadLocal hashcode:2056753021, Instance hashcode:1951432287, Value:0123
 Thread name:thread - 2 , ThreadLocal hashcode:2056753021, Instance hashcode:89739500, Value:0123
 Set, Thread name:thread - 3 , ThreadLocal hashcode:2056753021,  Instance hashcode:243889051, Value:hello world
 Set, Thread name:thread - 2 , ThreadLocal hashcode:2056753021,  Instance hashcode:194948663, Value:hello world
 继续...
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        InnerClass innerClass = new InnerClass();
        for(int i = 1; i <= threads; i++) {
            new Thread(() -> {
                for(int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();
        System.out.println("继续...");

    }

    private static class InnerClass {

        public void add(String newStr) {
            StringBuilder str = Counter.counter.get();
//            Counter.counter.set(str.append(newStr)); //没必要set，像下面一行就行
            str.append(newStr);

        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        public void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

    }

}
