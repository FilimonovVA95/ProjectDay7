package company.name.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OneOffMailPageThroughRequests{

    /**
     * Поля сайта одноразовой почты, время ожидания появления письма, время ожидания проверок наличия элементов,
     * вебдрайвер и ссылка на тестовый стенд
     */
    private static String getUrlToNewEmail = "https://post-shift.ru/api.php?action=new";


    private static int timeWaitEmail = 60;    //сколько секунд будем ждать появление письма в ящике
    private static int timeWait = 10;   //время ожидания при проверке

    private static String email;
    private static String keyEmail;
    private static String password;
    private static int lastIdLetter = 0;

    private OneOffMailPageThroughRequests() {}


    public static String getEmail() { return email; }

    public static String getNewEmail() throws IOException{
        URL url = new URL(getUrlToNewEmail);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(30000);
        connection.setDoOutput(true);

        String line;
        String result = "";

        BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(connection.getInputStream())));

        while ((line = bufferedReader.readLine()) != null) {
            result += line;
            System.out.println(line);
        }
        bufferedReader.close();

        System.out.println(result);

        email = result;   //надо еще распарсить
        keyEmail = result;

       // System.out.println(new String(bytes, UTF_8));



        return email;

    }


}
