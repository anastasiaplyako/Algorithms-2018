package lesson6;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * трудоемкость = O(n * m)n - длина 1 строки, m - длина 2 строки
     * ресурсоемкость = O(n * m)
     */
    public static String longestCommonSubSequence(String first, String second) {
        String res = "";
        int firstLength = first.length();
        int secondLength = second.length();
        int[][] max = new int[firstLength + 1][secondLength + 1];
        for (int i = 1; i < firstLength + 1; i++) {
            for (int j = 1; j < secondLength + 1; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    max[i][j] = 1 + max[i - 1][j - 1];
                } else max[i][j] = Math.max(max[i - 1][j], max[i][j - 1]);
            }
        }
        int i = firstLength;
        int j = secondLength;
        while (i > 0 && j > 0) {
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                res = first.charAt(i - 1) + res;
                i--;
                j--;
            } else if (max[i][j] == max[i - 1][j]) {
                i--;
            } else j--;
        }
        return res;
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * трудоемкость = O(n^2)
     * ресурсоемкость = O(n)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        List res = new ArrayList<Integer>();
        if (list.isEmpty()) return res;
        int n = list.size();
        int[] p = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = 1;
            p[i] = -5;
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                    p[i] = j;
                }
            }
        }
        int length = 0;
        int pos = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i] > length) {
                pos = i;
                length = d[i];
            }
        }
        while (pos != -5) {
            res.add(0,list.get(pos));
            pos = p[pos];
        }
        return res;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
