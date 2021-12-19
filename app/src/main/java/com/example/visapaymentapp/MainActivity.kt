package com.example.visapaymentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.visapaymentapp.databinding.ActivityMainBinding
import com.example.visapaymentapp.extensions.openPage
import com.example.visapaymentapp.ui.view.PaymentMethod

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = PaymentMethod.newInstance()

        fragment.openPage(false, this )
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragmentContainerView, fragment)
//            .commit()
    }
}