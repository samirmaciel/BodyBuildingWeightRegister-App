package com.samirmaciel.bodybuildingweightregister.view.home.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.bodybuildingweightregister.R
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel
import com.samirmaciel.bodybuildingweightregister.view.home.AddEquipamentBottomSheet

class EquipamentModelsRecyclerAdapter(
    val fragmentManager: FragmentManager,
    val onUpdate: (EquipamentModel) -> Unit,
    val onDelete: (EquipamentModel) -> Unit
) :
    RecyclerView.Adapter<EquipamentModelsRecyclerAdapter.MyViewHolder>() {

    var itemList: MutableList<EquipamentModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.equipament_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binditem(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEquipamentName = itemView.findViewById<TextView>(R.id.tv_equipamentName)
        val tvEquipamentWeight = itemView.findViewById<TextView>(R.id.tv_equipamentWeight)
        val btnEdit = itemView.findViewById<ImageButton>(R.id.btn_edit)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btn_delete)


        fun binditem(position: Int) {
            tvEquipamentName.text = itemList[position].name
            tvEquipamentWeight.text = itemList[position].weight

            btnDelete.setOnClickListener {
                val alertDialog = AlertDialog.Builder(itemView.context).apply {
                    setTitle("Vai deletar isso ai o mané?")
                    setPositiveButton("Vou sim chará!"){
                        _, _ -> onDelete(itemList[position])
                    }
                    setNegativeButton("Cancela! ", null)
                }.show()

            }

            btnEdit.setOnClickListener {
                val addEquipament = AddEquipamentBottomSheet(true, itemList[position]) {
                    itemList[position] = it
                    onUpdate(it)
                    notifyDataSetChanged()
                    Log.d("TESTE", "binditem: " + it.weight)
                }

                addEquipament.show(fragmentManager, "AddEquipamentBottomSheet")
            }

        }
    }
}