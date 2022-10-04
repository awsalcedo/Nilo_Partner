package com.asalcedo.nilopartner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.asalcedo.nilopartner.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: MutableList<Product>,
    private val listener: OnProductListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    //Assign a value to each component of the view
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.setListener(product)
        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = product.price.toString()
        holder.binding.tvQuantity.text = product.quantity.toString()
    }

    override fun getItemCount(): Int = productList.size

    fun add(product: Product) {
        if (!productList.contains(product)) {
            productList.add(product)
            notifyItemInserted(productList.size - 1)
        }
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Use viewBinding
        val binding = ItemProductBinding.bind(view)

        fun setListener(product: Product) {
            //Schedule click events
            binding.root.setOnClickListener {
                listener.onClick(product)
            }
            binding.root.setOnLongClickListener {
                listener.onLongClick(product)
                true
            }
        }

    }
}