import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class GetGameJson {

    public static String getGameJSON(int appid) {
        String apiUrl = "http://store.steampowered.com/api/appdetails/?appids=" + appid;

        try {
            URI uri = new URI(apiUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false); // Desabilita o redirecionamento automático

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {

                // Se o código de resposta for 302 (redirecionamento)
                
                String newUrl = connection.getHeaderField("Location"); // Pega a nova URL

                connection = (HttpURLConnection) new URL(newUrl).openConnection(); // Abre conexão com a nova URL

            }
            responseCode = connection.getResponseCode(); // Obtém o código de resposta da nova URL

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                System.out.println("Erro na solicitação HTTP. Código de resposta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao fazer a solicitação HTTP.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao construir a URL.");
        }

        return null;
    }

    public static String GetGameJsonData(int appid) {
        // Substitua pelo appid do jogo desejado
        String gameJSON = getGameJSON(appid);

        if (gameJSON != null) {
            System.out.println("JSON do jogo:");
            System.out.println(gameJSON);
            return gameJSON;
        } else {
            System.out.println("Não foi possível obter o JSON do jogo.");
            return null;
        }
    }
}
