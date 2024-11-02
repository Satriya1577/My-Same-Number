package com.example.guesssamenumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FragmentPageTwo : Fragment() {

    private var finalScore: Int = 50
    private lateinit var tvFinalScore: TextView
    private lateinit var btnPlayAgain: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            this.finalScore = bundle.getInt("int_final_score_value")
        }

        tvFinalScore = view.findViewById(R.id.tvPlayerScore)
        btnPlayAgain = view.findViewById(R.id.btnPlayAgain)

        btnPlayAgain.setOnClickListener { moveToPageOne() }
        tvFinalScore.text = finalScore.toString()
    }

    private fun moveToPageOne() {
        val fragmentManager = parentFragmentManager
        val fragmentPageOne = FragmentPageOne()
        fragmentManager.beginTransaction()
            .replace(
                R.id.frameContainer,
                fragmentPageOne,
                FragmentPageOne::class.java.simpleName
            )
            .commit()
    }
}