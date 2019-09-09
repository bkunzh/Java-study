import org.junit.Test;

public class ClassLoaderTest {

    @Test
    public void t() {
        System.out.println("ClassLoaderTest:" + ClassLoaderTest.class.getClassLoader());
        System.out.println("String:" + String.class.getClassLoader());
        System.out.println("int:" + int.class.getClassLoader());

        System.out.println("ClassLoaderTest parent:" + ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println("ClassLoaderTest parent parent:" + ClassLoaderTest.class.getClassLoader().getParent().getParent());


    }

    @Test
    public void classTest() {
        System.out.println(int.class.getName());
        System.out.println(Integer.class.getName());
    }
}
