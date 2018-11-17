package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {

        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     *  Ресурсоемкость - O(h)
     *  Трудоемкость - O(h)
     *  Задача решена на основе алгоритма https://neerc.ifmo.ru/wiki/index.php?title=Дерево_поиска,_наивная_реализация
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        T t = (T) o;
        Node<T> res = deleted(root, t);
        size--;
        return (res != null);
    }

    private Node<T> deleted(Node<T> root, T t) {
        Node<T> node = root;
        int compare = t.compareTo(root.value);
        if (compare < 0) {
            node.left = deleted(node.left, t);
        } else if (compare > 0) {
            node.right = deleted(node.right, t);
        } else if (node.right != null) {
            node.value = firstNode(node.right).value;
            node.right = deleted(node.right, node.value);
        } else {
            if (node.left != null) {
                node.value = lastNode(node.left).value;
                node.left = deleted(node.left, node.value);
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private Node<T> firstNode(Node<T> node) {
        if (node.left == null) return node;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<T> lastNode(Node<T> node) {
        if (node.right == null) return node;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        /**
         * Поиск следующего элемента
         * Средняя
         *  Ресурсоемкость - O(h)
         *  Трудоемкость - O(h)
         */
        private Node<T> findNext() {
            Node<T> node = current ;
            if (current != null) {
                node = current;
            } else return minimum();
            Node<T> res = current.right;
            if (res != null) {
                while (res.left != null) {
                    res = res.left;
                }
                return res;
            } else {
                Node<T> parent = null;
                if (root != null) {
                    parent = root;
                    res = root;
                }
                res = findParent(parent, node);
            }
            return res;
        }

        private Node<T> minimum() {
            if (root == null) throw new NoSuchElementException();
            Node<T> current = root;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        private Node<T> findParent(Node<T> parent, Node<T> node) {
            Node<T> res = null;
            while (parent != node && parent != null) {
                int x = node.value.compareTo(parent.value);
                if (x < 0) {
                    res = parent;
                    parent = parent.left;
                } else {
                    parent = parent.right;
                }
            }
            return res;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         *  Ресурсоемкость - O(h)
         *  Трудоемкость - O(h)
         * Сложная
         * рекурсия - делаем через root (или можно иначе?)
         * без рекурсии - треуется родитель. Определять в отдельном методе?
         * добавить границы в binary tree, входит ли в границу
         */
        @Override
        public void remove() {
     BinaryTree.this.remove(current.value);
        }
    }


    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     * Ресурсоемкость - O(h)
     * Трудоемкость - O(h)
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        SortedSet res = new TreeSet();
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (iterator.next().compareTo(toElement) < 0 && value.compareTo(fromElement) > 0) {
                res.add(value);
            }
        }
        return res;
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     *  Ресурсоемкость - O(h)
     *  Трудоемкость - O(h)
     */

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        SortedSet setList = new TreeSet();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(toElement) < 0) {
                setList.add(value);
            }
        }
        return setList;
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     *  Ресурсоемкость - O(h)
     *  Трудоемкость - O(h)
     * Сложная
     *
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        SortedSet setList = new TreeSet();
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(fromElement) > 0 || value.compareTo(fromElement) == 0) {
                setList.add(value);
            }
        }
        return setList;
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}

