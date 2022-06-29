package com.ve.hchalisa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ve.hchalisa.R
import com.ve.hchalisa.adapter.HanumanChalisaAdapter
import com.ve.hchalisa.helper.AppConstants
import com.ve.hchalisa.modal.HanumanChalisaContentModel
import com.ve.hchalisa.modal.HanumanChalisaMainModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class HanumanChalisaFragment: Fragment(), View.OnClickListener{
    var rv_hanuman_chalisa: RecyclerView?= null
    var iv_previous: ImageView?= null
    var iv_next: ImageView?= null
    var tv_previous: TextView?= null
    var tv_next: TextView?= null
    var tv_title: TextView?= null
    var adapter: HanumanChalisaAdapter?= null
    var arrayListChalisa = ArrayList<HanumanChalisaMainModel>()
    var arrayListChalistContent = ArrayList<HanumanChalisaContentModel>()
    private val currentIndex = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hanuman_chalisa, container, false)
        rv_hanuman_chalisa = view.findViewById(R.id.rv_hanuman_chalisa)
        tv_title = view.findViewById(R.id.tv_title)
        iv_previous = view.findViewById(R.id.iv_previous)
        tv_previous = view.findViewById(R.id.tv_previous)

        iv_next = view.findViewById(R.id.iv_next)
        tv_next = view.findViewById(R.id.tv_next)

        var layoutManager = LinearLayoutManager(requireActivity())
        rv_hanuman_chalisa?.layoutManager = layoutManager



        initView()
        setOnClickListener()
        return view
    }



    private fun initView() {

        getJsonData()


        var chalisaList = ArrayList<HanumanChalisaContentModel>()
        val hanumanChalisaContentModal1 = HanumanChalisaContentModel()
        hanumanChalisaContentModal1.id = arrayListChalistContent.get(0).id
        hanumanChalisaContentModal1.isDoha = arrayListChalistContent.get(0).isDoha
        hanumanChalisaContentModal1.isChaupai = arrayListChalistContent.get(0).isChaupai
        hanumanChalisaContentModal1.content = arrayListChalistContent.get(0).content

        if(hanumanChalisaContentModal1.isDoha){
            tv_title?.setText(resources.getString(R.string.txt_doha))
        }else{
            tv_title?.setText(resources.getString(R.string.txt_chaupai))
        }

        tv_previous?.setText("" + chalisaList.size)
        tv_next?.setText("" + arrayListChalistContent.size)

        chalisaList.add(hanumanChalisaContentModal1)

        adapter = HanumanChalisaAdapter(requireActivity(), chalisaList)
        rv_hanuman_chalisa?.adapter = adapter

/*        val hanumanChalisaMainModal = HanumanChalisaMainModel()

     //   hanumanChalisaMainModal.isChaupai = false
     //   hanumanChalisaMainModal.isDoha = true

        val hanumanChalisaContentModal1 = HanumanChalisaContentModel()
        hanumanChalisaContentModal1.id =1
        hanumanChalisaContentModal1.content = "doha test 1"

        val hanumanChalisaContentModal2 = HanumanChalisaContentModel()
        hanumanChalisaContentModal2.id =1
        hanumanChalisaContentModal2.content = "doha test2"


        val hanumanChalisaContentList = ArrayList<HanumanChalisaContentModel>()
        hanumanChalisaContentList.add(hanumanChalisaContentModal1)
        hanumanChalisaContentList.add(hanumanChalisaContentModal2)


        hanumanChalisaMainModal.chalisa_content = hanumanChalisaContentList
        arrayListChalisa.add(hanumanChalisaMainModal)



        val hanumanChalisaMainModal1 = HanumanChalisaMainModel()

//        hanumanChalisaMainModal1.isChaupai = true
//        hanumanChalisaMainModal1.isDoha = false

        val hanumanChalisaContentModal11 = HanumanChalisaContentModel()
        hanumanChalisaContentModal11.id =1
        hanumanChalisaContentModal11.content = "chapui test1"

        val hanumanChalisaContentModal22 = HanumanChalisaContentModel()
        hanumanChalisaContentModal22.id =1
        hanumanChalisaContentModal22.content = "chapui test2"


        val hanumanChalisaContentList1 = ArrayList<HanumanChalisaContentModel>()
        hanumanChalisaContentList1.add(hanumanChalisaContentModal11)
        hanumanChalisaContentList1.add(hanumanChalisaContentModal22)


        hanumanChalisaMainModal1.chalisa_content = hanumanChalisaContentList1
        arrayListChalisa.add(hanumanChalisaMainModal1)*/


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
                nextScreenData()
            }
            R.id.iv_previous -> {
                previousScreenData()
            }
        }
    }

    private fun previousScreenData() {

    }

    private fun nextScreenData() {
        var chalisaList = ArrayList<HanumanChalisaContentModel>()
        val hanumanChalisaContentModal2 = HanumanChalisaContentModel()
        hanumanChalisaContentModal2.id =arrayListChalistContent.get(1).id
        hanumanChalisaContentModal2.isDoha = arrayListChalistContent.get(1).isDoha
        hanumanChalisaContentModal2.isChaupai = arrayListChalistContent.get(1).isChaupai
        hanumanChalisaContentModal2.content = arrayListChalistContent.get(1).content

        if(hanumanChalisaContentModal2.isDoha){
            tv_title?.setText(resources.getString(R.string.txt_doha))
        }else{
            tv_title?.setText(resources.getString(R.string.txt_chaupai))
        }

        tv_previous?.setText("" + chalisaList.size)

        chalisaList.add(hanumanChalisaContentModal2)

        adapter = HanumanChalisaAdapter(requireActivity(), chalisaList)
        rv_hanuman_chalisa?.adapter = adapter
    }


}