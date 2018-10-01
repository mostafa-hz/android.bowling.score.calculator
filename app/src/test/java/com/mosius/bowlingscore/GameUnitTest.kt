package com.mosius.bowlingscore

import com.mosius.bowlingscore.models.ScoreType
import com.mosius.bowlingscore.models.Throw
import org.junit.Test

import org.junit.Assert.*

class GameUnitTest {

    // Default example
    private val exampleGameThrows = listOf(
            Throw(1),
            Throw(4),
            Throw(4),
            Throw(5),
            Throw(6),
            Throw(4),
            Throw(5),
            Throw(5),
            Throw(10),
            Throw(0),
            Throw(1),
            Throw(7),
            Throw(3),
            Throw(6),
            Throw(4),
            Throw(10),
            Throw(2),
            Throw(8),
            Throw(6)
    )

    @Test
    fun setThrows_isCorrect() {
        val game = Game()

        game.setThrows(exampleGameThrows)

        assertEquals(133, game.score)
        assertEquals(10, game.frames.filterNotNull().size)
        assertEquals(3, game.frames.filterNotNull().last().throws.size)
        assertEquals(-1, game.getPossibleHits())
    }

    @Test
    fun allStrikeScore_isCorrect() {
        val game = Game()
        val hitAllThrows = ArrayList<Throw>().apply { repeat(12) { add(Throw(10)) } }

        game.setThrows(hitAllThrows)

        assertEquals(300, game.score)
        assertEquals(10, game.frames.filterNotNull().size)
        assertEquals(3, game.frames.filterNotNull().last().throws.size)
        assertEquals(-1, game.getPossibleHits())
    }

    @Test
    fun missAllScore_isCorrect() {
        val game = Game()
        val missAllThrows = ArrayList<Throw>().apply { repeat(20) { add(Throw(0)) } }

        game.setThrows(missAllThrows)

        assertEquals(0, game.score)
        assertEquals(10, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)
        assertEquals(-1, game.getPossibleHits())
    }

    @Test
    fun framesScore_isCorrect() {
        val game = Game()
        game.setThrows(exampleGameThrows)

        assertEquals(5, game.frames[0]!!.score)
        assertEquals(14, game.frames[1]!!.score)
        assertEquals(29, game.frames[2]!!.score)
        assertEquals(49, game.frames[3]!!.score)
        assertEquals(60, game.frames[4]!!.score)
        assertEquals(61, game.frames[5]!!.score)
        assertEquals(77, game.frames[6]!!.score)
        assertEquals(97, game.frames[7]!!.score)
        assertEquals(117, game.frames[8]!!.score)
        assertEquals(133, game.frames[9]!!.score)
    }

    @Test
    fun framesScoreType_isCorrect() {
        val game = Game()
        game.setThrows(exampleGameThrows)

        assertEquals(ScoreType.NORMAL, game.frames[0]!!.scoreType)
        assertEquals(ScoreType.NORMAL, game.frames[1]!!.scoreType)
        assertEquals(ScoreType.SPARE, game.frames[2]!!.scoreType)
        assertEquals(ScoreType.SPARE, game.frames[3]!!.scoreType)
        assertEquals(ScoreType.STRIKE, game.frames[4]!!.scoreType)
        assertEquals(ScoreType.NORMAL, game.frames[5]!!.scoreType)
        assertEquals(ScoreType.SPARE, game.frames[6]!!.scoreType)
        assertEquals(ScoreType.SPARE, game.frames[7]!!.scoreType)
        assertEquals(ScoreType.STRIKE, game.frames[8]!!.scoreType)
        assertEquals(ScoreType.SPARE, game.frames[9]!!.scoreType)
    }

    @Test
    fun framesThrows_isCorrect() {
        val game = Game()
        game.setThrows(exampleGameThrows)

        assertArrayEquals(arrayOf(Throw(1), Throw(4)), game.frames[0]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(4), Throw(5)), game.frames[1]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(6), Throw(4)), game.frames[2]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(5), Throw(5)), game.frames[3]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(10)), game.frames[4]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(0), Throw(1)), game.frames[5]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(7), Throw(3)), game.frames[6]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(6), Throw(4)), game.frames[7]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(10)), game.frames[8]!!.throws.toTypedArray())
        assertArrayEquals(arrayOf(Throw(2), Throw(8), Throw(6)), game.frames[9]!!.throws.toTypedArray())
    }

    @Test
    fun restartGame_isCorrect() {
        val game = Game()
        game.setThrows(exampleGameThrows)
        game.restart()

        assertArrayEquals(arrayOfNulls(10), game.frames)
        assertEquals(0, game.score)
        assertEquals(10, game.getPossibleHits())
    }

    @Test
    fun addThrow_isCorrect() {
        val game = Game()
        assertEquals(0, game.score)
        assertEquals(0, game.frames.filterNotNull().size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(1))
        assertEquals(0, game.score)
        assertEquals(1, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(9, game.getPossibleHits())
        game.addThrow(Throw(4))
        assertEquals(5, game.score)
        assertEquals(1, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(4))
        assertEquals(5, game.score)
        assertEquals(2, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(6, game.getPossibleHits())
        game.addThrow(Throw(5))
        assertEquals(14, game.score)
        assertEquals(2, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(6))
        assertEquals(14, game.score)
        assertEquals(3, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(4, game.getPossibleHits())
        game.addThrow(Throw(4))
        assertEquals(14, game.score)
        assertEquals(3, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(5))
        assertEquals(29, game.score)
        assertEquals(4, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(5, game.getPossibleHits())
        game.addThrow(Throw(5))
        assertEquals(29, game.score)
        assertEquals(4, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(10))
        assertEquals(49, game.score)
        assertEquals(5, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(0))
        assertEquals(49, game.score)
        assertEquals(6, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(1))
        assertEquals(61, game.score)
        assertEquals(6, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(7))
        assertEquals(61, game.score)
        assertEquals(7, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(3, game.getPossibleHits())
        game.addThrow(Throw(3))
        assertEquals(61, game.score)
        assertEquals(7, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(6))
        assertEquals(77, game.score)
        assertEquals(8, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(4, game.getPossibleHits())
        game.addThrow(Throw(4))
        assertEquals(77, game.score)
        assertEquals(8, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(10))
        assertEquals(97, game.score)
        assertEquals(9, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(2))
        assertEquals(97, game.score)
        assertEquals(10, game.frames.filterNotNull().size)
        assertEquals(1, game.frames.filterNotNull().last().throws.size)

        assertEquals(8, game.getPossibleHits())
        game.addThrow(Throw(8))
        assertEquals(117, game.score)
        assertEquals(10, game.frames.filterNotNull().size)
        assertEquals(2, game.frames.filterNotNull().last().throws.size)

        assertEquals(10, game.getPossibleHits())
        game.addThrow(Throw(6))
        assertEquals(133, game.score)
        assertEquals(10, game.frames.filterNotNull().size)
        assertEquals(3, game.frames.filterNotNull().last().throws.size)
    }
}
