package org.mascota.ui.view.diary.adpter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.databinding.ItemProfileBinding
import org.mascota.ui.view.diary.data.ProfileList
import kotlin.coroutines.coroutineContext

class SelectProfileAdapter : RecyclerView.Adapter<SelectProfileAdapter.SelectProfileViewHolder>() {


    var profileList = mutableListOf<ProfileList>()
    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectProfileViewHolder {
        //뷰홀더
        //뷰에 대한 정보(layou,data)담고 있는 상자이자
        //이를 넣어주는 역할
        //databinding 객체 만들어서 item_profile.xml에 뷰로 만들어줄 데이터를 담아줘야함
        //databidding해서 이곳을 객체로 넘겨줘야함


        val binding : ItemProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_profile, parent, false)

        return SelectProfileViewHolder(binding)
    }



    inner class SelectProfileViewHolder(

        private val viewBiding : ItemProfileBinding

        ) : RecyclerView.ViewHolder(
        viewBiding.root
    ){
        fun onBind(data : ProfileList, position: Int){
            viewBiding.animal = data
            viewBiding.tvName.setText(data.tv_name)
            viewBiding.ivProfile.setBackgroundResource(data.img_profile)
            viewBiding.listView.setOnClickListener {
                // 색깔 바뀌게 했는데
                val item = profileList.get(position).tv_name
                itemClickListener.onClick(it, position, item)

                viewBiding.tvName.isSelected = !it.isSelected

                //Log.d("이름","아이템:")

            }



        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onBindViewHolder(holder: SelectProfileViewHolder, position: Int) {
        holder.onBind(profileList[position], position)


    }



    override fun getItemCount(): Int = profileList.size

    //어댑터에 클릭리스너 구현하기
   interface OnItemClickListener{

        fun onClick(v : View, position: Int, item : String)
    }



    fun setItemClickListenr(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener

    }
// 프로필 색깔 함수를 만들었어! seletor로!


}

