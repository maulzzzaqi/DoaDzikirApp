package com.mawl.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mawl.doadzikirapp.adapter.DoaDzikirAdapter
import com.mawl.doadzikirapp.R
import com.mawl.doadzikirapp.model.DataDoaDzikir.ListQauliyah
import com.mawl.doadzikirapp.databinding.ActivityQauliyahShalatBinding

class QauliyahShalatActivity : AppCompatActivity() {
    // Implementing viewBinding feature
    // viewBinding makes it easier to write code that interacts with views
    // viewBinding comes to  replace findViewByID
    private var _binding: ActivityQauliyahShalatBinding? = null
    private val binding get() = _binding as ActivityQauliyahShalatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.txt_menu_qauliyah_shalat)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // initialize property of _binding with layoutInflater to get the layout
        _binding = ActivityQauliyahShalatBinding.inflate(layoutInflater)
        // replace argument of setContentView with viewBinding
        // connect the view using view binding
        setContentView(binding.root)

        // set recyclerView
        val mAdater = DoaDzikirAdapter()
        mAdater.setData(ListQauliyah)
        binding.rvQauliyahShalat.adapter = mAdater
        // layoutManager is a class to set out structure of recyclerView to display dataset
        binding.rvQauliyahShalat.layoutManager = LinearLayoutManager(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}