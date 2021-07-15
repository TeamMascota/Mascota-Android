package org.mascota.ui.view.profile

import android.content.Intent
import android.net.Uri
import com.bumptech.glide.Glide
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.setIsProfileCreate
import org.mascota.databinding.ActivityBookCompleteBinding
import org.mascota.ui.MainActivity
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.IMG_URI
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.PET_NUM
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.TITLE
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.WRITER
import org.mascota.util.ColorFilterUtil.setImgFilter
import org.mascota.util.StringUtil.makeAuthorText

class BookCompleteActivity : BindingActivity<ActivityBookCompleteBinding>(R.layout.activity_book_complete) {
    override fun initView() {
        initBook()
        initClickEvent()
    }

    private fun initClickEvent() {
        binding.ibBack.setOnClickListener {
            finish()
        }
        binding.btnToHome.setOnClickListener {
            finish()
            setIsProfileCreate(true)
            startMainActivity()
        }
    }

    private fun initBook() {
        intent.apply {
            with(binding) {
                tvAuthor.text = makeAuthorText(getStringExtra(WRITER)?:"")
                tvNum.text = getStringExtra(PET_NUM)
                Glide.with(binding.ivBookCover).load(Uri.parse(getStringExtra(IMG_URI))).into(binding.ivBookCover)
                tvBookTitle.text = getStringExtra(TITLE)
                setImgFilter(binding.ivBookCover)
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}