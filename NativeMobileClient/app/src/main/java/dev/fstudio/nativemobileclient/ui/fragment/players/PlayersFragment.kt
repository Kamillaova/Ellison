package dev.fstudio.nativemobileclient.ui.fragment.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import dev.fstudio.nativemobileclient.databinding.FragmentPlayersBinding
import dev.fstudio.nativemobileclient.model.Time
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayersFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentPlayersBinding
    private lateinit var viewModel: PlayerViewModel
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        recycler = binding.recyclerPlayerList
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.players.collect {
                recycler.adapter = PlayersAdapter(it, this@PlayersFragment)
            }
        }
    }

    override fun onItemClick(name: String, time: Time?) {

    }
}