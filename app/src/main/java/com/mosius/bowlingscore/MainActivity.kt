package com.mosius.bowlingscore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mosius.bowlingscore.models.Throw
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = FramesAdapter(game.frames)

        updateButtons()
        updateScore()
    }

    fun onClickValue(view: View) {
        val value = view.tag.toString().toInt()

        game.addThrow(Throw(value))

        recycler.adapter?.notifyDataSetChanged()
        recycler.smoothScrollToPosition(game.frames.filterNotNull().size)

        updateButtons()
        updateScore()
    }

    fun onClickRestart(view: View) {
        game.restart()

        recycler.adapter?.notifyDataSetChanged()
        recycler.smoothScrollToPosition(0)

        updateButtons()
        updateScore()
    }

    private fun updateButtons() {
        val possibleHits = game.getPossibleHits()

        for (i in 0 until layout_buttons.childCount) {
            layout_buttons.getChildAt(i).isEnabled = possibleHits >= i
        }
    }

    private fun updateScore() {
        text_game_score.text = getString(R.string.score, game.score.toString())
    }
}
