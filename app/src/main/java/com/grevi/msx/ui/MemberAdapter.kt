package com.grevi.msx.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grevi.msx.R
import com.grevi.msx.model.Member
import kotlinx.android.synthetic.main.list_member.view.*

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    private val memberList : ArrayList<Member> = arrayListOf()

    inner class MemberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(member: Member){
            itemView.let {
                it.name.text = member.name
                it.age.text = member.age.toString()
                it.address.text = member.address
                it.hobby.text = member.hobby
            }
        }
    }

    fun addItem(item : List<Member>) {
        memberList.clear()
        memberList.addAll(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(memberList[position])
    }

}