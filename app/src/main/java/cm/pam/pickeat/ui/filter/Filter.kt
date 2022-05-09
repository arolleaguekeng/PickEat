package net.tipam2022.storiesview

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.airbnb.paris.extensions.style
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cm.pam.pickeat.R
//for constraint radioGroup
//import `in`.daemondhruv.customviewgroup.ConstraintRadioGroup
import java.lang.StringBuilder
import kotlin.collections.ArrayList

class Filter : AppCompatActivity() {
    //Fields for complements
    lateinit var complementTextView: TextView
    lateinit var selectedComplement: BooleanArray
    var complementArray: Array<String> = arrayOf("cmp1","cmp2", "cmp3")
    private var complementList: ArrayList<String> = arrayListOf()

    //Fields for nutrients
    lateinit var nutrientTextView: TextView
    lateinit var selectedNutrient: BooleanArray
    var nutrientArray: Array<String> = arrayOf("ntr1","ntr2", "ntr3")
    private var nutrientList: ArrayList<String> = arrayListOf()

    //Fileds for selected region
    lateinit var regions: RadioGroup
    lateinit var region: String
    var regionList: List<String> = listOf("Adamawa","Center", "East", "Far North", "Littoral", "North", "North West", "West", "South", "South West")

    //Fields for flavors
    lateinit var flavorTextView: TextView
    lateinit var selectedFlavor: BooleanArray
    var flavorArray: Array<String> = arrayOf("flv1","flv2", "flv3")
    private var flavorList: ArrayList<String> = arrayListOf()

    //Fields for type of meal
    lateinit var mealTextView: TextView
    lateinit var selectedMeal: BooleanArray
    var mealArray: Array<String> = arrayOf("ml1","ml2", "ml3")
    private var mealList: ArrayList<String> = arrayListOf()

    //Fields for eating system
    lateinit var systemTextView: TextView
    lateinit var selectedSystem: BooleanArray
    var systemArray: Array<String> = arrayOf("stm1","stm2", "stm3")
    private var systemList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        //Initialize complement options
        complementTextView = this.findViewById(R.id.complementOption)
        multiOptionsBuilder(complementTextView, complementArray, complementList, "Select Complements")

        //Initialize nutrients options
        nutrientTextView = this.findViewById(R.id.nutrientOption)
        multiOptionsBuilder(nutrientTextView, nutrientArray, nutrientList, "Select Nutrients")

        //initialize regions
        regions = this.findViewById(R.id.regions)
        initializeRadioOption(regions, regionList)
        regions.setOnCheckedChangeListener {
            radioGroup, i -> run {
                var selectedOption = radioGroup.getChildAt(i-1) as RadioButton
                Toast.makeText(this, "${selectedOption.text.toString()}", Toast.LENGTH_SHORT*20).show()
        } }

        //Initialize flavor options
        flavorTextView = this.findViewById(R.id.flavorOption)
        multiOptionsBuilder(flavorTextView, flavorArray, flavorList, "Select Flavors")

        //Initialize type of meal options
        mealTextView = this.findViewById(R.id.mealOption)
        singleOptionBuilder(mealTextView, mealArray, "Select One Type Of Meal")

        //Initialize system options
        systemTextView = this.findViewById(R.id.eatingOption)
        singleOptionBuilder(systemTextView, systemArray, "Select Your Eating System")
    }
    private fun initializeRadioOption(view: RadioGroup, options:List<String>){
//        for (i in 0..options.size - 1){
//            var radio = RadioButton(this)
//            radio.setText(options[i])
//            radio.style(R.style.chip_style)
//            view.addView(radio)
//        }
    }

    private fun multiOptionsBuilder(view: TextView, options:Array<String>,
                    valueIndices: ArrayList<String>, title: String) {
        var selectedValue: BooleanArray = BooleanArray(options.size)
        view.setOnClickListener {
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setCancelable(false)
            builder.setMultiChoiceItems(options, selectedValue, DialogInterface.OnMultiChoiceClickListener {
                    dialogInterface, i, b ->
                if(b){
                    valueIndices.add(i.toString())
                    valueIndices.sort()
                }
                else{
                    valueIndices.removeAt(i)
                }
            })

            builder.setPositiveButton("OK", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                run {
                    val stringBuilder = StringBuilder()
                    for (i in 0..valueIndices.size - 1) {
                        stringBuilder.append(options[i])
                        if(i!=valueIndices.size - 1)
                            stringBuilder.append(", ")
                    }
                    view.text = stringBuilder
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialogInterface, i -> dialogInterface.dismiss()  })
            builder.setNeutralButton("Clear All", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                for(i in 0..selectedValue.size-1){
                    selectedValue[i] = false
                    valueIndices.clear()
                    view.setText("");
                }
            })
            builder.show()
        }
    }

    private fun singleOptionBuilder(view: TextView,
         options:Array<String>, title: String) {
        var selectedValue = 0
        view.setOnClickListener {
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setCancelable(false)
            builder.setSingleChoiceItems(options, selectedValue, DialogInterface.OnClickListener {
                    dialogInterface, i -> selectedValue = i  })

            builder.setPositiveButton("OK", DialogInterface.OnClickListener {
                    dialogInterface, i -> view.text = options[selectedValue]
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialogInterface, i -> dialogInterface.dismiss()  })
            builder.setNeutralButton("Clear All", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                selectedValue = 0
                view.setText("");
            })
            builder.show()
        }
    }
}