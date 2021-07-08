package org.mascota.ui.view.diary.adpter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import org.mascota.R
import org.mascota.databinding.ItemSpinnerBinding
import org.mascota.ui.view.diary.data.SpinnerModel

@Suppress("UNREACHABLE_CODE")
class SpinnerAdapter(
    context: Context,
    @LayoutRes private var resId: Int,
    private val values: MutableList<SpinnerModel>
) : ArrayAdapter<SpinnerModel>(context, resId, values) {
    private var string : String = "목차를 선택해주세요"
    private var chapter : String = ""
    override fun getCount() = values.size

    override fun getItem(position: Int) = values[position]

    //눈에 보여지는 Spinner 모습
    //클릭안했을때
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewBinding : ItemSpinnerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_spinner,parent,false
        )

        viewBinding.tvChapter
        if(string == "목차를 선택해주세요") {
            viewBinding.tvChapter.text = string
            viewBinding.tvChapter.setTextColor(ContextCompat.getColor(context,R.color.maco_light_gray))
            }

        else {
            viewBinding.tvChapter.setTextColor(ContextCompat.getColor(context,R.color.maco_dark_gray))
            viewBinding.tvChapter.text = chapter
            viewBinding.tvChptitle.text = string
        }



        return viewBinding.root // 뿌린다!

        // 생명주기를 갖고있지 않음
        // 리턴을 한 다음에 생명주기가 정의돔
        // 그래서 뷰가 그려지기 전에 설정들을 다 할 것!!


    }

//메뉴 나타났을떄
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
    val viewBinding : ItemSpinnerBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_spinner,parent,false
    )

    val model = values[position]
    viewBinding.tvChptitle.text = model.tv_chatitle
    viewBinding.tvChapter.text = model.tv_chapter

    viewBinding.clContent.setOnClickListener {
        viewBinding.tvChapter.text.toString()

        string = viewBinding.tvChptitle.text.toString()
        chapter = viewBinding.tvChapter.text.toString()
        notifyDataSetChanged()
    }

    return viewBinding.root






    }









}