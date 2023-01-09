package com.jcpdev.ModernGreenThumb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

import static com.jcpdev.ModernGreenThumb.CONSTANTS.DATABASEURL;


@Service
public class FBInitialize {


    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = (FBInitialize.class.getResourceAsStream("/googlecredentials.json"));
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASEURL)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}