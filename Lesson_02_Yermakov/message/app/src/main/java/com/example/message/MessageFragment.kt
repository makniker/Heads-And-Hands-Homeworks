package com.example.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class MessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.edit_message_button)
        val message = view.findViewById<TextView>(R.id.message_view)
        val navController = findNavController()
        val args: MessageFragmentArgs by navArgs()
        message.text = args.editMessage
        button.setOnClickListener {
            navController.navigate(
                MessageFragmentDirections.actionMessageFragmentToEditFragment(message.text.toString())
            )
        }
    }
}