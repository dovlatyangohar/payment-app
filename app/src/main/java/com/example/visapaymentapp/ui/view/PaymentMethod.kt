package com.example.visapaymentapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visapaymentapp.MainActivity
import com.example.visapaymentapp.R
import com.example.visapaymentapp.databinding.FragmentPaymentMethodBinding
import com.example.visapaymentapp.extensions.openPage
import com.example.visapaymentapp.ui.viewmodels.PaymentViewModel

class PaymentMethod : Fragment() {

    private var paymentMethodBinding: FragmentPaymentMethodBinding? = null

    private lateinit var adapter: CardsAdapter
    private val paymentViewModel: PaymentViewModel by viewModels()
    private var isForEdit: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        paymentMethodBinding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        return paymentMethodBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentMethodBinding?.addCardFab?.setOnClickListener {

            val addFragment = PaymentAction.newInstance(isForEdit = false)
            addFragment.openPage(true, activity as? MainActivity)


        }

        val recyclerView: RecyclerView = view.findViewById(R.id.cardsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CardsAdapter(mutableListOf())
        adapter.apply {
            adapter.setOnItemClickListener {
                isForEdit = true
                val editFragment = PaymentAction.newInstance(it, isForEdit)
                editFragment.openPage(true, activity as? MainActivity)
            }
        }

        recyclerView.adapter = adapter


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PaymentMethod()
    }

    override fun onResume() {
        super.onResume()
        paymentViewModel.apply {
            getData()
            mCardInfoList.observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        paymentMethodBinding = null
    }
}