/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package testProject;

import javaswingdev.main.Main;
import FirebaseProject.FirebaseInitialize;
public class AppTest {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new App().getGreeting());
        Main useTest = new Main();
        useTest.main();
        FirebaseInitialize test1 = new FirebaseInitialize();
        test1.callFunction();
//        System.out.println("Im in haha");
    }
}

