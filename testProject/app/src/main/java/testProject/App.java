/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package testProject;

import FirebaseProject.FirebaseInitialize;
import login.login.splash;
        
        
public class App {
    public String getGreeting() {
        return "Hello  จ้ก!";
    }

    public static void main(String[] args) throws Exception {
            

            FirebaseInitialize tempServer = new FirebaseInitialize();
            tempServer.callFunction();
            splash test = new splash();
            test.main();
            

        
    }
}
