package com.soo.github.base

import android.util.ArrayMap
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<ITEM : Any>(
    private val layoutBindingId: Pair<Int, Int>,
    private val viewModels: ArrayMap<Int, BaseViewModel>? = null
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val dataSet = mutableListOf<ITEM>()

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bindViewHolder(dataSet[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(parent, layoutBindingId, viewModels)

    fun setItem(items: List<ITEM>?) {
        if (items == null) return
        dataSet.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}