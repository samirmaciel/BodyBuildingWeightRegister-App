package com.samirmaciel.bodybuildingweightregister.view.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.widget.Button
import com.samirmaciel.bodybuildingweightregister.R

object TabConfiguration {

    var currentTab: TabScreen? = null
    var lastTab: TabScreen? = null

    var btnTrainingA: Button? = null
    var btnTrainingB: Button? = null
    var btnTrainingC: Button? = null

    var colorActive: Int? = null
    var colorUnActive: Int? = null


    fun goTo(tabScreen: TabScreen): Int? {

        when (tabScreen) {

            TabScreen.TRAINING_A -> {
                if (currentTab != null) {
                    when (currentTab) {
                        TabScreen.TRAINING_B -> {
                            currentTab = TabScreen.TRAINING_A
                            colorUnActive?.let { btnTrainingB?.setBackgroundColor(it) }
                            colorActive?.let { btnTrainingA?.setBackgroundColor(it) }
                            return R.id.action_trainingBFragment_to_trainingAFragment
                        }
                        TabScreen.TRAINING_C -> {
                            currentTab = TabScreen.TRAINING_A
                            colorUnActive?.let { btnTrainingC?.setBackgroundColor(it) }
                            colorActive?.let { btnTrainingA?.setBackgroundColor(it) }
                            return R.id.action_trainingCFragment_to_trainingAFragment
                        }

                        else -> return null
                    }
                } else {
                    return null
                }
            }

            TabScreen.TRAINING_B -> {
                if (currentTab != null) {

                    when (currentTab) {
                        TabScreen.TRAINING_A -> {
                            currentTab = TabScreen.TRAINING_B
                            colorUnActive?.let { btnTrainingA?.setBackgroundColor(it) }
                            colorActive?.let { btnTrainingB?.setBackgroundColor(it) }
                            return R.id.action_trainingAFragment_to_trainingBFragment
                        }
                        TabScreen.TRAINING_C -> {
                            currentTab = TabScreen.TRAINING_B
                            colorUnActive?.let { btnTrainingC?.setBackgroundColor(it) }
                            colorActive?.let { btnTrainingB?.setBackgroundColor(it) }
                            return R.id.action_trainingCFragment_to_trainingBFragment
                        }

                        else -> return null
                    }
                } else {
                    return null
                }
            }

            TabScreen.TRAINING_C -> {
                if (currentTab != null) {

                    when (currentTab) {
                        TabScreen.TRAINING_B -> {
                            currentTab = TabScreen.TRAINING_C
                            colorUnActive?.let { btnTrainingB?.setBackgroundColor(it) }
                            colorActive?.let { btnTrainingC?.setBackgroundColor(it) }
                            return R.id.action_trainingBFragment_to_trainingCFragment
                        }
                        TabScreen.TRAINING_A -> {
                            currentTab = TabScreen.TRAINING_C
                            colorUnActive?.let { btnTrainingA?.setBackgroundColor(it) }
                            colorActive?.let { btnTrainingC?.setBackgroundColor(it) }
                            return R.id.action_trainingAFragment_to_trainingCFragment
                        }

                        else -> return null
                    }

                } else {
                    return null
                }
            }
        }

        return null
    }

    fun print(){
        Log.d("TESTECOLOR", "current: " + currentTab?.name)
        Log.d("TESTECOLOR", "last: " + lastTab?.name)
    }
}