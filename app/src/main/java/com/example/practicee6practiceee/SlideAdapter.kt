package com.example.practicee6practiceee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso


class SlideAdapter:PagerAdapter  {
    var context:Context
    var images:MutableList<Image>
    var numberClick = 0
    lateinit var inflater: LayoutInflater
    constructor(context:Context,images:MutableList<Image>):super() {
        this.context = context
        this.images = images
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object` as ConstraintLayout

    override fun getCount(): Int {
        return  images.size
    }
    fun checkText(text:String) : Boolean {
        val (upperCases, notUpperCases) = text.partition { it.isUpperCase() }
        return upperCases.length > notUpperCases.length
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var image = images[position]
        var tempImage : ImageView
        var tempText : TextView
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view:View = inflater.inflate(R.layout.item_slide,container,false)
        tempImage = view.findViewById(R.id.photo_view)
        tempText = view.findViewById(R.id.slide_text)
        Picasso.get().load(image.name).into(tempImage)
        tempText.text = image.description
        tempText.setOnClickListener {
            if (numberClick == 1) {
                if(checkText(tempText.text as String)) {
                    var temp = (tempText.text as String).toLowerCase()
                    tempText.text = temp
                } else {
                    var temp = (tempText.text as String).toUpperCase()
                    tempText.text = temp
                }
                numberClick = 0
            } else {
                numberClick ++
            }
        }
        container!!.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as ConstraintLayout)
        numberClick=0
    }
}