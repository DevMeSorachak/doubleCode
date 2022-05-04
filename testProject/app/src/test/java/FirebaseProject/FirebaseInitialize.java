/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FirebaseProject;

/**
 *
 * @author SUNZDEV
 */

import FirebaseProject.LogTest;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseInitialize {
    
    public static Firestore db;
    
    public FirebaseInitialize() {
        String constructor = "";
    };
    
    public void callFunction() throws Exception {
        initialize();
        LogTest.v();
        retrieveAllDocuments();
     }
    
    public static void initialize() {
    try {
    FileInputStream serviceAccount = new FileInputStream("C:\\Users\\SUNZDEV\\Downloads\\inventory managemnt system\\testProject\\app\\serviceAccount.json");
    FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))        
                .build();

      FirebaseApp.initializeApp(options);
      db = FirestoreClient.getFirestore();
      System.out.println("Connect");
    } catch (Exception e) {
         System.out.println("Not Connect"  + e);
        }
    }
    
    public static boolean insertData (
        String collection,
        String document,
        Map<String, Object> data) {
    try {
        DocumentReference docRef = db.collection(collection).document(document);
        ApiFuture<WriteResult> result = docRef.set(data);
           System.out.println("Up Time "  + result.get().getUpdateTime());
           return true;
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
        return false;
    }
    
    public static void retrieveAllDocuments() throws Exception {
        // เข้าถึง collection ชุด แรก
        // [START fs_get_all]
        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("Post5").get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
          //เข้าถึง DocumentID
          System.out.println("User: " + document.getId());
          //เข้าถึง field
          System.out.println("First: " + document.getString("click"));
          if (document.contains("middle")) {
            System.out.println("Middle: " + document.getString("middle"));
            }
          System.out.println("Last: " + document.getString("last"));
          System.out.println("Born: " + document.getLong("count"));
        }
        System.out.println("y r u jump out");
        
        // asynchronously retrieve multiple documents   
        
        ApiFuture<QuerySnapshot> future = db.collection("Post5").whereEqualTo("eiei", true).get();
        List<QueryDocumentSnapshot> documents2 = future.get().getDocuments();
        // future.get() blocks on response
        for (DocumentSnapshot document2 : documents2) {
            System.out.println(document2.getId() + " => " );
        }
    
        // [END fs_get_all]
    
        // รับ document หลายๆชุด
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future2 = db.collection("Post5").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents3 = future2.get().getDocuments();
        for (QueryDocumentSnapshot document3 : documents3) {
          System.out.println(document3.getId() + " => " );
        }
        // [END firestore_data_get_all_documents]
        // [END fs_get_all_docs]
        System.out.println("y r u jump out");
    }
    
    
    
    public void close() throws Exception {
    db.close();
  }
    
}