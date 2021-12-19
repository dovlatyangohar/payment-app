package com.example.visapaymentapp.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.visapaymentapp.R
import com.example.visapaymentapp.databinding.CardItemBinding
import com.example.visapaymentapp.model.CardInfo

class CardsAdapter(
    private var data: List<CardInfo> = mutableListOf()
) : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    private var onItemClickListener: (cardInfo: CardInfo) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        data[position].let { card ->
            holder.run {
                bind(card)
                itemView.setOnClickListener {
                    onItemClickListener(card)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(dataList: List<CardInfo>) {
        data = dataList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(l: (cardInfo: CardInfo) -> Unit) {
        onItemClickListener = l
    }

    class ViewHolder(private val itemViewBinding: CardItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(cardInfo: CardInfo) {
            itemViewBinding.cardNumberTextView.text = cardInfo.cardNumber
        }

    }
}