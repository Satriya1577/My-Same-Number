package com.example.guesssamenumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentPageThree : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val minValue: EditText = view.findViewById(R.id.edMinValue)
        val maxValue: EditText = view.findViewById(R.id.edMaxValue)
        val buttonSubmit: Button = view.findViewById(R.id.btnSubmit)
        val fragmentManager = parentFragmentManager
        val fragmentHalamanSatu = FragmentPageOne()

        buttonSubmit.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("int_min_value", minValue.text.toString().toInt())
            bundle.putInt("int_max_value", maxValue.text.toString().toInt())
            fragmentHalamanSatu.arguments = bundle

            Toast.makeText(requireActivity(), "Min dan Max berhasil diset", Toast.LENGTH_SHORT).show()
            fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, fragmentHalamanSatu, FragmentPageOne::class.java.simpleName)
                .commit()
        }
    }
}