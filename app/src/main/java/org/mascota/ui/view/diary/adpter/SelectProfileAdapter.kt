package org.mascota.ui.view.diary.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.databinding.ItemProfileBinding
import org.mascota.ui.view.diary.data.ProfileList

class SelectProfileAdapter : RecyclerView.Adapter<SelectProfileAdapter.SelectProfileViewHolder>() {


    var profileList = mutableListOf<ProfileList>()

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



    class SelectProfileViewHolder(

        private val viewBiding : ItemProfileBinding

        ) : RecyclerView.ViewHolder(
        viewBiding.root
    ){
        fun onBind(data : ProfileList){
            viewBiding.animal = data
            viewBiding.tvName.setText(data.tv_name)
            viewBiding.ivProfile.setBackgroundResource(data.img_profile)


        }
    }

    override fun onBindViewHolder(holder: SelectProfileViewHolder, position: Int) {
        holder.onBind(profileList[position])


    }

    override fun getItemCount(): Int = profileList.size


}

