package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A5_ChatServersOutgoingTraffic {
    public static void main(String[] args) throws IOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (String s = br.readLine(); s != null; s = br.readLine()){
                strings.add(s);
            }
        }
        int traffic = 0;
        int membersCount = 0;
        for (String string : strings) {
            if (string.charAt(0) == '+') {
                membersCount++;
            } else if (string.charAt(0) == '-') {
                membersCount--;
            } else {
                String[] nameAndMsg = string.split(":");
                if (nameAndMsg.length == 2) {
                    traffic += nameAndMsg[1].length() * membersCount;
                }
            }
        }
        System.out.println(traffic);
    }
}
