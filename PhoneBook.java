import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

public class PhoneBook {

    // Книга создаётся как HashMap, где ключи - строка (имя), а значения - ArrayList строк (список номеров телефонов)
    public HashMap<String, ArrayList<String>> book = new HashMap<>();

    // Добавление человека и номера телефона
    public void add(String name, String number) {
        if (book.containsKey(name)) {
            ArrayList<String> extracted = book.get(name);
            extracted.add(number);
            book.put(name, extracted);
        }
        else {
            ArrayList<String> list = new ArrayList<>();
            list.add(number);
            book.put(name, list);
        }
    }

    // Вывод на экран с сортировкой
    public void printSorted() {

        ArrayList<String> temp = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : book.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append(";;;");
            sb.append(entry.getValue().toString());
            sb.append(";;;");
            sb.append(entry.getValue().size());
            temp.add(new String(sb));
        }

        boolean isSorted = false;
        String buf;
        while (!isSorted) {
            for (int i = 0; i<temp.size()-1; i++) {
                if (Integer.parseInt(temp.get(i).split(";;;")[temp.get(i).split(";;;").length-1]) < Integer.parseInt(temp.get(i+1).split(";;;")[temp.get(i+1).split(";;;").length-1])) {
                    isSorted = false;

                    buf = temp.get(i);
                    temp.add(i, temp.get(i+1));
                    temp.add(i+1, buf);
                }
            }
        }

        Collections.reverse(temp);

        HashMap<String, ArrayList<String>> sortedBook = new HashMap<>();

        for (int i = 0; i<temp.size(); i++) {
            ArrayList<String> splitted = new ArrayList<>();
            for (String elem : temp.get(i).split(";;;")) {
                splitted.add(elem);
            }
            splitted.remove(splitted.size()-1);
            ArrayList<String> splittedPhonesOnly = splitted;
            splittedPhonesOnly.remove(0);
            sortedBook.put(splitted.get(0), splittedPhonesOnly);
        }

        for (Map.Entry<String, ArrayList<String>> entry : sortedBook.entrySet()) {
            System.out.printf("Имя: %s\nНомера телефонов: %s\n\n", entry.getKey(), String.join(", ", entry.getValue()));
        }

    }

}
