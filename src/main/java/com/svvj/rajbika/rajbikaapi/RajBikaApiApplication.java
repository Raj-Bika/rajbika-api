package com.svvj.rajbika.rajbikaapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class RajBikaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RajBikaApiApplication.class, args);
	}

	@Bean
	public FirebaseAuth firebaseAuth() throws IOException {
		FileInputStream serviceAccount = new FileInputStream("D:\\Project\\Java\\raj-bika-api\\rajbikaapi\\src\\main\\resources\\rajbika-store-firebase-adminsdk-qbl75-f270a449b1.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);

		return FirebaseAuth.getInstance();
	}

}
