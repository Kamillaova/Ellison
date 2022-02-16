package dev.fstudio.nativemobileclient.ui.fragment.players

import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {
    val players = PlayerRepository().getTop()
}