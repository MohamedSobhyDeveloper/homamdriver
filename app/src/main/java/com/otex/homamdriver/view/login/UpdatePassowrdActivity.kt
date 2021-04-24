package com.otex.homamdriver.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityUpdatePassowrdBinding
import com.otex.homamuser.utlitites.PrefsUtil
import java.util.HashMap

class UpdatePassowrdActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdatePassowrdBinding
    private var loginviewmodel : LoginActivityViewModel? = null
    private var newpassword:String=""
    private var retypepass:String=""
    var type: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePassowrdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        click()
    }

    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }


        binding.btnUpdate.setOnClickListener {
            newpassword=binding.editNewPassword.text.toString()
            retypepass=binding.editReTypePassword.text.toString()
            if(newpassword == ""){
                binding.editNewPassword.error = getString(R.string.enter_new_pass)
            }else if(retypepass == ""){
                binding.editReTypePassword.error = getString(R.string.enter_retype_pass)
            }else{
                if(retypepass==newpassword){
                    updatepassword(newpassword,retypepass)
                }else{
                    binding.editReTypePassword.error = getString(R.string.dosent_match)
                }
            }
        }

    }

    private fun updatepassword(newpassword: String, retypepass: String) {
        val map = HashMap<String, String?>()
        map["password"] = newpassword
        map["confirm_password"] = retypepass
        map["type"] = type

        loginviewmodel?.updatefPass(this,map)
    }

    private fun initialize() {
        type= PrefsUtil.with(this).get("type","")!!

        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

        loginviewmodel!!.updatePassowrdLiveData.observe(this) {

            if (it.status.equals("1")){
                PrefsUtil.with(this).add("token",it!!.token).apply()
                Toast.makeText(this,getString(R.string.update_pass_success), Toast.LENGTH_SHORT).show()
                finish()

            }
        }


    }
}