package com.example.visapaymentapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.visapaymentapp.MainActivity
import com.example.visapaymentapp.databinding.FragmentPaymentActionBinding
import com.example.visapaymentapp.extensions.openPage
import com.example.visapaymentapp.model.CardInfo
import com.example.visapaymentapp.ui.OnSwipeTouchListener
import com.example.visapaymentapp.ui.viewmodels.PaymentActionViewModel

class PaymentAction : Fragment() {

    private var paymentActionBinding: FragmentPaymentActionBinding? = null
    private val paymentActionViewModel: PaymentActionViewModel by viewModels()
    private var data: CardInfo? = null
    private var isForEdit: Boolean? = null
    private var somethingWentWrong: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        data = arguments?.getParcelable("data")
        isForEdit = arguments?.getBoolean("isForEdit")
        paymentActionBinding = FragmentPaymentActionBinding.inflate(inflater, container, false)

        val view = paymentActionBinding?.root
        val addFragment = PaymentMethod.newInstance()

        view?.setOnTouchListener(object : OnSwipeTouchListener(context) {

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                addFragment.openPage(true, activity as? MainActivity)
                println("GOHAR:::: LEFTFROMFRAGMENT")
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                addFragment.openPage(true, activity as? MainActivity)
                println("GOHAR:::: RIGHTFROMFRAGMENT")
            }

            override fun onSwipeTop() {
                super.onSwipeTop()
                println("GOHAR:::: TOPFROMFRAGMENT")
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
                println("GOHAR:::: BOTTOMFROMFRAGMENT")
            }
        })
        println("GOHAR::: setOnTouchListener")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentActionBinding?.root?.setOnClickListener {
                println("GOHAR::: onViewCreated setOnClickListener")
            }
        paymentActionBinding?.saveBtn?.setOnClickListener {
            val cardNumber = paymentActionBinding?.cardNumberEditText?.text.toString()
            val expireDate = paymentActionBinding?.expireDateEditText?.text.toString()
            val cvv = paymentActionBinding?.cvvEditText?.text.toString()

            paymentActionViewModel.insertDataToDB(
                CardInfo(
                    cardNumber,
                    expireDate,
                    cvv
                )
            )
            Toast.makeText(context, "Data added", Toast.LENGTH_LONG).show()
            onBackPressed()
        }

        if (isForEdit == true) {
            paymentActionBinding?.deleteBtn?.visibility = View.VISIBLE
            val cardNum = paymentActionBinding?.cardNumberEditText
            val expireDate = paymentActionBinding?.expireDateEditText
            val cvv = paymentActionBinding?.cvvEditText

            cardNum?.setText(data?.cardNumber)
            expireDate?.setText(data?.expireDate)
            cvv?.setText(data?.cvv)

            paymentActionBinding?.deleteBtn?.setOnClickListener {
                data?.let {
                    paymentActionViewModel.deleteData(data!!)
                }
                Toast.makeText(context, "Current item was deleted", Toast.LENGTH_LONG).show()
                onBackPressed()

            }
        }
//        somethingWentWrong = true
        if (somethingWentWrong == true) {
            paymentActionBinding?.errorView?.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        paymentActionBinding = null
    }

    private fun onBackPressed() {
        activity?.onBackPressed()
    }

    companion object {

        @JvmStatic
        fun newInstance(data: CardInfo? = null, isForEdit: Boolean) =
            PaymentAction().apply {
                arguments = Bundle().apply {
                    putBoolean("isForEdit", isForEdit)
                    putParcelable("data", data)
                }
            }
    }
}