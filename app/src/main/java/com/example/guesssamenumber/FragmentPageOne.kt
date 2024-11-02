package com.example.guesssamenumber

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentPageOne : Fragment() {

    private var minValue: Int = 1
    private var maxValue: Int = 5
    private var playerScore: Int = 50

    private lateinit var rc00: TextView
    private lateinit var rc01: TextView
    private lateinit var rc02: TextView
    private lateinit var rc03: TextView

    private lateinit var rc10: TextView
    private lateinit var rc11: TextView
    private lateinit var rc12: TextView
    private lateinit var rc13: TextView

    private lateinit var rc20: TextView
    private lateinit var rc21: TextView
    private lateinit var rc22: TextView
    private lateinit var rc23: TextView

    private lateinit var tvScore: TextView

    private lateinit var btnGiveUp: Button
    private lateinit var btnEndGame: Button
    private lateinit var btnSetRandomNumber: Button

    private var valueFromBlock1: TextView? = null
    private var valueFromBlock2: TextView? = null
    private var tempValue: TextView? = null
    
    private var listOfPressed = ArrayList<TextView>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            this.minValue = bundle.getInt("int_min_value")
            this.maxValue = bundle.getInt("int_max_value")
        }


        initializeActionButton(view)
        initializeGameComponents(view)
        getPlayerChoice()
        viewLifecycleOwner.lifecycleScope.launch {
            while (playerScore >= 0) {
                if (listOfPressed.size == 12) {
                    initializeGameComponents(view)
                    getPlayerChoice()
                    listOfPressed.clear()
                }
                delay(2000) // Wait for player actions
                if (valueFromBlock1 != null && valueFromBlock2 != null) {
                    checkGame(valueFromBlock1, valueFromBlock2)

                    // Reset for the next turn
                    valueFromBlock1 = null
                    valueFromBlock2 = null
                    tempValue = null
                }
            }
        }

        if (playerScore <= 0) {
            val bundle = Bundle()
            bundle.putInt("int_final_score_value", playerScore)
            val fragmentPageTwo = FragmentPageTwo()
            fragmentPageTwo.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frameContainer,
                    fragmentPageTwo,
                    FragmentPageTwo::class.java.simpleName
                )
                .commit()
        }
    }

    private fun initializeGameComponents(view: View) {
        rc00 = view.findViewById(R.id.rc00)
        rc01 = view.findViewById(R.id.rc01)
        rc02 = view.findViewById(R.id.rc02)
        rc03 = view.findViewById(R.id.rc03)

        rc10 = view.findViewById(R.id.rc10)
        rc11 = view.findViewById(R.id.rc11)
        rc12 = view.findViewById(R.id.rc12)
        rc13 = view.findViewById(R.id.rc13)

        rc20 = view.findViewById(R.id.rc20)
        rc21 = view.findViewById(R.id.rc21)
        rc22 = view.findViewById(R.id.rc22)
        rc23 = view.findViewById(R.id.rc23)

        tvScore = view.findViewById(R.id.tvPageOneScore)

        fillRandomNumbers()
        hideNumbers()
    }

    private fun fillRandomNumbers() {
        val numberRange = minValue..maxValue
        val textViews = listOf(
            rc00, rc01, rc02, rc03,
            rc10, rc11, rc12, rc13,
            rc20, rc21, rc22, rc23
        )

        textViews.forEach { textView ->
            val randomNumber = numberRange.random()
            textView.text = randomNumber.toString()
        }

        textViews.forEach { textView ->
            Log.d("FragmentOne", "${textView.text}")
        }
    }


    private fun hideNumbers() {
        rc00.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc01.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc02.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc03.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))

        rc10.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc11.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc12.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc13.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))

        rc20.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc21.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc22.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
        rc23.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
    }

    private fun initializeActionButton(view: View) {
        btnGiveUp = view.findViewById(R.id.btnGiveUp)
        btnGiveUp.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("int_final_score_value", playerScore)
            val fragmentPageTwo = FragmentPageTwo()
            fragmentPageTwo.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frameContainer,
                    fragmentPageTwo,
                    FragmentPageTwo::class.java.simpleName
                )
                .commit()
        }

        btnEndGame = view.findViewById(R.id.btnEndGame)
        btnEndGame.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("int_final_score_value", playerScore)
            val fragmentPageTwo = FragmentPageTwo()
            fragmentPageTwo.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frameContainer,
                    fragmentPageTwo,
                    FragmentPageTwo::class.java.simpleName
                )
                .commit()

        }

        btnSetRandomNumber = view.findViewById(R.id.btnSetRandomNumber)
        btnSetRandomNumber.setOnClickListener {
            val fragmentPageThree = FragmentPageThree()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frameContainer,
                    fragmentPageThree,
                    FragmentPageThree::class.java.simpleName
                )
                .commit()
        }
    }

    private fun checkGame(value1: TextView?, value2: TextView?) {
        if (value1?.text.toString() == value2?.text.toString()) {
            this.playerScore += 10
            if (value1 != null) {
                listOfPressed.add(value1)
            }

            if (value2 != null) {
                listOfPressed.add(value2)
            }
            value1?.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            value2?.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            tvScore.text = this.playerScore.toString()
            Toast.makeText(requireActivity(), "Player menang", Toast.LENGTH_SHORT).show()
        } else {
            value1?.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
            value2?.setTextColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
            this.playerScore -= 5
            tvScore.text = this.playerScore.toString()
        }
    }

    private fun getPlayerChoice() {
        rc00.setOnClickListener { setPlayerChoice(rc00) }
        rc01.setOnClickListener { setPlayerChoice(rc01) }
        rc02.setOnClickListener { setPlayerChoice(rc02) }
        rc03.setOnClickListener { setPlayerChoice(rc03) }
        rc10.setOnClickListener { setPlayerChoice(rc10) }
        rc11.setOnClickListener { setPlayerChoice(rc11) }
        rc12.setOnClickListener { setPlayerChoice(rc12) }
        rc13.setOnClickListener { setPlayerChoice(rc13) }
        rc20.setOnClickListener { setPlayerChoice(rc20) }
        rc21.setOnClickListener { setPlayerChoice(rc21) }
        rc22.setOnClickListener { setPlayerChoice(rc22) }
        rc23.setOnClickListener { setPlayerChoice(rc23) }
    }

    private fun setPlayerChoice(textView: TextView) {
        textView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))

        if (listOfPressed.contains(textView)) {
            Toast.makeText(requireActivity(),"This number already pressed", Toast.LENGTH_SHORT).show()
            return
        }
        tempValue = textView
        if (valueFromBlock1 == null) {
            valueFromBlock1 = tempValue
        } else if (valueFromBlock2 == null) {
            valueFromBlock2 = tempValue
        }
    }
}