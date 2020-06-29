package com.example.practicee6practiceee

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.activity_slide.*
import java.util.*

class SlideActivity : AppCompatActivity() {
    private var url = ""
    private var dateList : MutableList<com.example.practicee6practiceee.Image> = mutableListOf()
    private  var loadingDialog : Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        val bundle: Bundle? = intent.extras
        bundle?.let {
            url = bundle.get("url").toString()
        }
        showLoading()
        getDataToJson()
        textView2.isClickable = true
        textView2.isLongClickable = true
        textView2.setOnLongClickListener{
            randomColor()
            return@setOnLongClickListener true
        }
    }
    private fun hideLoading() {
        loadingDialog?.let { if(it.isShowing) it.cancel() }
    }
    private fun showLoading() {
        hideLoading()
        loadingDialog = CommonUtils.showLoadingDialog(this)
        textView2.visibility = View.GONE
        viewPager.visibility = View.GONE
    }
    fun randomColor() {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        textView2.setTextColor(color)
    }

    fun getDataToJson() {
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://nopbai.live/data/data.json")
            .build()
            .getAsObject(Images::class.java,object : ParsedRequestListener<Images>{
                override fun onResponse(response: Images) {
                    textView2.text = response.name
                    dateList.addAll(response.images)
                    viewPager.adapter = SlideAdapter(applicationContext,dateList)
                    Handler().postDelayed({
                        hideLoading()
                        textView2.visibility = View.VISIBLE
                        viewPager.visibility = View.VISIBLE
                    },2000)
                }

                override fun onError(anError: ANError?) {
                }
            })
    }
}
