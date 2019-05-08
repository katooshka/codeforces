package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B1008_TurnTheRectangles {

    public static void main(String[] args) throws IOException {
        int count;
        List<Rect> rects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                String[] arguments = br.readLine().split(" ");
                rects.add(new Rect(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1])));
            }
        }
        int previous = rects.get(0).maxHeight;
        for (int i = 0; i < rects.size() - 1; i++) {
            Rect currentRect = rects.get(i + 1);
            if (previous < currentRect.maxHeight && previous < currentRect.minHeight) {
                System.out.println("NO");
                return;
            } else if (previous < currentRect.maxHeight && previous >= currentRect.minHeight) {
                previous = currentRect.minHeight;
            } else {
                previous = currentRect.maxHeight;
            }
        }
        System.out.println("YES");
    }

    static class Rect {
        int minHeight;
        int maxHeight;

        public Rect(int width, int height) {
            this.minHeight = Math.min(width, height);
            this.maxHeight = Math.max(width, height);
        }
    }
 }
