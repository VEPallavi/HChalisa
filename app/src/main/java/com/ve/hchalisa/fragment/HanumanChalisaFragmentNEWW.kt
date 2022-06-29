package com.ve.hchalisa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ve.hchalisa.R
import com.ve.hchalisa.adapter.HanumanChalisaAdapter
import com.ve.hchalisa.helper.AppConstants
import com.ve.hchalisa.modal.HanumanChalisaContentModel
import com.ve.hchalisa.modal.HanumanChalisaMainModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class HanumanChalisaFragmentNEWW: Fragment(), View.OnClickListener{
    var rv_hanuman_chalisa: TextView?= null
    var iv_previous: ImageView?= null
    var iv_next: ImageView?= null
    var tv_previous: TextView?= null
    var tv_next: TextView?= null
    var tv_title: TextView?= null
    var adapter: HanumanChalisaAdapter?= null
    var arrayListChalisa = ArrayList<HanumanChalisaMainModel>()
    var arrayListChalistContent = ArrayList<HanumanChalisaContentModel>()
    private var currentIndex = 0

    // to keep current question track
   // private var currentQuestionIndex = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hanuman_chalisa_neww, container, false)
        rv_hanuman_chalisa = view.findViewById(R.id.rv_hanuman_chalisa)
        tv_title = view.findViewById(R.id.tv_title)
        iv_previous = view.findViewById(R.id.iv_previous)
        tv_previous = view.findViewById(R.id.tv_previous)

        iv_next = view.findViewById(R.id.iv_next)
        tv_next = view.findViewById(R.id.tv_next)




        initView()
        setOnClickListener()
        return view
    }



    private fun initView() {

        getJsonData()
        displayData()

//        var chalisaList = ArrayList<HanumanChalisaContentModel>()
//        val hanumanChalisaContentModal1 = HanumanChalisaContentModel()
//        hanumanChalisaContentModal1.id = arrayListChalistContent.get(0).id
//        hanumanChalisaContentModal1.isDoha = arrayListChalistContent.get(0).isDoha
//        hanumanChalisaContentModal1.isChaupai = arrayListChalistContent.get(0).isChaupai
//        hanumanChalisaContentModal1.content = arrayListChalistContent.get(0).content
//
//        if(hanumanChalisaContentModal1.isDoha){
//            tv_title?.setText(resources.getString(R.string.txt_doha))
//        }else{
//            tv_title?.setText(resources.getString(R.string.txt_chaupai))
//        }
//
//        tv_previous?.setText("" + chalisaList.size)
//        tv_next?.setText("" + arrayListChalistContent.size)
//
//        chalisaList.add(hanumanChalisaContentModal1)


    }

    private fun getJsonData() {
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val dataList = obj.getJSONArray("chalisa_content")

            for (i in 0 until dataList.length()) {
                val chalisaContentModel = HanumanChalisaContentModel()
                val jsonObjectChalisa: JSONObject = dataList.getJSONObject(i)
                val idd = jsonObjectChalisa.getInt("id")
                val isDoha = jsonObjectChalisa.getBoolean("isDoha")
                val isChaupai = jsonObjectChalisa.getBoolean("isChaupai")
                val content = jsonObjectChalisa.getString("content")

                chalisaContentModel.id = idd
                chalisaContentModel.isDoha = isDoha
                chalisaContentModel.isChaupai = isChaupai
                chalisaContentModel.content = content

                arrayListChalistContent.add(chalisaContentModel)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val inputStream = requireActivity().assets.open(AppConstants.JSON_FILE_NAME_CHALISA)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


    private fun setOnClickListener() {
        iv_previous?.setOnClickListener(this)
        iv_next?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_next -> {
                currentIndex++
                nextScreenData()
            }
            R.id.iv_previous -> {
                previousScreenData()
            }
        }
    }



    private fun displayData() {
        if(arrayListChalistContent.get(currentIndex).isDoha){
            tv_title?.setText(resources.getString(R.string.txt_doha))
        }else{
            tv_title?.setText(resources.getString(R.string.txt_chaupai))
        }
        rv_hanuman_chalisa?.setText(arrayListChalistContent.get(currentIndex).content)
        tv_next?.setText(""+arrayListChalistContent.size)
    }

    private fun nextScreenData()
    {
        if(arrayListChalistContent.get(currentIndex).isDoha)
        {
            tv_title?.setText(resources.getString(R.string.txt_doha))
        } else
        {
            tv_title?.setText(resources.getString(R.string.txt_chaupai))
        }

        rv_hanuman_chalisa?.setText(arrayListChalistContent.get(currentIndex).content)
        tv_previous?.setText("" + (currentIndex + 1))

        iv_previous?.setImageResource(R.drawable.back_active)
        iv_previous?.isClickable = true

        if (currentIndex == arrayListChalistContent.size - 1)
        {
            iv_next?.setImageResource(R.drawable.done)
            iv_next?.isClickable = false
        }

    }

    private fun previousScreenData() {
        if(arrayListChalistContent.get(currentIndex).isDoha)
        {
            tv_title?.setText(resources.getString(R.string.txt_doha))
        }else
        {
            tv_title?.setText(resources.getString(R.string.txt_chaupai))
        }


        if (currentIndex > 0) {
            currentIndex = ((currentIndex - 1) % arrayListChalistContent.size)

            iv_previous?.isClickable = true
            iv_previous?.setImageResource(R.drawable.back_active)
            rv_hanuman_chalisa?.setText(arrayListChalistContent.get(currentIndex).content)
            tv_previous?.setText("" + (currentIndex+1))

        }


        if (currentIndex == 0) {
            tv_title?.setText(resources.getString(R.string.txt_doha))
            tv_previous?.setText("" + (currentIndex+1))
            iv_previous?.setImageResource(R.drawable.back_disable)
            iv_previous?.isClickable = false
            iv_next?.setImageResource(R.drawable.forward)
            iv_next?.isClickable = true
        }


//        rv_hanuman_chalisa?.setText(arrayListChalistContent.get(currentIndex).content)
//        tv_previous?.setText("" + (currentIndex))
//
//        if (currentIndex == 0)
//        {
//            iv_previous?.setImageResource(R.drawable.back_disable)
//            iv_previous?.isClickable = false
//            iv_next?.setImageResource(R.drawable.forward)
//            iv_next?.isClickable = true
//        }






    }


}