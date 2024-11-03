package com.example.guesssamenumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class FragmentPageTwo : Fragment() {

    private var finalScore: Int = 50
    private lateinit var tvFinalScore: TextView
    private lateinit var btnPlayAgain: Button

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        tvFinalScore = view.findViewById(R.id.tvPlayerScore)
        btnPlayAgain = view.findViewById(R.id.btnPlayAgain)

        sharedViewModel.score.observe(viewLifecycleOwner) { score ->
            finalScore = score
            tvFinalScore.text = finalScore.toString()
        }

        btnPlayAgain.setOnClickListener { moveToPageOne() }
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