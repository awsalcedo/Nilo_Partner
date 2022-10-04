package com.asalcedo.nilopartner

//The interface will be called from the adapter
interface OnProductListener {
    fun onClick(product: Product)
    fun onLongClick(product: Product)
}