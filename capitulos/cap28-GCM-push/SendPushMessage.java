

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * @author Ricardo Lecheta
 * 
 */
public class SendPushMessage {

	// Registration id do dispositivo
	private static String DEVICE_REGISTRATION_ID = "APA91bEq6-RpN-ULVI1-J5gxhRjqchSJo3InhHGkyPgbdsXWumsLOs3eOw8Gjtp5Ww-nS25PwGWy76LQezcpeFrcabXAanuar3JOfhHmMCvbEvpX7ILlpDyXYKvon07Tnt-tnng8XPkSUk8I5j4JYg9jhL45uEa5DQ";
	
	// Chave criada no Console. Menu > API Access > (create new server key)
	private static final String API_KEY = "AIzaSyCMCGxjvjeoAmI8jllDH4388EJI4qJFZsU";

	public static void main(String[] args) throws IOException {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("msg", "Olá ao vivo");
		String result = post(API_KEY, DEVICE_REGISTRATION_ID, params );
		System.out.println(result);
	}

	/**
	 * Faz POST no servidor do Google
	 */
	public static String post(String apiKey, String deviceRegistrationId, Map<String, String> params) throws IOException {

		// Parâmetros necessários para o POST
		StringBuilder postBody = new StringBuilder();
		postBody.append("registration_id").append("=").append(deviceRegistrationId);
		
		// Cria os parâmetros chave=valor
		Set<String> keys = params.keySet();
		for (String key : keys) {
			String value = params.get(key);
			postBody.append("&").append("data.").append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
		}

		// Cria a mensagem
		byte[] postData = postBody.toString().getBytes("UTF-8");

		// Faz POST
		URL url = new URL("https://android.googleapis.com/gcm/send");
		HttpsURLConnection.setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
		conn.setRequestProperty("Authorization", "key=" + apiKey);

		// Lê a resposta
		OutputStream out = conn.getOutputStream();
		out.write(postData);
		out.close();

		int responseCode = conn.getResponseCode();
		if(responseCode == 200) {
			// OK
			String response = conn.getResponseMessage();
			return response;
		} else {
			System.err.println(responseCode + ": " + conn.getResponseMessage());
		}
		
		return null;
	}

	private static class CustomizedHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
