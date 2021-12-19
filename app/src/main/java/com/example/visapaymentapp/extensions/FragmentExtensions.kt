package com.example.visapaymentapp.extensions

import androidx.fragment.app.Fragment
import com.example.visapaymentapp.MainActivity
import com.example.visapaymentapp.R


fun Fragment.openPage(hasBack: Boolean, activity: MainActivity?) {

    if (hasBack) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.fragmentContainerView, this)
            ?.commit()

    } else {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.fragmentContainerView, this)
            ?.commit()
    }

}