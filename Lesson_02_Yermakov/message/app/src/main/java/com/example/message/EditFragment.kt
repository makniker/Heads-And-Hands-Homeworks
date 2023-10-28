package com.example.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class EditFragment : Fragment() {

    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editMessage = view.findViewById<EditText>(R.id.edit_message)
        editMessage.setText(args.message)
        val saveButton = view.findViewById<Button>(R.id.save_message_button)
        saveButton.setOnClickListener {
            setFragmentResult(
                "returnEditMessageKey",
                bundleOf(Pair("editedMessage", editMessage.text.toString()))
            )
            findNavController().popBackStack()
        }
    }
}