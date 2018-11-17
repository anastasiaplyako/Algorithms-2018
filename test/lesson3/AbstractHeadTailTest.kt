package lesson3

import java.util.SortedSet
import kotlin.test.*

abstract class AbstractHeadTailTest {
    private lateinit var tree: SortedSet<Int>

    protected fun fillTree(empty: SortedSet<Int>) {
        this.tree = empty
        //В произвольном порядке добавим числа от 1 до 10
        tree.add(5)
        tree.add(1)
        tree.add(2)
        tree.add(7)
        tree.add(9)
        tree.add(10)
        tree.add(8)
        tree.add(4)
        tree.add(3)
        tree.add(6)
    }

    protected fun doHeadSetTest() {
        var set: SortedSet<Int> = tree.headSet(5)
        assertEquals(true, set.contains(1))
        assertEquals(true, set.contains(2))
        assertEquals(true, set.contains(3))
        assertEquals(true, set.contains(4))
        assertEquals(false, set.contains(5))
        assertEquals(false, set.contains(6))
        assertEquals(false, set.contains(7))
        assertEquals(false, set.contains(8))
        assertEquals(false, set.contains(9))
        assertEquals(false, set.contains(10))
        set = tree.headSet(12)
        for (i in 1..10)
            assertEquals(true, set.contains(i))


        var set1: SortedSet<Int> = tree.headSet(3)
        assertEquals(true, set1.contains(1))
        assertEquals(true, set1.contains(2))
        assertEquals(false, set1.contains(3))
        assertEquals(false, set1.contains(4))
        assertEquals(false, set1.contains(5))
        assertEquals(false, set1.contains(6))
        assertEquals(false, set1.contains(7))
        assertEquals(false, set1.contains(8))
        assertEquals(false, set1.contains(9))
        assertEquals(false, set1.contains(10))
        set1 = tree.headSet(12)
        for (i in 1..10)
            assertEquals(true, set1.contains(i))

        var set2: SortedSet<Int> = tree.headSet(11)
        assertEquals(true, set1.contains(1))
        assertEquals(true, set1.contains(2))
        assertEquals(true, set1.contains(3))
        assertEquals(true, set1.contains(4))
        assertEquals(true, set1.contains(5))
        assertEquals(true, set1.contains(6))
        assertEquals(true, set1.contains(7))
        assertEquals(true, set1.contains(8))
        assertEquals(true, set1.contains(9))
        assertEquals(true, set1.contains(10))
        set2 = tree.headSet(12)
        for (i in 1..10)
            assertEquals(true, set2.contains(i))
    }

    protected fun doTailSetTest() {
        var set: SortedSet<Int> = tree.tailSet(5)
        assertEquals(false, set.contains(1))
        assertEquals(false, set.contains(2))
        assertEquals(false, set.contains(3))
        assertEquals(false, set.contains(4))
        assertEquals(true, set.contains(5))
        assertEquals(true, set.contains(6))
        assertEquals(true, set.contains(7))
        assertEquals(true, set.contains(8))
        assertEquals(true, set.contains(9))
        assertEquals(true, set.contains(10))

        set = tree.tailSet(-128)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

        var set1: SortedSet<Int> = tree.tailSet(9)
        assertEquals(false, set1.contains(1))
        assertEquals(false, set1.contains(2))
        assertEquals(false, set1.contains(3))
        assertEquals(false, set1.contains(4))
        assertEquals(false, set1.contains(5))
        assertEquals(false, set1.contains(6))
        assertEquals(false, set1.contains(7))
        assertEquals(false, set1.contains(8))
        assertEquals(true, set1.contains(9))
        assertEquals(true, set1.contains(10))
        set1 = tree.tailSet(-128)
        for (i in 1..10)
            assertEquals(true, set1.contains(i))

        var set2: SortedSet<Int> = tree.tailSet(11)
        assertEquals(false, set2.contains(1))
        assertEquals(false, set2.contains(2))
        assertEquals(false, set2.contains(3))
        assertEquals(false, set2.contains(4))
        assertEquals(false, set2.contains(5))
        assertEquals(false, set2.contains(6))
        assertEquals(false, set2.contains(7))
        assertEquals(false, set2.contains(8))
        assertEquals(false, set2.contains(9))
        assertEquals(false, set2.contains(10))
        set2 = tree.tailSet(-128)
        for (i in 1..10)
            assertEquals(true, set2.contains(i))
    }

    protected fun doHeadSetRelationTest() {
        val set: SortedSet<Int> = tree.headSet(7)
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
        tree.add(0)
        assertTrue(set.contains(0))
        set.remove(4)
        assertFalse(tree.contains(4))
        tree.remove(6)
        assertFalse(set.contains(6))
        tree.add(12)
        assertFalse(set.contains(12))
        assertEquals(5, set.size)
        assertEquals(10, tree.size)
    }

    protected fun doTailSetRelationTest() {
        val set: SortedSet<Int> = tree.tailSet(4)
        assertEquals(7, set.size)
        assertEquals(10, tree.size)
        tree.add(12)
        assertTrue(set.contains(12))
        set.remove(4)
        assertFalse(tree.contains(4))
        tree.remove(6)
        assertFalse(set.contains(6))
        tree.add(0)
        assertFalse(set.contains(0))
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
    }

    protected fun doSubSetTest() {
        TODO()
    }

}