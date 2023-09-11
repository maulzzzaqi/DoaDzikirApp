package com.mawl.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mawl.doadzikirapp.adapter.DoaDzikirAdapter
import com.mawl.doadzikirapp.R
import com.mawl.doadzikirapp.databinding.ActivityDzikirHarianBinding
import com.mawl.doadzikirapp.model.DoaDzikirItem

class DzikirHarianActivity : AppCompatActivity() {
    private var _binding: ActivityDzikirHarianBinding? = null
    private val binding get() = _binding as ActivityDzikirHarianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.txt_dzikir_doa_harian)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_dzikir_harian)
        _binding = ActivityDzikirHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        providingDzikirDatas()
        initView()
    }

    private fun initView() {
        // apply is a scope function that return context of object
        // in this case the object is rvDzikirHarian
        // so, apply will return 'this: RecyclerView'
        binding.rvDzikirHarian.apply {
            val mAdapter = DoaDzikirAdapter()
            mAdapter.setData(providingDzikirDatas())
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@DzikirHarianActivity)
        }
    }

    private fun providingDzikirDatas(): List<DoaDzikirItem> {
        val titleDzikir = resources.getStringArray(R.array.arr_dzikir_doa_harian)
        val arabicDzikir = resources.getStringArray(R.array.arr_lafaz_dzikir_doa_harian)
        val translateDzikir = resources.getStringArray(R.array.arr_terjemah_dzikir_doa_harian)

        val listData = arrayListOf<DoaDzikirItem>()
        for (i in titleDzikir.indices) {
            val dataDzikirHarian = DoaDzikirItem(
                titleDzikir[i],
                arabicDzikir[i],
                translateDzikir[i]
            )
            listData.add(dataDzikirHarian)
        }
        return listData
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