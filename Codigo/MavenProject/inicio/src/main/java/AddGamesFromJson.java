import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddGamesFromJson {
    /*
     * public static void main(String[] args) { //main
     * 
     * String jsonFilePath =
     * "C:/Users/Gaok1/Documents/GitHub/D.A.N.T/MavenProject/inicio/src/main/java/Games.json";
     * // Altere para o caminho do seu arquivo JSON
     * 
     * try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
     * StringBuilder jsonContent = new StringBuilder();
     * String line;
     * while ((line = br.readLine()) != null) {
     * jsonContent.append(line);
     * }
     * 
     * // Analisa o JSON
     * JsonObject jsonObject =
     * JsonParser.parseString(jsonContent.toString()).getAsJsonObject();
     * JsonArray appsArray =
     * jsonObject.getAsJsonObject("applist").getAsJsonArray("apps");
     * 
     * // Conecta-se ao banco de dados
     * GameDAO gameDAO = new GameDAO();
     * 
     * // Loop através dos jogos e insere no banco de dados
     * for (int i = 0; i < appsArray.size(); i++) {
     * JsonObject appObject = appsArray.get(i).getAsJsonObject();
     * int appid = appObject.get("appid").getAsInt();
     * String name = appObject.get("name").getAsString();
     * 
     * // Chama a função de adicionar jogo
     * gameDAO.insertGame(appid, name);
     * }
     * 
     * // Fecha a conexão com o banco de dados
     * gameDAO.closeConnection();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * 
     * }
     */
}
