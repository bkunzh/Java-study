package hello

/**
 *
 * @author  bingkun_zhang
 * @date  2020/8/27
 */
class Greeter(val name: String) {
    fun greet() {
        println("Hello, $name")
    }
}

fun main(args: Array<String>) {
    Greeter("World!").greet()          // 创建一个对象不用 new 关键字
}