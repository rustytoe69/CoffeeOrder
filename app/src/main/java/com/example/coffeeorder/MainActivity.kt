package com.example.coffeeorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var quantityNum: Int = 1
    lateinit var quantityNumView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val increaseButton: Button = findViewById(R.id.increase_quantity_button)
        val decreaseButton: Button = findViewById(R.id.decrease_quantity_button)

        val smallSize: RadioButton = findViewById(R.id.small_size_button)
        val mediumSize: RadioButton = findViewById(R.id.medium_size_button)
        val largeSize: RadioButton = findViewById(R.id.large_size_button)

        val orderButton: Button = findViewById(R.id.order_button)

        var size="small"

        quantityNumView = findViewById(R.id.quantity_num)


        increaseButton.setOnClickListener{view->
            updateQuantity(1)
        }
        decreaseButton.setOnClickListener { view->
            updateQuantity(-1)
        }

        smallSize.setOnClickListener { view->
            size="small"
        }
        mediumSize.setOnClickListener { view->
            size="medium"
        }
        largeSize.setOnClickListener { view->
            size="large"
        }


        orderButton.setOnClickListener { view->
            submitOrder(size)
        }
    }


    fun updateQuantity(changeQuantityNum:Int){
        quantityNum+=changeQuantityNum
        if(quantityNum<=0 || quantityNum>10){
            Toast.makeText(this,R.string.more_less_toast,Toast.LENGTH_LONG).show()
            if(quantityNum<=0) quantityNum=1
            if(quantityNum>10) quantityNum=10
        }
        quantityNumView.text=quantityNum.toString()
    }

    fun submitOrder(size: String){
        var total: Double=0.0
        var orderSummary: String
        val nameInput:EditText = findViewById(R.id.name_input)
        val whippedCreamBox:CheckBox=findViewById(R.id.whipped_cream_box)
        val chocolateBox:CheckBox=findViewById(R.id.chocolate_box)
        var orderSummaryText: TextView=findViewById(R.id.order_summary_text)
        orderSummary="Thanks, ${nameInput.text}!\n$quantityNum $size "

        if(quantityNum==1){
            orderSummary+="coffee\n"
        }
        else{
            orderSummary+="coffees\n"
        }

        if(whippedCreamBox.isChecked){
            orderSummary+="+Whipped Cream"
            Log.i("MainActivity", "whipped cream added to summary")
            total+=1.00*quantityNum
            Log.i("MainActivity", "whipped cream updated total to $total")

        }
        if(chocolateBox.isChecked){
            orderSummary+="+Chocolate"
            total+=2.00*quantityNum
        }

        orderSummary+="\nTotal:$"

        if(size=="small") total+=(3.00*quantityNum)
        else if(size=="medium") total+=(4.00*quantityNum)
        else total+=(5.00*quantityNum)

        if(nameInput.text.equals(" ")){
            Toast.makeText(this,R.string.no_name_toast,Toast.LENGTH_LONG).show()
        }

        orderSummary+=total
        orderSummary+=0
        orderSummaryText.text=orderSummary
    }
}