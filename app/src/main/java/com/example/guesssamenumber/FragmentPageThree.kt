package com.example.guesssamenumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class FragmentPageThree : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val minValue: EditText = view.findViewById(R.id.edMinValue)
        val maxValue: EditText = view.findViewById(R.id.edMaxValue)
        val buttonSubmit: Button = view.findViewById(R.id.btnSubmit)

        buttonSubmit.setOnClickListener {
            val min: Int = minValue.text.toString().toInt()
            val max: Int = maxValue.text.toString().toInt()
            sharedViewModel.setRange(Range(min,max))

            Toast.makeText(requireActivity(), "Min dan Max berhasil diset", Toast.LENGTH_SHORT).show()
            val fragmentManager = parentFragmentManager
            val fragmentPageOne = FragmentPageOne()
            fragmentManager
                .beginTransaction()
                .replace(
                    R.id.frameContainer,
                    fragmentPageOne,
                    FragmentPageOne::class.java.simpleName
                )
                .commit()
        }
    }
}