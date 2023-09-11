package com.mawl.doadzikirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.mawl.doadzikirapp.adapter.ArtikelAdapter
import com.mawl.doadzikirapp.databinding.ActivityMainBinding
import com.mawl.doadzikirapp.model.ArtikelItem
import com.mawl.doadzikirapp.presentation.DetailArtikelActivity
import com.mawl.doadzikirapp.presentation.DzikirHarianActivity
import com.mawl.doadzikirapp.presentation.DzikirSetiapSaatActivity
import com.mawl.doadzikirapp.presentation.pagipetang.PagiPetangActivity
import com.mawl.doadzikirapp.presentation.QauliyahShalatActivity
import com.mawl.doadzikirapp.utils.OnItemCallback

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding


    private var dotSliderIndicator = arrayOf<ImageView?>()

    // onPageChangeCallback is SubClass from ViewPager2 for
    // responding to changing state of the selected page
    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        // instance onPageSelected gives you access to setting behavior state of selected page
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Toast.makeText(this@MainActivity, "Page $position", Toast.LENGTH_SHORT).show()
            for (i in initData().indices) {
                dotSliderIndicator[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.inactive_dot)
                )
            }

            dotSliderIndicator[0]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.txt_doa_dzikir_app)
        // This method is from dependencies Splash Screen API 12
        installSplashScreen()

        initView()
        setupViewPager()
    }

    private fun setupViewPager() {
        dotSliderIndicator = arrayOfNulls(initData().size)

        for (i in initData().indices) {
            dotSliderIndicator[i] = ImageView(this)
            dotSliderIndicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.inactive_dot)
            )

            val params = LinearLayout.LayoutParams(35,35)
            params.marginStart = 8
            params.marginEnd = 8
            binding.dots.addView(dotSliderIndicator[i], LinearLayout.LayoutParams(35,35))
        }
    }

    fun initView() {
        // setContentView is used to choose display layout in activity
        _binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Declare variable to get in touch with view in layout activyty_main
        // use findViewById to communicate with the item
        val cardQauliyahShalat = findViewById<MaterialCardView>(R.id.card_qauliyah_shalat)
        val cardDzikirSetiapSaat = findViewById<MaterialCardView>(R.id.card_dzikir_setiap_saat)
        val cardDzikirHarian = findViewById<MaterialCardView>(R.id.card_dzikir_harian)
        val cardDzikirPagiPetang = findViewById<MaterialCardView>(R.id.card_dzikir_pagi_petang)

        // when cardView is clicked, it will navigate to the other page
        // Intent is used for navigating to the other activity bt clicking cardView
        cardQauliyahShalat.setOnClickListener {
            val intent = Intent(this, QauliyahShalatActivity::class.java)
            // start to go to the destination activity
            startActivity(intent)
        }
        cardDzikirSetiapSaat.setOnClickListener(this)
        cardDzikirHarian.setOnClickListener(this)
        cardDzikirPagiPetang.setOnClickListener(this)

        val mAdapter = ArtikelAdapter()
        mAdapter.setData(initData())
        mAdapter.setOnItemCLickCallback(object : OnItemCallback {
            override fun onItemClicked(item: ArtikelItem) {
                val intent = Intent(applicationContext, DetailArtikelActivity::class.java)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA_TITLE, item.title)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA_CONTENT, item.content)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA_IMAGE, item.image)
                startActivity(intent)
            }
        })
        binding.vpViewpager.adapter = mAdapter
        binding.vpViewpager.registerOnPageChangeCallback(slidingCallback)
    }

    private fun initData() : List<ArtikelItem> {
        val titleArtikel = resources.getStringArray(R.array.arr_title_artikel)
        val contentArtikel = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArtikel = resources.obtainTypedArray(R.array.arr_img_artikel)

        val listData = arrayListOf<ArtikelItem>()
        for (i in titleArtikel.indices) {
            val data = ArtikelItem(
                titleArtikel[i],
                imageArtikel.getResourceId(i, 0),
                contentArtikel[i]
            )
            listData.add(data)
        }
        imageArtikel.recycle()
        return listData
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.card_dzikir_harian -> startActivity(Intent(this, DzikirHarianActivity::class.java))
            R.id.card_qauliyah_shalat -> startActivity(
                Intent(this, QauliyahShalatActivity::class.java)
            )

            R.id.card_dzikir_pagi_petang -> startActivity(
                Intent(this, PagiPetangActivity::class.java)
            )

            R.id.card_dzikir_setiap_saat -> startActivity(
                Intent(this, DzikirSetiapSaatActivity::class.java)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}