package com.mosius.bowlingscore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mosius.bowlingscore.models.Frame
import com.mosius.bowlingscore.models.ScoreType
import kotlinx.android.synthetic.main.item_frame.view.*

class FramesAdapter(private val frames: Array<Frame?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(LayoutInflater
                .from(parent.context).inflate(R.layout.item_frame, parent, false)) {}
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit = holder.itemView.run {
        val item = frames[position]

        if (position == 9) {
            text_score_3.visibility = View.VISIBLE
            view_separator.visibility = View.VISIBLE
        } else {
            text_score_3.visibility = View.GONE
            view_separator.visibility = View.GONE
        }

        if (item == null) {
            text_score_1.text = ""
            text_score_2.text = ""
            text_score_3.text = ""
            text_frame_score.text = ""
            return@run
        }

        when (item.scoreType) {
            ScoreType.STRIKE -> {

                if (position == 9) {
                    text_score_1.text = "X"
                    text_score_2.text = (item.throws.getOrNull(1)?.hits)?.toString() ?: ""
                } else {
                    text_score_1.text = ""
                    text_score_2.text = "X"
                }

                text_score_3.text = (item.throws.getOrNull(2)?.hits)?.toString() ?: ""

            }
            ScoreType.SPARE -> {
                text_score_1.text = (item.throws.getOrNull(0)?.hits)?.toString() ?: ""
                text_score_2.text = "/"
                text_score_3.text = (item.throws.getOrNull(2)?.hits)?.toString() ?: ""
            }
            ScoreType.NORMAL -> {
                text_score_1.text = (item.throws.getOrNull(0)?.hits)?.toString() ?: ""
                text_score_2.text = (item.throws.getOrNull(1)?.hits)?.toString() ?: ""
                text_score_3.text = (item.throws.getOrNull(2)?.hits)?.toString() ?: ""
            }
        }

        text_frame_score.text = item.score?.toString() ?: ""

    }

}