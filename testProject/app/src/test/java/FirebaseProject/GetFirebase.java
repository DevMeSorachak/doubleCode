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
import FirebaseProject.FirebaseInitialize;
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
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;
import java.lang.InterruptedException;

public class GetFirebase {
    private String id;


    public String getId() {
        return id;
    }

  
    
    
    public GetFirebase(String id,String number){
        this.id = id;
    }
    
    public void getData() throws InterruptedException, ExecutionException {
        
        List<GetFirebase> listaGetFirebase = new ArrayList<GetFirebase>();
        
        CollectionReference getFirst = FirebaseInitialize.db.collection("Post5");
        ApiFuture<QuerySnapshot> querySnapshot = getFirst.get();
        
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            GetFirebase getdata = new GetFirebase(document.getId(),
            document.getString("click"));
            System.out.println(document.getId());
            listaGetFirebase.add(getdata);
        }
    }
    

}