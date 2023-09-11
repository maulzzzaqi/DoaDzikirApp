package com.mawl.doadzikirapp.utils

import com.mawl.doadzikirapp.model.ArtikelItem

interface OnItemCallback {
    fun onItemClicked(item: ArtikelItem)
}