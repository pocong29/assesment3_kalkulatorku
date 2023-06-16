package com.d3if4063.assesment3_kalkulatorku.network

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if4063.assesment3_kalkulatorku.R
import com.d3if4063.assesment3_kalkulatorku.databinding.ItemKonversiBinding
import com.d3if4063.assesment3_kalkulatorku.model.GaleriModel

class  KonversiAdapter : RecyclerView.Adapter<KonversiAdapter.ViewHolder>() {
    private val data = mutableListOf<GaleriModel>()

    fun updateData(newData: List<GaleriModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemKonversiBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(
        private val binding: ItemKonversiBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(galeri: GaleriModel) = with(binding) {
            TextView1.text = galeri.nama
            TextView2.text = galeri.namaLatin
            Glide.with(imageView3.context)
                .load(DataApi.getDataUrl(galeri.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView3)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, galeri.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}