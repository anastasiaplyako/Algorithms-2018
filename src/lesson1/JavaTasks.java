package lesson1;

import kotlin.NotImplementedError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     * <p>
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     * T(n)
     * O(n*log(n))
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        List<String> list = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(inputName));
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
        try {
            FileWriter output = new FileWriter(new File(outputName));
            for (int i = 0; i < list.size(); i++) {
                output.write(list.get(i) + "\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     * R = O(Nlog2N)
     * T = O(n)
     */
    static public void sortTemperatures(String inputName, String outputName) {
        List<String> list = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(inputName));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (Double.parseDouble(line) >= -2730 && Double.parseDouble(line) <= 5000)
                    list.add(line);
                else throw new IllegalArgumentException("IllegalArgument");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double[] a = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = Double.parseDouble(list.get(i));
        }
        quickSort(a);
        try {
            FileWriter output = new FileWriter(new File(outputName));
            for (int i = 0; i < list.size(); i++) {
                output.write(a[i] + "\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Random random = new Random(Calendar.getInstance().getTimeInMillis());

    private static int partition(double[] elements, int min, int max) {
        double x = elements[min + random.nextInt(max - min + 1)];
        int left = min, right = max;
        while (left <= right) {
            while (elements[left] < x) {
                left++;
            }
            while (elements[right] > x) {
                right--;
            }
            if (left <= right) {
                double temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                left++;
                right--;
            }
        }
        return right;
    }

    private static void quickSort(double[] elements, int min, int max) {
        if (min < max) {
            int border = partition(elements, min, max);
            quickSort(elements, min, border);
            quickSort(elements, border + 1, max);
        }
    }

    private static void quickSort(double[] elements) {
        quickSort(elements, 0, elements.length - 1);
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     * R = O(Nlog2N)
     * T = O(n + m), где n = list.size, m - наибольшее количество повторений одного числа
     */
    static public void sortSequence(String inputName, String outputName) {
        List<Integer> list = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        try {
            Scanner scanner = new Scanner(new File(inputName));
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int maxReiter = 1;
        int element = 0;
        for (int i = 0; i < list.size(); i++) {
            Integer number = 1;
            if (map.containsKey(list.get(i))) {
                number = map.get(list.get(i)) + 1;
            }
            if (number > maxReiter || number == maxReiter && list.get(i) < element) {
                maxReiter = number;
                element = list.get(i);
            }
            map.put(list.get(i), number);
        }
        try {
            FileWriter output = new FileWriter(new File(outputName));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != element) {
                    output.write(list.get(i) + "\n");
                }
            }
            for (int i = 0; i < maxReiter; i++) {
                output.write(element + "\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }

}
