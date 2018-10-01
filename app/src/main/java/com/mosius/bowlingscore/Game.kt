package com.mosius.bowlingscore

import com.mosius.bowlingscore.models.Frame
import com.mosius.bowlingscore.models.ScoreType
import com.mosius.bowlingscore.models.Throw

class Game {

    /**
     * All game frames including empty frames (empty frames are `null`)
     */
    val frames: Array<Frame?> = arrayOfNulls<Frame?>(10)

    /**
     * Current score of the game
     */
    val score: Int
        get() = frames.lastOrNull {
            it?.score != null
        }?.score ?: 0

    private val throws = ArrayList<Throw>()

    /**
     * Set game throws data
     */
    fun setThrows(throws: List<Throw>) {
        this.throws.clear()
        this.throws.addAll(throws)
        calculateFrames(0, 0)
    }

    /**
     * Clear all game data
     */
    fun restart() {
        throws.clear()
        frames.forEachIndexed { i, _ -> frames[i] = null }
    }

    fun addThrow(`throw`: Throw) {
        throws.add(`throw`)

        val lastFrameIndex = frames.indexOfLast { it != null }

        if (lastFrameIndex == -1) {
            calculateFrames(0, 0)
            return
        }

        val targetFrameIndex = Math.max(0, lastFrameIndex - 2)
        val targetFrame = frames[targetFrameIndex]!!
        val targetThrowIndex = throws.indexOfFirst { targetFrame.throws.first() === it }

        calculateFrames(targetThrowIndex, targetFrameIndex)
    }

    fun getPossibleHits(): Int {
        if (frames.filterNotNull().size == 10) {
            val frame = frames[9]!!

            if (frame.throws.size < 3 && frame.scoreType == ScoreType.STRIKE)
                return 10

            if (frame.throws.size == 1) {
                return (10 - frame.throws[0].hits)
            }

            if (frame.throws.size == 2 && frame.scoreType == ScoreType.SPARE) {
                return 10
            }

            return -1
        }

        val lastFrame = frames.filterNotNull().lastOrNull()

        if (
                lastFrame != null &&
                lastFrame.scoreType == ScoreType.NORMAL &&
                lastFrame.throws.size == 1
        ) {
            return (10 - lastFrame.throws[0].hits)
        }

        return 10
    }

    /**
     * Calculate all the frames after the required index
     *
     * @param [i] represent the starting index for [Throw]
     * @param [j] represent the starting index for [Frame]
     *
     */
    private fun calculateFrames(i: Int, j: Int) {
        // break the recursive function calling if all frames are set
        if (j == frames.size) {
            return
        }

        // it sets all the remaining frames value to null
        if (i >= throws.size) {
            frames[j] = null
            calculateFrames(i, j + 1)
            return
        }

        // get the previous frame score or 0 if there is not any frame yet
        val previousScore: Int? = if (j > 0) frames[j - 1]?.score else 0

        when {

            // Strike
            throws[i].hits == 10 -> {
                // check if Strike score can be calculated otherwise the score is null
                val score: Int? = if (throws.size > i + 2) {
                    previousScore?.let { it + 10 + throws[i + 1].hits + throws[i + 2].hits }
                } else {
                    null
                }

                // create a sub list for frame throws
                val throws = ArrayList(throws.subList(
                        i,
                        if (j == 9) throws.size else Math.min(i + 1, throws.size)
                ))

                // create the frame
                frames[j] = Frame(score, ScoreType.STRIKE, throws)

                // calculate next frame
                calculateFrames(i + 1, j + 1)
            }

            // Spare
            throws.size > i + 1 && (throws[i].hits + throws[i + 1].hits) == 10 -> {
                // check if Spare score can be calculated otherwise the score is null
                val score: Int? = if (throws.size > i + 2) {
                    previousScore?.let { it + 10 + throws[i + 2].hits }
                } else {
                    null
                }

                // create a sub list for frame throws
                val throws = ArrayList(throws.subList(
                        i,
                        if (j == 9) throws.size else Math.min(i + 2, throws.size)
                ))

                // create the frame
                frames[j] = Frame(score, ScoreType.SPARE, throws)

                // calculate next frame
                calculateFrames(i + 2, j + 1)
            }

            // Normal
            else -> {
                // Check if the score can be calculated otherwise the score is null
                val score: Int? = if (throws.size > i + 1) {
                    previousScore?.let { it + throws[i].hits + throws[i + 1].hits }
                } else {
                    null
                }

                val throws = ArrayList(throws.subList(
                        i,
                        Math.min(i + 2, throws.size)
                ))

                frames[j] = Frame(score, ScoreType.NORMAL, throws)

                calculateFrames(i + 2, j + 1)
            }
        }
    }
}