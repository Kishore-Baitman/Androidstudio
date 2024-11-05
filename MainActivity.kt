package com.example.calc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calc.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonClear.setOnClickListener {
                input.text = ""
                output.text = ""
            }

            buttonBracketLeft.setOnClickListener { input.text = addToInputText("(") }
            buttonBracketRight.setOnClickListener { input.text = addToInputText(")") }
            button0.setOnClickListener { input.text = addToInputText("0") }
            button1.setOnClickListener { input.text = addToInputText("1") }
            button2.setOnClickListener { input.text = addToInputText("2") }
            button3.setOnClickListener { input.text = addToInputText("3") }
            button4.setOnClickListener { input.text = addToInputText("4") }
            button5.setOnClickListener { input.text = addToInputText("5") }
            button6.setOnClickListener { input.text = addToInputText("6") }
            button7.setOnClickListener { input.text = addToInputText("7") }
            button8.setOnClickListener { input.text = addToInputText("8") }
            button9.setOnClickListener { input.text = addToInputText("9") }
            buttonDot.setOnClickListener { input.text = addToInputText(".") }
            buttonDivision.setOnClickListener { input.text = addToInputText("÷") }
            buttonMultiply.setOnClickListener { input.text = addToInputText("×") }
            buttonSubtraction.setOnClickListener { input.text = addToInputText("-") }
            buttonAddition.setOnClickListener { input.text = addToInputText("+") }
            buttonEquals.setOnClickListener { showResult() }
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${binding.input.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.toString().replace("÷", "/").replace("×", "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                binding.output.text = "Error"
                binding.output.setTextColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.holo_red_dark
                    )
                )
            } else {
                // Explicitly cast result to Double
                binding.output.text = DecimalFormat("0.######").format(result as Double)
                binding.output.setTextColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.holo_green_dark
                    )
                )
            }
        } catch (e: Exception) {
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }
    }
}
