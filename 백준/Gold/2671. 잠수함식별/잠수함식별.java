import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        Pattern pattern = Pattern.compile("^(100+1+|01)+$");
        Matcher matcher = pattern.matcher(s);

        System.out.println(matcher.matches() ? "SUBMARINE" : "NOISE");
    }
}
