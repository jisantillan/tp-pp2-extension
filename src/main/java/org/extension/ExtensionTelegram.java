package org.extension;

import org.domingus.interfaces.NotificationPlatform;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExtensionTelegram implements NotificationPlatform {

    private static final String TELEGRAM_BOT_TOKEN = "7785419552:AAElsFSdQPqIGNU_jIBeE0QUQvhRQzT67UI";
    private static final String TELEGRAM_CHAT_ID = "-1002389775922";  // Reemplaza con tu chat_id

    public void sendMessage(String message)  {
        try {
            String apiUrl = "https://api.telegram.org/bot" + TELEGRAM_BOT_TOKEN + "/sendMessage";
            String urlParameters = "chat_id=" + TELEGRAM_CHAT_ID + "&text=" + java.net.URLEncoder.encode(message, "UTF-8");

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(urlParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Mensaje enviado correctamente a Telegram");
            } else {
                System.out.println("Error al enviar el mensaje a Telegram: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
