package lesson3

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BinaryTreeTest {
    private fun testAdd(create: () -> CheckableSortedSet<Int>) {
        val tree = create()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }

    @Test
    @Tag("Example")
    fun testAddKotlin() {
        testAdd { createKotlinTree() }
    }

    @Test
    @Tag("Example")
    fun testAddJava() {
        testAdd { createJavaTree() }
    }

    private fun <T : Comparable<T>> createJavaTree(): CheckableSortedSet<T> = BinaryTree()

    private fun <T : Comparable<T>> createKotlinTree(): CheckableSortedSet<T> = KtBinaryTree()

    private fun testRemove(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val toRemove = list[random.nextInt(list.size)]
            treeSet.remove(toRemove)
            binarySet.remove(toRemove)
            println("Removing $toRemove from $list")
            assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
            assertEquals(treeSet.size, binarySet.size)
            for (element in list) {
                val inn = element != toRemove
                assertEquals(inn, element in binarySet,
                        "$element should be ${if (inn) "in" else "not in"} tree")
            }
            assertTrue(binarySet.checkInvariant())
        }

        val list1 = mutableListOf<Int>();
        list1.add(5)
        list1.add(1)
        list1.add(2)
        list1.add(3)
        list1.add(4)
        val treeSet = TreeSet<Int>()
        val binarySet = create()
        for (element in list1) {
            treeSet += element
            binarySet += element
        }
        val toRemove = list1[random.nextInt(list1.size)]
        treeSet.remove(toRemove)
        binarySet.remove(toRemove)
        println("Removing $toRemove from $list1")
        assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list1")
        assertEquals(treeSet.size, binarySet.size)
        for (element in list1) {
            val inn = element != toRemove
            assertEquals(inn, element in binarySet,
                    "$element should be ${if (inn) "in" else "not in"} tree")
        }
        assertTrue(binarySet.checkInvariant())

        val list2 = mutableListOf<Int>();
        list2.add(0)
        list2.add(0)
        list2.add(0)
        list2.add(0)
        list2.add(0)
        list2.add(0)
        val treeSet2 = TreeSet<Int>()
        val binarySet2 = create()
        for (element in list1) {
            treeSet2 += element
            binarySet2 += element
        }
        val toRemove2 = list2[random.nextInt(list2.size)]
        treeSet2.remove(toRemove)
        binarySet2.remove(toRemove)
        println("Removing $toRemove2 from $list2")
        assertEquals<SortedSet<*>>(treeSet2, binarySet2, "After removal of $toRemove from $list2")
        assertEquals(treeSet2.size, binarySet2.size)
        for (element in list2) {
            val inn = element != toRemove2
            assertEquals(inn, element in binarySet2,
                    "$element should be ${if (inn) "in" else "not in"} tree")
        }
        assertTrue(binarySet.checkInvariant())
    }

    @Test
    @Tag("Normal")
    fun testRemoveKotlin() {
        testRemove { createKotlinTree() }
    }

    @Test
    @Tag("Normal")
    fun testRemoveJava() {
        testRemove { createJavaTree() }
    }

    private fun testIterator(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
           }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val treeIt = treeSet.iterator()
            val binaryIt = binarySet.iterator()
            println("Traversing $list")
            while (treeIt.hasNext()) {
                assertEquals(treeIt.next(), binaryIt.next())
            }
        }

        val list = mutableListOf<Int>();
        list.add(0)
        list.add(1)
        list.add(5)
        list.add(2)
        list.add(2)
        list.add(8)
        list.add(3)
        list.add(10)
        list.add(15)
        val treeSet = TreeSet<Int>()
        val binarySet = create()
        for (element in list) {
            treeSet += element
            binarySet += element
        }
        val treeIt = treeSet.iterator()
        val binaryIt = binarySet.iterator()
        println("Traversing $list")
        while (treeIt.hasNext()) {
            assertEquals(treeIt.next(), binaryIt.next())
        }

        val list1 = mutableListOf<Int>();
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        val treeSet1 = TreeSet<Int>()
        val binarySet1 = create()
        for (element in list1) {
            treeSet1 += element
            binarySet1 += element
        }
        val treeIt1 = treeSet.iterator()
        val binaryIt1 = binarySet.iterator()
        println("Traversing $list1")
        while (treeIt1.hasNext()) {
            assertEquals(treeIt1.next(), binaryIt1.next())
        }
    }

    @Test
    @Tag("Normal")
    fun testIteratorKotlin() {
        testIterator { createKotlinTree() }
    }

    @Test
    @Tag("Normal")
    fun testIteratorJava() {
        testIterator { createJavaTree() }
    }

    private fun testIteratorRemove(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val toRemove = list[random.nextInt(list.size)]
            treeSet.remove(toRemove)
            println("Removing $toRemove from $list")
            val iterator = binarySet.iterator()
            while (iterator.hasNext()) {
                val element = iterator.next()
                print("$element ")
                if (element == toRemove) {
                    iterator.remove()
                }
            }
            println()
            assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
            assertEquals(treeSet.size, binarySet.size)
            for (element in list) {
                val inn = element != toRemove
                assertEquals(inn, element in binarySet,
                        "$element should be ${if (inn) "in" else "not in"} tree")
            }
            assertTrue(binarySet.checkInvariant())
        }

        val list = mutableListOf<Int>()
        list.add(234);
        list.add(234)
        list.add(128)
        list.add(555)
        list.add(222)
        list.add(333)
        list.add(444)
        list.add(675)
        val treeSet = TreeSet<Int>()
        val binarySet = create()
        for (element in list) {
            treeSet += element
            binarySet += element
        }
        val toRemove = list[random.nextInt(list.size)]
        treeSet.remove(toRemove)
        println("Removing $toRemove from $list")
        val iterator = binarySet.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            print("$element ")
            if (element == toRemove) {
                iterator.remove()
            }
        }
        println()
        assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
        assertEquals(treeSet.size, binarySet.size)
        for (element in list) {
            val inn = element != toRemove
            assertEquals(inn, element in binarySet,
                    "$element should be ${if (inn) "in" else "not in"} tree")
        }
        assertTrue(binarySet.checkInvariant())

        val list1 =mutableListOf<Int>()
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        list1.add(0)
        val treeSet1 = TreeSet<Int>()
        val binarySet1 = create()
        for (element in list) {
            treeSet1 += element
            binarySet1 += element
        }
        val toRemove1 = list1[random.nextInt(list.size)]
        treeSet.remove(toRemove1)
        println("Removing $toRemove1 from $list1")
        val iterator1 = binarySet1.iterator()
        while (iterator1.hasNext()) {
            val element = iterator1.next()
            print("$element ")
            if (element == toRemove1) {
                iterator.remove()
            }
        }
        println()
        assertEquals<SortedSet<*>>(treeSet1, binarySet1, "After removal of $toRemove1 from $list1")
        assertEquals(treeSet1.size, binarySet1.size)
        for (element in list1) {
            val inn = element != toRemove1
            assertEquals(inn, element in binarySet1,
                    "$element should be ${if (inn) "in" else "not in"} tree")
        }
        assertTrue(binarySet.checkInvariant())
    }

    @Test
    @Tag("Hard")
    fun testIteratorRemoveKotlin() {
        testIteratorRemove { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun testIteratorRemoveJava() {
        testIteratorRemove { createJavaTree() }
    }
}