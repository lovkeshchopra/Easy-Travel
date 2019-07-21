package com.easytravel.utils;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import net.minidev.json.JSONObject;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import com.easytravel.responseDTO.LoginDTO;
import com.easytravel.service.impl.HeaderRequestInterceptor;

@Async
public class NotificationUtil {

	private static final String FIREBASE_SERVER_KEY = "AAAAghJLZzQ:APA91bFLdQGFMq_O29rnB8MYgeaZ4fzraINCRXbnt_TbwqwV_Tg6w5FZ1prv8f9zE5ogniv0NbglqmVMwN85xXYb09gSJp0eqQA0EoTexQSNuV9s5-ohfsVnz-YppLIAmWTlfPbg7Hac";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	public static CompletableFuture<String> send(LoginDTO request) {

		JSONObject body = new JSONObject();
		body.put("to", request.getDeviceToken());
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", request.getTitle());
		notification.put("body", request.getNotificationBody());

		JSONObject data = new JSONObject();
		data.put("title", request.getTitle());
		data.put("body", request.getNotificationBody());

		body.put("notification", notification);
		body.put("data", data);

		RestTemplate restTemplate = new RestTemplate();

		/**
		 * https://fcm.googleapis.com/fcm/send Content-Type:application/json
		 * Authorization:key=FIREBASE_SERVER_KEY
		 */

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, body, String.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}

}
