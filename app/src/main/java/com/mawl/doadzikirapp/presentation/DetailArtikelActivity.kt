package com.mawl.doadzikirapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mawl.doadzikirapp.R
import com.mawl.doadzikirapp.databinding.ActivityDetailArtikelBinding

class DetailArtikelActivity : AppCompatActivity() {
    private  var _binding : ActivityDetailArtikelBinding? = null
    private val binding get() = _binding as ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Artikel"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        _binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titleArtikel = intent.getStringExtra(EXTRA_DATA_TITLE)
        val contentArticle = intent.getStringExtra(EXTRA_DATA_CONTENT)
        val imageArtikel = intent.getIntExtra(EXTRA_DATA_IMAGE, 1)

        binding.apply {
            tvDetailTitle.text = titleArtikel
            tvDetailContent.text = contentArticle
            imgDetailArtikel.setImageResource(imageArtikel)
        }
    }

    // object that used in this Activity that can access by the other class
    companion object {
        // constant to save KEY of data transaction
        const val EXTRA_DATA_TITLE = "title"
        const val EXTRA_DATA_CONTENT = "content"
        const val EXTRA_DATA_IMAGE = "image"
    }
}