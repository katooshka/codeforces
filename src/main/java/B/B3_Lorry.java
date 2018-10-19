package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B3_Lorry {
    public static void main(String args[]) throws IOException {
        int vehiclesCount;
        int freeSpace;
        List<Kayak> kayaks = new ArrayList<>();
        List<Catamaran> catamarans = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            vehiclesCount = Integer.parseInt(firstArgs[0]);
            freeSpace = Integer.parseInt(firstArgs[1]);
            for (int i = 0; i < vehiclesCount; i++) {
                String[] line = br.readLine().split(" ");
                if (Integer.parseInt(line[0]) == 1) {
                    kayaks.add(new Kayak(i + 1, Integer.parseInt(line[1])));
                } else {
                    catamarans.add(new Catamaran(i + 1, Integer.parseInt(line[1])));
                }
            }
        }
        Collections.sort(kayaks);
        Collections.sort(catamarans);
        int currentCapacity = 0;
        int maxCapacity;
        int resultCatamaran;
        int resultKayak;
        int currentKayak = -1;
        while (freeSpace > 0 && currentKayak < kayaks.size() - 1) {
            currentCapacity = currentCapacity + kayaks.get(currentKayak + 1).capacity;
            freeSpace--;
            currentKayak++;
        }
        int currentCatamaran = -1;
        if (freeSpace > 0) {
            while (freeSpace > 1 && currentCatamaran < catamarans.size() - 1) {
                currentCapacity = currentCapacity + catamarans.get(currentCatamaran + 1).capacity;
                freeSpace = freeSpace - 2;
                currentCatamaran++;
            }
        }

        maxCapacity = currentCapacity;
        resultKayak = currentKayak;
        resultCatamaran = currentCatamaran;
        List<Integer> result = new ArrayList<>();
        if (currentKayak < kayaks.size() - 1 || currentCatamaran < catamarans.size() - 1) {
            if (currentCatamaran == catamarans.size() - 1) {
                for (int i = 0; i < currentCatamaran; i++) {
                    result.add(catamarans.get(i).number);
                }
                for (int i = 0; i < currentKayak; i++) {
                    result.add(kayaks.get(i).number);
                }
            } else {
                while (currentKayak >= 0 && currentCatamaran < catamarans.size() - 1) {
                    currentCapacity = currentCapacity
                            - kayaks.get(currentKayak).capacity
                            + catamarans.get(currentCatamaran + 1).capacity;
                    currentKayak--;
                    freeSpace = freeSpace - 1;
                    if (freeSpace < 0 && currentKayak >= 0) {
                        currentCapacity = currentCapacity - kayaks.get(currentKayak).capacity;
                        currentKayak--;
                        freeSpace++;
                    }
                    currentCatamaran++;if (currentCapacity > maxCapacity) {
                        maxCapacity = currentCapacity;
                        resultCatamaran = currentCatamaran;
                        resultKayak = currentKayak;
                    }
                }
                for (int i = 0; i <= resultCatamaran; i++) {
                    result.add(catamarans.get(i).number);
                }
                for (int i = 0; i <= resultKayak; i++) {
                    result.add(kayaks.get(i).number);
                }
            }
        } else {
            for (Catamaran catamaran : catamarans) {
                result.add(catamaran.number);
            }
            for (Kayak kayak : kayaks) {
                result.add(kayak.number);
            }
        }
        System.out.println(maxCapacity);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }

    static class Kayak implements Comparable<Kayak>{
        int number;
        int capacity;

        public Kayak(int number, int capacity) {
            this.number = number;
            this.capacity = capacity;
        }

        @Override
        public int compareTo(Kayak o) {
            if (this.capacity > o.capacity) {
                return -1;
            } else if (this.capacity < o.capacity) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static class Catamaran implements Comparable<Catamaran>{
        int number;
        int capacity;

        public Catamaran(int number, int capacity) {
            this.number = number;
            this.capacity = capacity;
        }

        @Override
        public int compareTo(Catamaran o) {
            if (this.capacity > o.capacity) {
                return -1;
            } else if (this.capacity < o.capacity) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}