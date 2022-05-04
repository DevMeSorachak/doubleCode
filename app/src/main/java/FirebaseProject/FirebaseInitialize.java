/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FirebaseProject;

/**
 *
 * @author SUNZDEV
 */

import NewPost.Post;
import NewPost.ShowMyPost;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.ArrayList;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FieldValue;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.main.MyPost;

public class FirebaseInitialize {
    
    public static Firestore db;
    
    public FirebaseInitialize() {
        String constructor = "";
    };
    
    public void callFunction() {
        
        
            initialize();
        
//           getAllPostFromUser();
//           testGetComment();
//           LogTest.v();
     
       
       
        
    }
     
    
    public static void initialize() {
    try {
    FileInputStream serviceAccount = new FileInputStream("serviceAccount.json");
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
    
    
    
    public static boolean insertData(  
        String collection1,
        String document1,
        String collection2,
        String document2,
        Map<String, Object> data)  { 
        {
        try {
//        DocumentReference docRef = db.collection(collection).document(document);
          System.out.println("try connect deepCollection");
          DocumentReference docRef = db.collection(collection1).document(document1).collection(collection2).document(document2);
          ApiFuture<WriteResult> result = docRef.set(data);
           System.out.println("Up Time "  + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
            return false;
        }
    }
    public static boolean setPostOnUser(  
        
        String username,//username
        String uuidAndTitle,//uuid-title
        Map<String, Object> newPost)  { 
        {
        try {
//        DocumentReference docRef = db.collection(collection).document(document);
          System.out.println("try connect deepCollection");
          DocumentReference docRef = db.collection("member").document(username).collection("userPost").document(uuidAndTitle);
          ApiFuture<WriteResult> result = docRef.set(newPost);
           System.out.println("Up Time "  + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
            return false;
        }
    }
    
    public static boolean setPostOnType(  
        String type,
        String uuidAndTitle,
        Map<String, Object> newPost)  { 
        {
        try {
//        DocumentReference docRef = db.collection(collection).document(document);
          System.out.println("try connect deepCollection");
          DocumentReference docRef = db.collection(type).document(uuidAndTitle);
          ApiFuture<WriteResult> result = docRef.set(newPost);
           System.out.println("Up Time "  + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
            return false;
        }
    }
    
    public static boolean createLike(  
        String type,
        String uuid,
        String title,
        Map<String, Object> likes)  { 
        {
        try {
//        DocumentReference docRef = db.collection(collection).document(document);
          System.out.println("try connect deepCollection");
          
          String uuidAndTitle = uuid + "-" + title;
          String uuidAndLike = uuid + "-likes";
          
          DocumentReference docRef = db.collection(type).document(uuidAndTitle).collection("likes").document(uuidAndLike);
          ApiFuture<WriteResult> result = docRef.set(likes);
           System.out.println("Up Time "  + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
            return false;
        }
    }
    
    public static boolean increaseLike(      
        String username,
        String type,
        String uuid,
        String title) throws ExecutionException, InterruptedException{
        boolean check = false;
        
        String uuidAndTitle = uuid + "-" + title;
        String uuidAndLike = uuid + "-likes";
        
        Iterable<CollectionReference> collections =
        db.collection(type).document(uuidAndTitle).listCollections();
        
        for (CollectionReference collRef : collections) {
          
            ApiFuture<QuerySnapshot> queryOnLike = collRef.get();
            
              
            QuerySnapshot querySnapshotOnLike = queryOnLike.get();
            List<QueryDocumentSnapshot> documentsOnLike = querySnapshotOnLike.getDocuments();

            for (QueryDocumentSnapshot documentOnLike : documentsOnLike) {
            
                System.out.println("test get field length: " + documentOnLike.getData().size());
                if (documentOnLike.getId().toString().indexOf("likes") != -1 ) {
                    if (documentOnLike.getData().containsKey(username) != true ){
                        documentOnLike.getReference().update("likes", FieldValue.increment(1));
                        
                        Map<String, Object> userLike = new HashMap<>();
                        userLike.put(username, "");
                        
                        DocumentReference docRef = db.collection(type).document(uuidAndTitle).collection("likes").document(uuidAndLike);
                        ApiFuture<WriteResult> result = docRef.update(userLike);
                        check = true;
                    }
                }
            }
        }
        return check;
    }
    
    public static boolean createComment(  
        String type,
        String uuid,
        String title,
        Map<String, Object> comments)  { 
        {
        try {
//        DocumentReference docRef = db.collection(collection).document(document);
          System.out.println("try connect deepCollection");
          
          String uuidAndTitle = uuid + "-" + title;
          String uuidAndComment = uuid + "-comments";
          
          DocumentReference docRef = db.collection(type).document(uuidAndTitle).collection("comments").document(uuidAndComment);
          ApiFuture<WriteResult> result = docRef.set(comments);
           System.out.println("Up Time "  + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
            return false;
        }
    }
    
    public static void increaseComment(      
        String username,
        String type,
        String uuid,
        String title,
        String comment) throws ExecutionException, InterruptedException{
        
        
        String uuidAndTitle = uuid + "-" + title;
        String uuidAndComment = uuid + "-comments";
        
        Iterable<CollectionReference> collections =
        db.collection(type).document(uuidAndTitle).listCollections();
        
        for (CollectionReference collRef : collections) {
          
            ApiFuture<QuerySnapshot> queryOnComment = collRef.get();
            
              
            QuerySnapshot querySnapshotOnComment = queryOnComment.get();
            List<QueryDocumentSnapshot> documentsOnComment = querySnapshotOnComment.getDocuments();

            for (QueryDocumentSnapshot documentOnComment : documentsOnComment) {
            
                if (documentOnComment.getId().toString().indexOf("comments") != -1 ){
                    System.out.println("test get field length: " + documentOnComment.getData().size());
                              
                    Map<String, Object> newComment = new HashMap<>();
                
                    String keyComment = "comment" + documentOnComment.getData().size();
                
                    String commentInField = username + "-" + comment;
                    newComment.put(keyComment, commentInField);       
                    DocumentReference docRef = db.collection(type).document(uuidAndTitle).collection("comments").document(uuidAndComment);
                    ApiFuture<WriteResult> result = docRef.update(newComment);
                }            
            }
        }
    }
    
    public static void retrieveAllDocuments() {
        try {
            // ��Ҷ֧ collection �ش �á
            // [START fs_get_all]
            // asynchronously retrieve all users
            ApiFuture<QuerySnapshot> query = db.collection("Post5").get();
            // ...
            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                
                //��Ҷ֧ DocumentID
                System.out.println("User: " + document.getId());
                //��Ҷ֧ field
                System.out.println("First: " + document.getString("click"));
                if (document.contains("middle")) {
                    System.out.println("Middle: " + document.getString("middle"));
                }
                System.out.println("Last: " + document.getString("last"));
                System.out.println("Born: " + document.getLong("count"));
            }
            System.out.println("y r u jump out");
            
            // asynchronously retrieve multiple documents
            
            ApiFuture<QuerySnapshot> future = db.collection("Post5").whereEqualTo("Post3", true).get();
            List<QueryDocumentSnapshot> documents2 = future.get().getDocuments();
            // future.get() blocks on response
            for (DocumentSnapshot document2 : documents2) {
                System.out.println(document2.getId() + " => " );
            }
            
            // [END fs_get_all]
            
            // �Ѻ document �����ش
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
            
            // collection --> documet ===> All collection2
            Iterable<CollectionReference> collections =
                    db.collection("Post5").document("click2").listCollections();
            for (CollectionReference collRef : collections) {
                System.out.println("Found subcollection with id: " + collRef.getId());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void testLogin(){
    
    }
    


    
    public static void testGetComment() throws Exception {
        // collection --> documet ===> All collection2
        Iterable<CollectionReference> collections =
        db.collection("Sport").document("WSd1NeURV1t1alUiyNzA-Guy love Jok").listCollections();
        for (CollectionReference collRef : collections) {
          System.out.println("Found subcollection with id: " + collRef.getId());
//          System.out.println(collRef.getParent());
//          System.out.println(collRef.getPath());
//          System.out.println(collRef.document());
//            collRef.get();
        ApiFuture<QuerySnapshot> queryOnComment = collRef.get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshotOnComment = queryOnComment.get();
        List<QueryDocumentSnapshot> documentsOnComment = querySnapshotOnComment.getDocuments();
        
        for (QueryDocumentSnapshot documentOnComment : documentsOnComment) {
          //��Ҷ֧ DocumentID
          System.out.println("test get field length: " + documentOnComment.getData().size());
          if (documentOnComment.getId().toString().indexOf("likes") != -1 ) {
              if (documentOnComment.getData().containsKey("nothingtosay2") != true ){
                  documentOnComment.getReference().update("likes", FieldValue.increment(1));
              }
          }
            }
          
        }  
    
    } 
    
    public static void presentPost(String getUsername, String getUUID, String getType) throws Exception {
         // ��Ҷ֧ collection �ش �á
        // [START fs_get_all]
        // asynchronously retrieve all user
        String title = "";
        String type = "";
        String author = "";
        String detail = "";
        String uuid = "";
        String uuidAndTitle = "";
        String uuidAndComment = "";
        String comment = "";
        String numComment = "";
        String commentInField = "";
        Long like = 0L;
        
        ApiFuture<QuerySnapshot> query = db.collection(getType).get();
        
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
          //��Ҷ֧ DocumentID
          if(document.getId().toString().indexOf(getUUID) != -1){
              title = document.getString("title");
              type = document.getString("type");
              author = document.getString("author");
              detail = document.getString("detailPost");
              uuid = document.getString("uuid");
              
              uuidAndTitle = uuidAndTitle + uuid + "-" + title;
              uuidAndComment = uuid + "-comments";
                              
            }                 
        }
        Iterable<CollectionReference> collections =
        db.collection(type).document(uuidAndTitle).listCollections();
        
        for (CollectionReference collRef : collections) {
          
            ApiFuture<QuerySnapshot> queryOnComment = collRef.get();
            
              
            QuerySnapshot querySnapshotOnComment = queryOnComment.get();
            List<QueryDocumentSnapshot> documentsOnComment = querySnapshotOnComment.getDocuments();

            for (QueryDocumentSnapshot documentOnComment : documentsOnComment) {
            
                if (documentOnComment.getId().toString().indexOf("comments") != -1 ){
                    
                    for(int i = 0; i < documentOnComment.getData().size(); i++){
                        numComment = "comment" + i;
                        commentInField = documentOnComment.getString(numComment);
                        
                        int temp = commentInField.indexOf("-");
                        
                        comment = comment + commentInField.substring(0, temp) + " : " + commentInField.substring(temp + 1) + "\n";                        
                    }         
                }
                if (documentOnComment.getId().toString().indexOf("likes") != -1 ){
                    
                    like = documentOnComment.getLong("likes");
                }  
            }
//          System.out.println("Last: " + document.getString("last"));
//          System.out.println("Born: " + document.getLong("count"));
        }
        Post showPost = new Post();
        showPost.displayPost(getUsername, title, type, author, detail, uuid, comment, like);
        System.out.println(uuidAndTitle);
    }
    public static void presentPostOnMyPost(String getUsername, String getUUID, String getType, MyPost getMyPost) throws Exception {
         // ��Ҷ֧ collection �ش �á
        // [START fs_get_all]
        // asynchronously retrieve all users
        
        String title = "";
        String type = "";
        String author = "";
        String detail = "";
        String uuid = "";
        String uuidAndTitle = "";
        String uuidAndComment = "";
        String comment = "";
        String numComment = "";
        String commentInField = "";
        Long like = 0L;
        
        
        ApiFuture<QuerySnapshot> query = db.collection(getType).get();
        
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
          //��Ҷ֧ DocumentID
          if(document.getId().toString().indexOf(getUUID) != -1){
              title = document.getString("title");
              type = document.getString("type");
              author = document.getString("author");
              detail = document.getString("detailPost");
              uuid = document.getString("uuid");
              
              uuidAndTitle = uuidAndTitle + uuid + "-" + title;
              uuidAndComment = uuid + "-comments";
                              
            }                 
        }
        Iterable<CollectionReference> collections =
        db.collection(type).document(uuidAndTitle).listCollections();
        
        for (CollectionReference collRef : collections) {
          
            ApiFuture<QuerySnapshot> queryOnComment = collRef.get();
            
              
            QuerySnapshot querySnapshotOnComment = queryOnComment.get();
            List<QueryDocumentSnapshot> documentsOnComment = querySnapshotOnComment.getDocuments();

            for (QueryDocumentSnapshot documentOnComment : documentsOnComment) {
            
                if (documentOnComment.getId().toString().indexOf("comments") != -1 ){
                    
                    for(int i = 0; i < documentOnComment.getData().size(); i++){
                        numComment = "comment" + i;
                        commentInField = documentOnComment.getString(numComment);
                        
                        int temp = commentInField.indexOf("-");
                        
                        comment = comment + commentInField.substring(0, temp) + " : " + commentInField.substring(temp + 1) + "\n";                        
                    }         
                }
                if (documentOnComment.getId().toString().indexOf("likes") != -1 ){
                    
                    like = documentOnComment.getLong("likes");
                }  
            }
//          System.out.println("Last: " + document.getString("last"));
//          System.out.println("Born: " + document.getLong("count"));
        }
        ShowMyPost showPost = new ShowMyPost();
        showPost.displayPost(getUsername, title, type, author, detail, uuid, comment, like, getMyPost);
        System.out.println(uuidAndTitle);
    }
    
    
    
    public static ArrayList<ArrayList<String>> getAllPostOfType(String type)   {
        
        
        try {
            ArrayList<ArrayList<String>> ObjectPost = new ArrayList<ArrayList<String>>();
            ApiFuture<QuerySnapshot> query = db.collection(type).get();
            // ...
            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                // ���ҧ ArrayList �� ObjectPostInit {Tile, type , like}
                ArrayList<String> ObjectPostInit = new ArrayList<String>();
                //��Ҷ֧ DocumentID
                //              System.out.println(document.getData()); // �ʴ� Field ������� document ���
                ObjectPostInit.add(type);     // �� Type ŧ� ObjectPostInit
                String tempTitle = document.getId().toString();
                int tempGetString = tempTitle.indexOf("-");
                String subTitleByID = tempTitle.substring(tempGetString + 1);
                ObjectPostInit.add(subTitleByID);      // �� Title ŧ� ObjectPostInit


                String tempTitleUUID = document.getId().toString();
                int tempGetStringUUID = tempTitleUUID.indexOf("-");
                String subTitleByIDUUID = tempTitleUUID.substring(0, tempGetStringUUID);
                ObjectPostInit.add(subTitleByIDUUID);

                //��Ҷ֧ field
                //              System.out.println("uuid: " + document.getString("uuid"));
                //              if (document.contains("detailPost")) {
                //                System.out.println("detailPost: " + document.getString("detailPost"));
                //              }
                // collection --> documet ===> All collection2
                Iterable<CollectionReference> collections =
                        db.collection(type).document(document.getId().toString()).listCollections();
                for (CollectionReference collRef : collections) {
//                    System.out.println("Found subcollection with id: " + collRef.getId());
                    //          System.out.println(collRef.getParent());
                    //          System.out.println(collRef.getPath());
                    //          System.out.println(collRef.document());
                    //            collRef.get();
                    ApiFuture<QuerySnapshot> queryOnLike = collRef.get();
                    // ...
                    // query.get() blocks on response
                    QuerySnapshot querySnapshotOnLike = queryOnLike.get();
                    List<QueryDocumentSnapshot> documentsOnLike = querySnapshotOnLike.getDocuments();
                    for (QueryDocumentSnapshot documentOnLike : documentsOnLike) {
                        String blankStringLike = "likes";
                        if ((documentOnLike.getId()).indexOf(blankStringLike) != -1) {
                            Long getLike = documentOnLike.getLong("likes");
                            String getStringLike = "" + getLike;
                            ObjectPostInit.add(getStringLike);
                           
                        }
                       

                    }
                }
        ObjectPost.add(ObjectPostInit);
            }
            
            return ObjectPost;
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static ArrayList<ArrayList<String>> getRandomPostOfType(String type)   {
        
        
        try {
            ArrayList<ArrayList<String>> ObjectPost = new ArrayList<ArrayList<String>>();
            ApiFuture<QuerySnapshot> query = db.collection(type).get();
            Random rand = new Random();

            // ...
            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            int numberOfRandom = rand.nextInt(documents.size());
            System.out.print(documents.size());
            
            QueryDocumentSnapshot document = documents.get(numberOfRandom); 
                // ���ҧ ArrayList �� ObjectPostInit {Tile, type , like}
                ArrayList<String> ObjectPostInit = new ArrayList<String>();
                //��Ҷ֧ DocumentID
                //              System.out.println(document.getData()); // �ʴ� Field ������� document ���
                ObjectPostInit.add(type);     // �� Type ŧ� ObjectPostInit
                String tempTitle = document.getId().toString();
                int tempGetString = tempTitle.indexOf("-");
                String subTitleByID = tempTitle.substring(tempGetString + 1);
                ObjectPostInit.add(subTitleByID);      // �� Title ŧ� ObjectPostInit


                String tempTitleUUID = document.getId().toString();
                int tempGetStringUUID = tempTitleUUID.indexOf("-");
                String subTitleByIDUUID = tempTitleUUID.substring(0, tempGetStringUUID);
                ObjectPostInit.add(subTitleByIDUUID);

                //��Ҷ֧ field
                //              System.out.println("uuid: " + document.getString("uuid"));
                //              if (document.contains("detailPost")) {
                //                System.out.println("detailPost: " + document.getString("detailPost"));
                //              }
                // collection --> documet ===> All collection2
                Iterable<CollectionReference> collections =
                        db.collection(type).document(document.getId().toString()).listCollections();
                for (CollectionReference collRef : collections) {
//                    System.out.println("Found subcollection with id: " + collRef.getId());
                    //          System.out.println(collRef.getParent());
                    //          System.out.println(collRef.getPath());
                    //          System.out.println(collRef.document());
                    //            collRef.get();
                    ApiFuture<QuerySnapshot> queryOnLike = collRef.get();
                    // ...
                    // query.get() blocks on response
                    QuerySnapshot querySnapshotOnLike = queryOnLike.get();
                    List<QueryDocumentSnapshot> documentsOnLike = querySnapshotOnLike.getDocuments();
                    for (QueryDocumentSnapshot documentOnLike : documentsOnLike) {
                        String blankStringLike = "likes";
                        if ((documentOnLike.getId()).indexOf(blankStringLike) != -1) {
                            Long getLike = documentOnLike.getLong("likes");
                            String getStringLike = "" + getLike;
                            ObjectPostInit.add(getStringLike);
                           
                        }
                       

                    }
                }
        ObjectPost.add(ObjectPostInit);
            
            
         return ObjectPost;
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
        
    public static void DeletePost(String getUserName,String getUUID, String getType) throws InterruptedException   {
        try {
            String username = getUserName;
             ApiFuture<QuerySnapshot> query = db.collection("member").get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            //��Ҷ֧ DocumentID
            if (document.getId().toString().equalsIgnoreCase(username)) {
                    Iterable<CollectionReference> collections =
                        db.collection("member").document(document.getId().toString()).listCollections();
                        for (CollectionReference collRef : collections) {
                          System.out.println("Found subcollection with id: " + collRef.getId());
                //          System.out.println(collRef.getParent());
                //          System.out.println(collRef.getPath());
                //          System.out.println(collRef.document());
                //            collRef.get();
                        ApiFuture<QuerySnapshot> queryOnPost = collRef.get();
                        // ...
                        // query.get() blocks on response
                        QuerySnapshot querySnapshotOnPost = queryOnPost.get();
                        List<QueryDocumentSnapshot> documentsOnPost= querySnapshotOnPost.getDocuments();
                        
                        for (QueryDocumentSnapshot documentOnPost : documentsOnPost) {
                          //��Ҷ֧ DocumentID\
                          if (documentOnPost.getId().toString().indexOf(getUUID) != -1){
                                documentOnPost.getReference().delete();
                                System.out.println("Delete user Post" + getUUID);
                          }
                         
                         }

                       }
            
            }

          
          }
        
        // deletePost At Type
        ApiFuture<QuerySnapshot> queryType = db.collection(getType).get();
        QuerySnapshot querySnapshotType = queryType.get();
         List<QueryDocumentSnapshot> documentsTypes = querySnapshotType.getDocuments();
         for (QueryDocumentSnapshot documentsType : documentsTypes) {
            //��Ҷ֧ DocumentID
            if (documentsType.getId().toString().indexOf(getUUID) != -1 ) {
                    Iterable<CollectionReference> collections =
                        db.collection(getType).document(documentsType.getId().toString()).listCollections();
                        for (CollectionReference collRef : collections) {
                          System.out.println("Found subcollection with id: " + collRef.getId());
                //          System.out.println(collRef.getParent());
                //          System.out.println(collRef.getPath());
                //          System.out.println(collRef.document());
                //            collRef.get();
                        ApiFuture<QuerySnapshot> queryOnType = collRef.get();
                        // ...
                        // query.get() blocks on response
                        QuerySnapshot querySnapshotOnType = queryOnType.get();
                        List<QueryDocumentSnapshot> documentsOnType= querySnapshotOnType.getDocuments();
                        
                        for (QueryDocumentSnapshot documentOnType : documentsOnType) {
                          //��Ҷ֧ DocumentID\
                          if (documentOnType.getId().toString().indexOf(getUUID) != -1){
                                documentOnType.getReference().delete();
                                System.out.println("Delete likes ");
                          }
                         
                         }
                        
                       }
                        
                       
            documentsType.getReference().delete();
            System.out.println("Delete Post At Type By UUID");
            }

          
          }
        
        } catch (ExecutionException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    public static void ShowPost(String getUUID, String getType) {
        
    }
    
    
    public static  ArrayList<String> getAllPostFromUser(String getUsername) throws ExecutionException {
         ArrayList<String> ObjectPost = new ArrayList<String>();
        try {
                String username = getUsername;
            
            ApiFuture<QuerySnapshot> query = db.collection("member").get();
            // ...
            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //��Ҷ֧ DocumentID
                if (document.getId().toString().equalsIgnoreCase(username)) {
                    Iterable<CollectionReference> collections =
                            db.collection("member").document(document.getId().toString()).listCollections();
                    for (CollectionReference collRef : collections) {
                        System.out.println("Found subcollection with id: " + collRef.getId());
                        //          System.out.println(collRef.getParent());
                        //          System.out.println(collRef.getPath());
                        //          System.out.println(collRef.document());
                        //            collRef.get();
                        ApiFuture<QuerySnapshot> queryOnPost = collRef.get();
                        // ...
                        // query.get() blocks on response
                        QuerySnapshot querySnapshotOnPost = queryOnPost.get();
                        List<QueryDocumentSnapshot> documentsOnPost= querySnapshotOnPost.getDocuments();
                        for (QueryDocumentSnapshot documentOnPost : documentsOnPost) {
                            //��Ҷ֧ DocumentID\
                            
                           System.out.println("title: " + documentOnPost.getString("title"));
                           System.out.println("type: " + documentOnPost.getString("type"));
                           System.out.println("uuid: " + documentOnPost.getString("uuid"));
                           ObjectPost.add(documentOnPost.getString("title"));
                           ObjectPost.add(documentOnPost.getString("type"));
                           ObjectPost.add(documentOnPost.getString("uuid"));
                        }
                        
                    }
                    
                }
                
                
            }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println(ObjectPost);
    return ObjectPost;
    }
    
    public static int registerUser(String email, 
            String username,
            Map<String, Object> newUser) throws Exception{
        ApiFuture<QuerySnapshot> query = db.collection("member").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents){
            if(document.getString("email").toLowerCase().equals(email.toLowerCase())){
                return 1;
            }
            if(document.getString("username").toLowerCase().equals(username.toLowerCase())){
                return 2;

            }
        }
        try {
//        DocumentReference docRef = db.collection(collection).document(document);
          System.out.println("try connect deepCollection");
          DocumentReference docRef = db.collection("member").document(username.toLowerCase());
          ApiFuture<WriteResult> result = docRef.set(newUser);
           System.out.println("Up Time "  + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println("Not Connect"  + e);
        }
        return 0;

    }

    public static int userLogin(String username, String password) throws Exception{
        ApiFuture<QuerySnapshot> query = db.collection("member").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents){
            if(document.getId().toString().toLowerCase().equals(username.toLowerCase())){
                if(document.getString("password").equals(password)){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        }
        return 2;
    }
    
    
 
}