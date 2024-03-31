package com.example.designpatternsdemo.core.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.designpatternsdemo.core.model.ProductModel
import com.example.designpatternsdemo.databinding.LayoutListItemBinding


class ProductListAdapter(
    private var context: Context,
    private var products: ArrayList<ProductModel>
) :
    RecyclerView.Adapter<ProductListAdapter.MvcProductViewHolder>() {
    class MvcProductViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(mContext: Context, data: ProductModel, isLast: Boolean) {
            binding.productCategory.text = data.category
            binding.productDescription.text = data.description
            binding.productPrice.text = "${data.price}\$"
            binding.productTitle.text = data.title
            Glide
                .with(mContext)
                .load(Uri.parse(data.image))
                .fitCenter()
                .into(binding.productImage)

            if (!isLast) {
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val dpValue = 16
                val d = mContext.resources.displayMetrics.density
                val margin = (dpValue * d).toInt()
                layoutParams.bottomMargin = margin
                binding.root.layoutParams = layoutParams
            }
        }
    }

    fun updateProducts(nProducts: List<ProductModel>) {
        products.addAll(nProducts)
        notifyItemRangeInserted(0, nProducts.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvcProductViewHolder {
        val binding = LayoutListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MvcProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: MvcProductViewHolder, position: Int) {
        holder.bind(context, products[position], position == (products.size - 1))
    }
}