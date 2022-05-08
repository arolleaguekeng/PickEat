package cm.pam.pickeat.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cm.pam.pickeat.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {



    // This property is only valid between onCreateView and
    // onDestroyView.


    lateinit var binding : FragmentNotificationsBinding
    lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)



        return binding.root


    }

}