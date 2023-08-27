package com.example.walletapp


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import java.time.format.DateTimeFormatter as DateTimeFormatter1




class CustomAdapter(var context: Context, var data:ArrayList<Expenses>):BaseAdapter() {
    private class ViewHolder(row:View?){

        var txtamount:TextView
        var txtdescription:TextView
        var txtdate: DatePicker
        var btn_update:Button
        var btn_delete:Button

        init {

            this.txtamount = row?.findViewById(R.id.mTxtamount) as TextView
            this.txtdescription = row?.findViewById(R.id.mTxtdescription) as TextView
            this.txtdate = row?.findViewById(R.id.datePickerid) as DatePicker
            this.btn_update = row?.findViewById(R.id.btnUpdate) as Button
            this.btn_delete = row?.findViewById(R.id.btnDelete) as Button

        }
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.expenses_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Expenses = getItem(position) as Expenses
        var format = DateTimeFormatter1.ofPattern("Mmm-dd-yyyy")
        var formattedDate = item.date.format(format)

        viewHolder.txtamount.text = item.amount  //coming from your model
        //viewHolder.txtdate.date = item.date
        viewHolder.txtdescription.text = item.description
        viewHolder.btn_delete.setOnClickListener {

            var ref = FirebaseDatabase.getInstance().getReference().child("cars/"+item.rec_id)

            //toast a message to delete item
            ref.removeValue().addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(context, "Item has been Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show()
                }

            }

        }


        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}

