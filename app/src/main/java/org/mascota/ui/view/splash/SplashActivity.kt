package org.mascota.ui.view.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.getLogin
import org.mascota.databinding.ActivitySplashBinding
import org.mascota.ui.MainActivity
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.user.login.LoginActivity
import org.mascota.util.StatusBarUtil.setStatusBarColor

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun initView() {
        setStatusBarColor(getColor(R.color.maco_orange))
        Glide.with(binding.ivSplash).load(R.raw.gif_logo).into(binding.ivSplash)
        setHandler()
    }

    private fun setHandler() {
        Handler(Looper.getMainLooper()).postDelayed({
            if(getLogin())
                startMain()
            else
                startLogin()
            finish()
        }, SPLASH_TIME)
    }

    private fun startLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun startMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        const val SPLASH_TIME = 3000L
    }
}