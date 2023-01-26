package com.example.dialogs_kotlin

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.CharacterPickerDialog
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var btnDatePickerDialog: Button
    private lateinit var btnTimePickerDialog: Button
    private lateinit var btnAlertDialog: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListener()
    }
    fun initViews(){
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog)
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog)
        btnAlertDialog = findViewById(R.id.btnAlertDialog)
    }

    fun initListener(){
        btnAlertDialog.setOnClickListener(BtnAlertDialogClickListener())
        btnDatePickerDialog.setOnClickListener(BtnDatePickerDialogClickListener())
        btnTimePickerDialog.setOnClickListener(BtnTimePickerDialogClickListener())
    }

    inner class BtnDatePickerDialogClickListener : View.OnClickListener{
        override fun onClick(v: View?) {
            DatePickerDialog(this@MainActivity, MyOnDatesetListener(),2023,1,20).show()
        }

    }
    inner class MyOnDatesetListener : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            btnDatePickerDialog.text = "$year,-- $month,-- $dayOfMonth"

        }
    }

    inner class BtnTimePickerDialogClickListener : View.OnClickListener{
        override fun onClick(v: View?) {
            TimePickerDialog(this@MainActivity, MyOnTimeSetListener(),
            20,
            40,
            true).show()
        }

    }
    inner class MyOnTimeSetListener : TimePickerDialog.OnTimeSetListener{
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
            btnTimePickerDialog.text = "$hourOfDay : $minute"
        }

    }

    inner class BtnAlertDialogClickListener : View.OnClickListener{
        override fun onClick(v: View?) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Do you really want to Submit the Exam?")
            builder.setTitle("Exam Submit")
            builder.setIcon(R.drawable.ic_launcher_background)

         val listener = DialogInterface.OnClickListener { dialog, which ->
             if (which == Dialog.BUTTON_POSITIVE){
                 makeToast1("Positive Button Clicked")
             }
             if (which == Dialog.BUTTON_NEGATIVE){
                 makeToast1("Negative Button Clicked")
             }
             if (which == Dialog.BUTTON_NEUTRAL){
                 makeToast1("Neutral Button Clicked")
             }
         }
            builder.setPositiveButton("Yes",listener)
            builder.setNegativeButton("No",listener)
            builder.setNeutralButton("NA",listener)

            builder.setOnCancelListener(DialogCancelClickListener())
            builder.setOnDismissListener(DialogDismissClickListener())

          val logoutDialog = builder.create()
          logoutDialog.setCancelable(true)

          logoutDialog.show()
        }
        inner class DialogCancelClickListener: DialogInterface.OnCancelListener {

            override fun onCancel(dialog: DialogInterface?) {
                makeToast1("Cancel Clicked")
            }
        }

        inner class DialogDismissClickListener : DialogInterface.OnDismissListener {

            override fun onDismiss(dialog: DialogInterface?) {
                makeToast1("Dismiss")
            }

        }

       /* inner class AlertButtonsClickListener: DialogInterface.OnClickListener,
                (dialog: DialogInterface, which: Int) -> Unit {

            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == Dialog.BUTTON_POSITIVE){
                    makeToast1("Positive Button Clicked")
                }
                if (which == Dialog.BUTTON_NEGATIVE){
                    makeToast1("Negative Button Clicked")
                }
                if (which == Dialog.BUTTON_NEUTRAL){
                    makeToast1("Neutral Button Clicked")
                }                   }

            override fun invoke(dialog: DialogInterface, which: Int) {
                TODO("Not yet implemented")
            }
        }*/

        inner class PositiveBtnClickListener : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                makeToast1("Yes Clicked")
            }

        }
        inner class NegativeBtnClickListener : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                makeToast1("No Clicked")
            }

        }
        inner class NeutralBtnClickListener : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                makeToast1("NA Clicked")
            }

        }


    }
    private fun makeToast1(text : String){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()

    }
}