package com.ve.hchalisa.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ve.hchalisa.R
import com.ve.hchalisa.fragment.HanumanArtiFragment
import com.ve.hchalisa.fragment.HanumanChalisaFragment
import com.ve.hchalisa.helper.AppConstants
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity: AppCompatActivity(), View.OnClickListener {
    var mSelectedTabBtnPosition: String = ""
    private var mManager: FragmentManager? = null
    private var mTransaction: FragmentTransaction? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        manageTabButtonClick(tv_chalisa)
        mSelectedTabBtnPosition = AppConstants.HANUMAN_CHALISA
        setupClickListener()
    }


    private fun setupClickListener()
    {
        iv_back.setOnClickListener(this)
        tv_chalisa.setOnClickListener(this)
        tv_aarti.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back ->{
                finish()
            }
            R.id.tv_chalisa ->{
                mSelectedTabBtnPosition = AppConstants.HANUMAN_CHALISA
                manageTabButtonClick(tv_chalisa)
            }
            R.id.tv_aarti ->{
                mSelectedTabBtnPosition = AppConstants.HANUMAN_AARTI
                manageTabButtonClick(tv_aarti)
            }
        }
    }


    private fun manageTabButtonClick(selectedBtn: View) {
        tv_chalisa.setTextColor(ContextCompat.getColor(this, R.color.white))
        tv_aarti.setTextColor(ContextCompat.getColor(this, R.color.white))
        view_chalisa.setBackgroundColor(ContextCompat.getColor(this, R.color.color_transparent))
        view_aarti.setBackgroundColor(ContextCompat.getColor(this, R.color.color_transparent))

        when(selectedBtn.id)
        {
            R.id.tv_chalisa ->{
                tv_chalisa.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                tv_chalisa.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_selected))
                tv_aarti.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_disable))
                setDisplayFragment(1)
            }
            R.id.tv_aarti ->{
                tv_aarti.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                tv_aarti.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_selected))
                tv_chalisa.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_disable))
                setDisplayFragment(2)
            }
        }
    }

    fun setDisplayFragment(id: Int) {
        var mFragment: Fragment? = null
        when (id) {
            1 -> {
                mFragment = HanumanChalisaFragment()
                replaceFragment(mFragment)
            }
            2 -> {
                mFragment = HanumanArtiFragment()
                replaceFragment(mFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        mManager = supportFragmentManager
        mTransaction = mManager?.beginTransaction()
        mTransaction!!.replace(R.id.fragment_container, fragment)
        mTransaction!!.addToBackStack(null)
        mTransaction!!.commit()
    }

    fun handleBackPress() {
//        try{
//            if (mManager?.findFragmentById(R.id.fragment_container) is HanumanChalisaFragment)
//            {
//                finish()
//            }
//            else
//            {
//                mManager?.popBackStackImmediate()
//            }
//        }catch (e: Exception){
//        }
    }

    override fun onBackPressed() {
       // handleBackPress()

        finish()
    }


}