package com.jcpdev.ModernGreenThumb.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.jcpdev.ModernGreenThumb.Model.MoistureTracker;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class MoistureTrackerService {
    public static final String COL_NAME = "Users";

    public String addTracker(Map<String, String> json, String userId) throws InterruptedException, ExecutionException {
        MoistureTracker moistureTracker = new MoistureTracker(json.get("name"));
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(userId).collection("trackers").document(moistureTracker.getName()).set(moistureTracker);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateTrackerValues(Map<String, String> json, String userId) throws InterruptedException, ExecutionException {
        String name = json.get("name");
        int value = Integer.parseInt(json.get("value"));
        String timeStamp = new SimpleDateFormat("EEE, MMM d, h:mm a, z").format(new java.util.Date());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(userId).collection("trackers").document(name).update("value", value, "lastTimeChecked", timeStamp);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateTrackerImage(Map<String, String> json, String userId) throws InterruptedException, ExecutionException {
        String name = json.get("name");
        String imageUrl = json.get("thumbnailUrl");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(userId).collection("trackers").document(name).update("thumbnailUrl", imageUrl);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }


    public JSONObject getPlanters(String userId) throws InterruptedException, ExecutionException {
        JSONObject jsonObject = new JSONObject();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(userId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ApiFuture<QuerySnapshot> trackerSnapshot = dbFirestore.collection(COL_NAME).document(userId).collection("trackers").get();

        if (document.exists() && trackerSnapshot.get().size() != 0) {
            List<QueryDocumentSnapshot> documents = trackerSnapshot.get().getDocuments();
            List<Map> plantersList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : documents) {
                plantersList.add(documentSnapshot.getData());
            }
            jsonObject.put("planters", plantersList);
        }
        return jsonObject;
    }
}
