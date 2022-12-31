package com.jcpdev.ModernGreenThumb.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.jcpdev.ModernGreenThumb.Model.MoistureTracker;
import com.jcpdev.ModernGreenThumb.Model.User;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    public static final String COL_NAME = "Users";

    public String saveUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getToken()).set(user.getName());
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String addTracker(Map<String, String> json, String userId) throws InterruptedException, ExecutionException {
        MoistureTracker moistureTracker = new MoistureTracker(json.get("name"));
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(userId).collection("trackers").document(String.valueOf(moistureTracker.getName())).set(moistureTracker);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public JSONObject getPlanters(String userId) throws InterruptedException, ExecutionException {
        JSONObject jsonObject = new JSONObject();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(userId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ApiFuture<QuerySnapshot> trackerSnapshot = dbFirestore.collection(COL_NAME).document(userId).collection("trackers").get();

        if (document.exists()&&trackerSnapshot.get().size()!=0) {
            List<QueryDocumentSnapshot> documents = trackerSnapshot.get().getDocuments();
            List<Map> plantersList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot: documents){
                plantersList.add(documentSnapshot.getData());
            }
            jsonObject.put("planters",plantersList);
        }
        return jsonObject;
    }

    public String updateUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "User with name " + name + " has been deleted";
    }

}
