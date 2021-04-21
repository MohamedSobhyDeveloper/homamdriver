package com.otex.homamdriver.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityLoginBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.home.homedriver.HomeDriverActivity
import com.otex.homamdriver.view.home.homerestaurant.HomeActivity
import com.otex.homamdriver.view.login.modeldriver.ModelLoginDriver
import com.otex.homamdriver.view.start.MainActivity
import com.otex.homamrestaurant.view.login.model.ModelLoginRestaurant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import java.util.HashMap


@Suppress("DEPRECATION")
class LoginActivity : BaseActivity() {

    private var loginviewmodel : LoginActivityViewModel? = null
    lateinit var binding: ActivityLoginBinding
    var email_or_phone:String=""
    var password:String=""
    var type:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()

    }

    private fun click() {
         type= intent.getStringExtra("type").toString()

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


        binding.btnLogin.setOnClickListener {
            assimentvariable()
            validationVariable()
        }

    }

    private fun validationVariable() {

        if(email_or_phone == ""){
            binding.editEmailPhone.setError(getString(R.string.enter_email))
        }else if(password == ""){
            binding.editPassword.setError(getString(R.string.enter_password))
        }else{

            login(email_or_phone,password,type)
        }

    }

    private fun assimentvariable() {
        email_or_phone=binding.editEmailPhone.text.toString()
        password=binding.editPassword.text.toString()
    }

    private fun login(emailOrPhone: String, password: String, type: String) {

        val map = HashMap<String, String?>()
        map.put("phone",emailOrPhone)
        map.put("password",password)
        if(type==Constant.store){
            loginviewmodel!!.makeLoginRestaurant(this, map)
        }else{

            loginviewmodel!!.makeLoginDriver(this, map)
        }


    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

            observeRestaurant()

            observerDriver()




        }

    private fun observerDriver() {
        loginviewmodel!!.loginDriverLivedata.observe(this) {

            saveDataInShared(it)
            if(type==Constant.store){
                Toast.makeText(this,getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("type",type)
                startActivity(intent)
                finish()
            }else{

                Toast.makeText(this,getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeDriverActivity::class.java)
                intent.putExtra("type",type)
                startActivity(intent)
                finish()            }

        }

    }

    private fun observeRestaurant() {
        loginviewmodel!!.loginRestaurantLivedata.observe(this) {

            saveDataInShared(it)
            if(type==Constant.store){
                Toast.makeText(this,getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("type",type)
                startActivity(intent)
                finish()
            }else{

                Toast.makeText(this,getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeDriverActivity::class.java)
                intent.putExtra("type",type)
                startActivity(intent)
                finish()
            }


        }
    }

    private fun saveDataInShared(it: ModelLoginRestaurant?) {
        PrefsUtil.with(this).add("token",it!!.token).apply()
        PrefsUtil.with(this).add("id",it.restaurant.id).apply()
        PrefsUtil.with(this).add("username",it.restaurant.name).apply()
        PrefsUtil.with(this).add("email",it.restaurant.email).apply()
    }

    private fun saveDataInShared(it: ModelLoginDriver) {
        PrefsUtil.with(this).add("token",it!!.token).apply()
        PrefsUtil.with(this).add("id",it.driver.id).apply()
        PrefsUtil.with(this).add("username",it.driver.name).apply()
        PrefsUtil.with(this).add("email",it.driver.email).apply()
    }
}