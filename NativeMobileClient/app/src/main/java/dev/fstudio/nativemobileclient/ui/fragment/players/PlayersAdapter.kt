package dev.fstudio.nativemobileclient.ui.fragment.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.fstudio.nativemobileclient.api.Player
import dev.fstudio.nativemobileclient.databinding.ItemPlayerListBinding
import dev.fstudio.nativemobileclient.model.Time
import dev.fstudio.nativemobileclient.util.Extends.tickToTime

class PlayersAdapter(
    private val list: List<Player>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {
    inner class PlayerViewHolder(private val binding: ItemPlayerListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(player: Player) {
            binding.itemPlayerName.text = player.name
            binding.itemPlayerPlaytime.text = player.minecraftPlayOneMinute?.tickToTime().toString()

            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos = list[adapterPosition]
            itemClickListener.onItemClick(pos.name, pos.minecraftPlayOneMinute?.tickToTime())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder =
        PlayerViewHolder(
            ItemPlayerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

interface ItemClickListener {
    fun onItemClick(
        name: String,
        time: Time?
    )
}