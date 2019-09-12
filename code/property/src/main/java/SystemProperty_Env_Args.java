import java.util.Arrays;

/**
 * Created by bkunzhang on 2019/4/10.
 输出：
 ----System.getProperty----
 pro20190410
 ----System.getenv----
 env20190410_a
 C:\Program Files (x86)\Intel\iCLS Client\;C:\Program Files\Intel\iCLS Client\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files\MySQL\MySQL Server 5.5\bin;D:\software\java\bin;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Git\cmd;D:\app\apache-maven-3.5.0\bin;E:\software\TortoiseGit\bin;C:\Users\zhang\AppData\Local\Microsoft\WindowsApps;D:\app\Microsoft VS Code\bin;C:\Users\zhang\AppData\Local\GitHubDesktop\bin
 ----Program arguments: args----
 [abc, --spring.profiles.active=dev, xxx2019, 4444]

 */
public class SystemProperty_Env_Args {
    public static void main(String[] args) {
        System.out.println("----System.getProperty----");
        System.out.println(System.getProperty("myproperty111"));
        System.out.println("----System.getenv----");
        System.out.println(System.getenv("myenv222"));
        System.out.println(System.getenv("path"));
        System.out.println("----Program arguments: args----");
        System.out.println(Arrays.toString(args));
    }
}
