package com.ajtp.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.ajtp.horoscapp.databinding.ItemHoroscopeBinding
import com.ajtp.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var isAnimationRunning = false;
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvTitle.text = context.getString(horoscopeInfo.name)
        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = {
                isAnimationRunning = false;
                onItemSelected(horoscopeInfo)
            })
        }
    }

    private fun startRotationAnimation(view: View, newLambda: () -> Unit) {
        if(!isAnimationRunning) {
            isAnimationRunning = true
            view.animate().apply {
                duration = 500
                interpolator = LinearInterpolator()
                rotationBy(360f)
                withEndAction {
                    newLambda()
                }
                start()
            }
        }
    }
}